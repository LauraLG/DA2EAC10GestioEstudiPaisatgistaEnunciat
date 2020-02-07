package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Estudi;
import vista.EstudiForm;
import vista.EstudiLlista;
import vista.MenuEstudiVista;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;
import principal.GestorEstudisException;

/**
 *
 * @author FTA
 */
public class ControladorEstudi implements ActionListener {

    private MenuEstudiVista menuEstudiVista;
    private EstudiForm estudiForm = null;
    private EstudiLlista estudiLlista = null;
    private int opcioSelec = 0;

    public ControladorEstudi() {

        /*
        TODO        
        S'inicialitza l'atribut menuEstudiVista (això mostrarà el menú estudis)
        Es crida a afegirListenersMenu        
         */
        
        menuEstudiVista = new MenuEstudiVista();
        afegirListenersMenu();
    }

    //El controlador com a listener dels controls de les finestres que gestionen els estudis
    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL MENU
    private void afegirListenersMenu() {
        /*
        TODO        
        A cada botó del menú estudis, s'afegeix aquest mateix objecte (ControladorEstudi) com a listener        
         */

        for (JButton boton : menuEstudiVista.getMenuButtons()){
            boton.addActionListener(this);
        }
    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL FORMULARI
    private void afegirListenersForm() {
        /*
        TODO        
        A cada botó del formulari de l'estudi, s'afegeix aquest mateix objecte (ControladorEstudi) com a listener        
         */
        
        estudiForm.getbDesar().addActionListener(this);
        estudiForm.getbSortir().addActionListener(this); 
    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DEL BOTO DE LA LLISTA
    private void afegirListenersLlista() {
        /*
        TODO        
        Al botó de sortir de la llista d'estudis, s'afegeix aquest mateix objecte (ControladorEstudi) com a listener        */
        
        estudiLlista.getbSortir().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO        
        Nota:
            Com ControladorEstudi és listener del menú d'estudis, del formulari i de la llista, llavors en aquest mètode
            actionPerformed heu de controlar si l'usuari ha premut algun botó de qualsevol dels esmentats frames.
            Ull! En el cas del formulari i de la llista, com provenen del menú (els llança el menú d'estudis), heu de verificar
            primer que els objectes estudiForm o estudiLlista no són nulls, per tal de saber si podeu comparar-los amb
            alguns dels botons d'aquests frames.
        
        Accions per al menú:
            S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es correspon amb
            la posició que el botó ocupa a l'array de botons de menuEstudiVista
            També, heu d'actualitzar la propietat opcioSelec (amb l'opció que ha premut l'usuari)
        
        Accions per al formulari d'estudi:
            
            ---- DESAR ----
            Si el botó premut per l'usuari és el botó de desar del formulari d'estudi, llavors
                Si l'opció seleccionada (al menú d'estudis) és 1 (alta), llavors  
                        Es crea un nou objecte Estudi amb les dades del formulari
                        S'afegeix l'estudi creat a la llista de ControladorPrincipal
                        Es posa aquest estudi com estudiActual (de ControladorPrincipal) i es canvia l'atribut
                        opcioSelec a 2
                Si l'opció seleccionada (al menú d'estudis) és 3 (modificació), llavors
                    Nota: no es validen dades amb aquesta opció (per simplificar)
                    Es modifica l'objecte estudi amb les dades del formulari (penseu que és l'estudiActual de ControladorPrincipal)
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir del formulari d'estudis, llavors
                Heu de tornar al menú d'estudis (i amagar el formulari)
        
        Accions per a la llista d'estudis:
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir de la llista d'estudis, llavors
                Heu de tornar al menú d'estudis (i amagar la llista)
         
         */
        
        //Acciónes en el menú
        JButton[] losBotones = menuEstudiVista.getMenuButtons();
        
        for (int i = 0; i < losBotones.length; i++) {
            if (e.getSource() == losBotones[i]) {
                menuEstudiVista.getFrame().setVisible(false);
                opcioSelec = i;
                bifurcaOpcio(i);
            }
        }
        
        //Acciónes para el formulario estudi
        
        if(estudiForm != null){
            if (e.getSource() == estudiForm.getbDesar()) {
                if (opcioSelec == 1){
                    
 //                  int codi = Integer.parseInt(estudiForm.gettCodi().getText());
                    String nom = estudiForm.gettNom().getText();
                    String adreca = estudiForm.gettAdreca().getText();
//                    int pos = comprovarEstudi(codi);
                   // Estudi estudi = new Estudi(estudiForm.gettNom().getText(),estudiForm.gettAdreca().getText());
                   Estudi estudi = new Estudi (nom, adreca);
                    
//                    if(pos != -1){
//                        ControladorPrincipal.getEstudis()[pos] = estudi;
//                    } else {
//                        ControladorPrincipal.getEstudis()[ControladorPrincipal.getPosicioEstudis()] = estudi;                        
//                    }

                    ControladorPrincipal.getEstudis()[ControladorPrincipal.getPosicioEstudis()] = estudi;
                    ControladorPrincipal.setPosicioEstudis();

                    estudiForm.gettCodi().setText(String.valueOf(estudi.getCodi()));
                    ControladorPrincipal.setEstudiActual(estudi);
                    opcioSelec = 2;
                    
//                    estudiForm.gettNom().setText(String.valueOf(estudi.getNom()));
//                    estudiForm.gettAdreca().setText(String.valueOf(estudi.getAdreca()));
//                    ControladorPrincipal.setEstudiActual(estudi);
//                    opcioSelec = 2;
                    
                } else if (opcioSelec == 3){
                    ControladorPrincipal.getEstudiActual().setCodi(Integer.parseInt(estudiForm.gettCodi().getText()));
                    ControladorPrincipal.getEstudiActual().setNom(estudiForm.gettNom().getText());
                    ControladorPrincipal.getEstudiActual().setAdreca(estudiForm.gettAdreca().getText());
                }
                
            } else if (e.getSource() == estudiForm.getbSortir()){
                estudiForm.getFrame().setVisible(false);
                menuEstudiVista.getFrame().setVisible(true);
            }                         
                      
        }
        
        if (estudiLlista != null){
            if (e.getSource() == estudiLlista.getbSortir()){
                estudiLlista.getFrame().setVisible(false);
                menuEstudiVista.getFrame().setVisible(true);
            }           
            
        }       

    }

