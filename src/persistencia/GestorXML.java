package persistencia;

import model.Dissenyador;
import model.Jardiner;
import model.Torn;
import model.Treballador;
import java.io.File;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.Estudi;
import principal.GestorEstudisException;
import model.Projecte;

/**
 *
 * @author cesca
 */
public class GestorXML implements ProveedorPersistencia{

    private Document doc;
    private Estudi estudi;

    public Estudi getEstudi() {
        return estudi;
    }

    public void setEstudi(Estudi estudi) {
        this.estudi = estudi;
    }

    public void desarEstudi(String nomFitxer, Estudi estudi) throws GestorEstudisException {
        construeixModel(estudi);
        desarModel(nomFitxer);
    }

    public void carregarEstudi(String nomFitxer) throws GestorEstudisException {
        carregarFitxer(nomFitxer);
        fitxerEstudi();
    }

    /*Paràmetres: Estudi a partir de la qual volem construir el model
     *
     *Acció: 
     * - Llegir els atributs de l'objecte estudi passat per paràmetre 
     *   per construir un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "estudi". Aquesta arrel, l'heu d'afegir 
     *   a doc. Un cop fet això, heu de recórrer l'ArrayList components d'estudi
     *   i per a cada component, afegir un fill a doc. Cada fill 
     *   tindrà com atributs els atributs de l'objecte (nif, nom, …).
     *   Si l'atribut a afegir és l'atribut booleà actiu o finalitzat, quan aquests siguin verdaders
     *   el valor de l'atribut del document XML serà 1, en cas contrari 0.
     *
     * - Si es tracta d'un jardiner, a més, heu d'afegir un fill addicional amb 
     *   el seu torn.
     *
     * - Si es tracte d'un projecte, a més, heu d'afegir fills addicionals amb 
     *   els dissenyador i jardiners assignats.
     *
     *Retorn: cap
     */
    public void construeixModel(Estudi estudi) throws GestorEstudisException {
        //Es construeix el document model
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Element fill, net, renet;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new GestorEstudisException("GestorXML.model");
        }

        this.doc = (Document) builder.newDocument();

        //Element arrel
        Element arrel = doc.createElement("estudi");
        arrel.setAttribute("codi", String.valueOf(estudi.getCodi()));
        arrel.setAttribute("nom", estudi.getNom());
        arrel.setAttribute("adreca", estudi.getAdreca());
        doc.appendChild(arrel);