    private void bifurcaOpcio(Integer opcio) {

        switch (opcio) {

            case 0: //sortir
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;

            case 1: // alta
                if (ControladorPrincipal.getPosicioEstudis()< ControladorPrincipal.getMAXESTUDIS()) {
                    estudiForm = new EstudiForm();
                    estudiForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuEstudiVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Màxim nombre d'estudis assolits.");
                }
                break;

            case 2: //seleccionar
                menuEstudiVista.getFrame().setVisible(true);
                if (ControladorPrincipal.getEstudis()[0] != null) {
                    seleccionarEstudi();
                } else {
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Abans s'ha de crear al menys un estudi");
                }
                break;

            case 3: //modificar
                if (ControladorPrincipal.getEstudis()[0] != null) {
                    seleccionarEstudi();
                    estudiForm = new EstudiForm(ControladorPrincipal.getEstudiActual().getCodi(), ControladorPrincipal.getEstudiActual().getNom(), ControladorPrincipal.getEstudiActual().getAdreca());
                    estudiForm.gettCodi().setEnabled(false);
                    afegirListenersForm();                   
                    
                } else {
                    menuEstudiVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Abans s'ha de crear al menys un estudi");
                }
                break;

            case 4: // llistar
                if (ControladorPrincipal.getEstudis()[0] != null) {
                    estudiLlista = new EstudiLlista();
                    afegirListenersLlista();
                } else {
                    menuEstudiVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Abans s'ha de crear al menys un estudi");
                }
                break;

            case 5: //carregar
            /*
            TODO
                
            Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega 
            (propietat a Controlador Principal: ara XML i Serial)
            Un cop seleccionat el mètode, amb un altre dialog, es demana el codi de l'estudi a carregar 
            (recordeu que el nom del fitxer és el codi de l'estudi i l'extensió)
            Un cop l'usuari ha entrat el codi i ha premut OK,
                Es crea un objecte estudi (nouEstudi) com retorn de cridar a carregarEstudi del gestor de persistència. Penseu que la
                carrega pots ser d'un fitxer XML o bé d'un fitxer serial.
                Es comprova si el codi del nouEstudi ja existeix al vector d'estudis (això donarà la posició on s'ha trobat a la llista). Penseu
                que en aquesta classe teniu un mètode per fer la comprovació.
                Si existeix,
                    es mostra un dialog notificant a l'usuari si vol substituir l'estudi del vector pel que es carregarà des de el fitxer (JOptionPane.showOptionDialog)
                    Si l'usuari cancela, no es fa res
                    Si l'usuari accepta, llavors es posa el nouEstudi al vector a la mateixa posició on s'havia trobat aquest codi
                Si no existeix,
                    S'afegeix el nouEstudi al vector d'estudis a la darrera posició
                    Es mostra un missatge confirmant l'addició (JOptionPane.showMessageDialog)
            
            */
                
                menuEstudiVista.getFrame().setVisible(true);                
                int metodo = JOptionPane.showOptionDialog(null, "Seleciona un metodo",
                        "Carregar estudi", 0, JOptionPane.QUESTION_MESSAGE,null,
                        ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                
                if (metodo != JOptionPane.CLOSED_OPTION){
                    GestorPersistencia gestor = new GestorPersistencia();
                    Estudi nouEstudi;
                    
                    try {
                        String codi = JOptionPane.showInputDialog("Cual el el código del estudio que quieres cargar?");
                        gestor.carregarEstudi(ControladorPrincipal.getMETODESPERSISTENCIA()[metodo], "XML");
                        
                        if (ControladorPrincipal.getMETODESPERSISTENCIA()[metodo].equals("XML")){
                            nouEstudi = ((GestorXML)gestor.getGestor()).getEstudi();
                        } else {
                            nouEstudi = ((GestorXML)gestor.getGestor()).getEstudi();
                        }
                        
                        int pos = comprovarEstudi(nouEstudi.getCodi());
                        
                        if (pos >= 0) {
                            
                            Object[] options = {"OK", "Cancellar"};                          
                            int i = JOptionPane.showOptionDialog(null, "Apreta OK por reeplazarlo", "El Esrudi ya existe",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                    null, options, options[0]);
                            
                            if (i == 0) {
                                ControladorPrincipal.getEstudis()[pos] = nouEstudi;
                            }
                    } else {
                            ControladorPrincipal.getEstudis()[ControladorPrincipal.getPosicioEstudis()]  = nouEstudi;
                            ControladorPrincipal.setPosicioEstudis();
                            JOptionPane.showMessageDialog(menuEstudiVista.getFrame(),"Estudio se ha añadido correctamente");
                        }
                } catch (GestorEstudisException e){
                        JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), e.getMessage());
                }               
            }
                break;

            case 6: //desar
                /*
                TODO
                
                Es comprova si s'ha seleccionat l'estudi, mostrant, si correspon, missatges d'error (JOptionPane.showMessageDialog)
                Si s'ha sseleccionat l'estudi, 
                    Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega
                    (propietat a Controlador Principal: ara XML i Serial)
                    Un cop escollit el mètode, es desa l'estudi cridant a desarEstudi del gestor de persistència
                 */
                menuEstudiVista.getFrame().setVisible(true);
                
                if (ControladorPrincipal.getEstudiActual() != null){
                    int message = JOptionPane.QUESTION_MESSAGE;
                    int metod = JOptionPane.showOptionDialog(null, "Selecciona un método", "Desar estudi", 0, message, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                    
                    if (metod != JOptionPane.CLOSED_OPTION){
                        
                        GestorPersistencia gestor = new GestorPersistencia();
                        
                        try {
                            gestor.desarEstudi(ControladorPrincipal.getMETODESPERSISTENCIA()[metod], String.valueOf(ControladorPrincipal.getEstudiActual().getCodi()), ControladorPrincipal.getEstudiActual());
                        } catch (GestorEstudisException e) {
                            JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), e.getMessage());
                        }                        
                    }
                    
                } else{
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Se tiene que selecionar un Estudi");
                }
                break;      
            }
    }
    
    private void seleccionarEstudi() {

        String[] nomEstudi = new String[ControladorPrincipal.getPosicioEstudis()];

        int i = 0;

        for (Estudi estudi : ControladorPrincipal.getEstudis()) {

            if (estudi != null) {
                nomEstudi[i] = estudi.getNom();
            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona un estudi", "Selecció d'estudi", 0, messageType, null, nomEstudi, "A");
        
        if (code != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setEstudiActual(ControladorPrincipal.getEstudis()[code]);
        }

    }

    private Integer comprovarEstudi(int codi) {

        boolean trobat = false;

        int pos = -1;

        for (int i = 0; i < ControladorPrincipal.getEstudis().length && !trobat; i++) {

            if (ControladorPrincipal.getEstudis()[i] != null) {
                if (ControladorPrincipal.getEstudis()[i].getCodi() == codi) {
                    pos = i;
                    trobat = true;
                }
            }
        }

        return pos;
    }

}