        for (int i = 0; i < estudi.getComponents().size(); i++) {

            if (estudi.getComponents().get(i) instanceof Torn) {

                fill = doc.createElement("torn");

                fill.setAttribute("codi", ((Torn) estudi.getComponents().get(i)).getCodi());
                fill.setAttribute("nom", ((Torn) estudi.getComponents().get(i)).getNom());
                fill.setAttribute("horaInici", ((Torn) estudi.getComponents().get(i)).getHoraInici());
                fill.setAttribute("horaAcabament", ((Torn) estudi.getComponents().get(i)).getHoraAcabament());

                arrel.appendChild(fill);

            } else if (estudi.getComponents().get(i) instanceof Dissenyador) {

                fill = doc.createElement("dissenyador");

                fill.setAttribute("nif", ((Dissenyador) estudi.getComponents().get(i)).getNif());
                fill.setAttribute("nom", ((Dissenyador) estudi.getComponents().get(i)).getNom());

                if (((Dissenyador) estudi.getComponents().get(i)).getActiu()) {
                    fill.setAttribute("actiu", "1");
                } else {
                    fill.setAttribute("actiu", "0");
                }

                arrel.appendChild(fill);

            } else if (estudi.getComponents().get(i) instanceof Jardiner) {

                fill = doc.createElement("jardiner");

                fill.setAttribute("nif", ((Jardiner) estudi.getComponents().get(i)).getNif());
                fill.setAttribute("nom", ((Jardiner) estudi.getComponents().get(i)).getNom());

                if (((Jardiner) estudi.getComponents().get(i)).getActiu()) {
                    fill.setAttribute("actiu", "1");
                } else {
                    fill.setAttribute("actiu", "0");
                }

                if (((Jardiner) estudi.getComponents().get(i)).getTorn() != null) {
                    net = doc.createElement("torn");

                    net.setAttribute("codi", ((Jardiner) estudi.getComponents().get(i)).getTorn().getCodi());
                    net.setAttribute("nom", ((Jardiner) estudi.getComponents().get(i)).getTorn().getNom());
                    net.setAttribute("horaInici", ((Jardiner) estudi.getComponents().get(i)).getTorn().getHoraInici());
                    net.setAttribute("horaAcabament", ((Jardiner) estudi.getComponents().get(i)).getTorn().getHoraAcabament());

                    fill.appendChild(net);
                }

                arrel.appendChild(fill);

            } else if (estudi.getComponents().get(i) instanceof Projecte) {

                fill = doc.createElement("projecte");

                fill.setAttribute("codi", String.valueOf(((Projecte) estudi.getComponents().get(i)).getCodi()));
                fill.setAttribute("nifClient", ((Projecte) estudi.getComponents().get(i)).getNifClient());

                if (((Projecte) estudi.getComponents().get(i)).isFinalitzat()) {
                    fill.setAttribute("finalitzat", "1");
                } else {
                    fill.setAttribute("finalitzat", "0");
                }

                fill.setAttribute("pressupost", String.valueOf(((Projecte) estudi.getComponents().get(i)).getPressupost()));

                Iterator codi = ((Projecte) estudi.getComponents().get(i)).getTreballadors().keySet().iterator();

                while (codi.hasNext()) {

                    Object codiActual = codi.next();

                    if (codiActual.equals("dissenyador")) {

                        net = doc.createElement("dissenyador");

                        net.setAttribute("nif", ((Dissenyador) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get("dissenyador")).getNif());
                        net.setAttribute("nom", ((Dissenyador) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get("dissenyador")).getNom());

                        if (((Dissenyador) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get("dissenyador")).getActiu()) {
                            net.setAttribute("actiu", "1");
                        } else {
                            net.setAttribute("actiu", "0");
                        }

                    } else { //És jardiner

                        net = doc.createElement("jardiner");

                        net.setAttribute("nif", ((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getNif());
                        net.setAttribute("nom", ((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getNom());

                        if (((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getActiu()) {
                            net.setAttribute("actiu", "1");
                        } else {
                            net.setAttribute("actiu", "0");
                        }

                        if (((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getTorn() != null) {
                            renet = doc.createElement("torn");

                            renet.setAttribute("codi", ((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getTorn().getCodi());
                            renet.setAttribute("nom", ((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getTorn().getNom());
                            renet.setAttribute("horaInici", ((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getTorn().getHoraInici());
                            renet.setAttribute("horaAcabament", ((Jardiner) ((Projecte) estudi.getComponents().get(i)).getTreballadors().get(codiActual)).getTorn().getHoraAcabament());

                            net.appendChild(renet);
                        }

                    }

                    fill.appendChild(net);
                }

                arrel.appendChild(fill);
            }
        }
    }

    public void desarModel(String nomFitxer) throws GestorEstudisException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new GestorEstudisException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorEstudisException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new GestorEstudisException("GestorXML.carrega");
        }
    }

    /*Paràmetres: cap
     *
     *Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc, 
     *   per assignar valors als atributs d'estudi i la resta d'objectes que formen 
     *   els components d'estudi.
     *    
     * - Primer heu de crear l'objecte estudi a partir de l'arrel del document XML
     *   per després recórrer el doc i per cada fill, afegir un objecte a l'atribut 
     *   components d'estudi mitjançant el mètode escaient de la classe Estudi.
    
     * - Si l'element del document XML que s'ha llegit és un jardiner, recordeu 
     *   que a més d'afegir-lo a components, també haureu d'assignarli el torn 
     *   corresponent.
     *
     * - Si l'element del document XML que s'ha llegit és un projecte, recordeu 
     *   que a més d'afegir-lo a components, també haureu d'assignar-li
     *   el dissenyador i jardiners corresponents.
     *
     * - Penseu que els estats actius dels treballadors i finalitzats del projecte,
     *   s'han d'afegir un cop creat l'objecte pertinent.
     *
     *Retorn: cap
     */
    private void fitxerEstudi() throws GestorEstudisException {
        String component;

        Element arrel = doc.getDocumentElement();

        //Es crea l'objecte estudi
        estudi = new Estudi(Integer.parseInt(arrel.getAttribute("codi")), arrel.getAttribute("nom"), arrel.getAttribute("adreca"));

        //Recorregut de nodes fill d'un element       
        NodeList llistaFills = arrel.getChildNodes();

        for (int i = 0; i < llistaFills.getLength(); i++) {

            Node fill = llistaFills.item(i);

            if (fill.getNodeType() == Node.ELEMENT_NODE) {

                component = fill.getNodeName();

                if (component.equals("torn")) {

                    String codi = ((Element) fill).getAttribute("codi");
                    String nom = ((Element) fill).getAttribute("nom");
                    String horaInici = ((Element) fill).getAttribute("horaInici");
                    String horaAcabament = ((Element) fill).getAttribute("horaAcabament");

                    estudi.addTorn(new Torn(codi, nom, horaInici, horaAcabament));

                } else if (component.equals("dissenyador")) {

                    String nif = ((Element) fill).getAttribute("nif");
                    String nom = ((Element) fill).getAttribute("nom");

                    boolean actiu = false;

                    if (((Element) fill).getAttribute("actiu").equals("1")) {
                        actiu = true;
                    }

                    Dissenyador nouDissenyador = new Dissenyador(nif, nom);
                    nouDissenyador.setActiu(actiu);

                    estudi.addDissenyador(nouDissenyador);

                } else if (component.equals("jardiner")) {

                    String nif = ((Element) fill).getAttribute("nif");
                    String nom = ((Element) fill).getAttribute("nom");

                    boolean actiu = false;

                    if (((Element) fill).getAttribute("actiu").equals("1")) {
                        actiu = true;
                    }

                    Jardiner nouJardiner = new Jardiner(nif, nom);
                    nouJardiner.setActiu(actiu);

                    NodeList nets = fill.getChildNodes();

                    for (int j = 0; j < nets.getLength(); j++) {

                        Node net = nets.item(j);

                        if (net != null && net.getNodeType() == Node.ELEMENT_NODE) {
                            nouJardiner.setTorn((Torn) estudi.getComponents().get(estudi.selectComponent(3, ((Element) net).getAttribute("codi"))));
                        }
                    }

                    estudi.addJardiner(nouJardiner);

                } else if (component.equals("projecte")) {

                    int codi = Integer.parseInt(((Element) fill).getAttribute("codi"));
                    String nifClient = ((Element) fill).getAttribute("nifClient");
                    double pressupost = Double.parseDouble(((Element) fill).getAttribute("pressupost"));

                    boolean finalitzat = false;

                    if (((Element) fill).getAttribute("finalitzat").equals("1")) {
                        finalitzat = true;
                    }

                    Projecte nouProjecte = new Projecte(codi, nifClient, pressupost);
                    nouProjecte.setFinalitzat(finalitzat);

                    estudi.addProjecte(nouProjecte);

                    NodeList nets = fill.getChildNodes();

                    for (int j = 0; j < nets.getLength(); j++) {

                        Node net = nets.item(j);

                        if (net != null && net.getNodeType() == Node.ELEMENT_NODE) {

                            int tipus = 0;

                            component = net.getNodeName();

                            switch (component) {
                                case "dissenyador":
                                    tipus = 1;
                                    break;
                                default: //Jardiner
                                    tipus = 2;
                                    break;
                            }

                            nouProjecte.addTreballador((Treballador)estudi.getComponents().get(estudi.selectComponent(tipus, ((Element) net).getAttribute("nif"))));

                        }
                    }
                }
            }
        }
    }
}
