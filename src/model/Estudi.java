/*
 * Classe que defineix un estudi. Un estudi es defineix per un codi, un nom i adreça 
 * on està ubicat. A més, contindrà vectors amb dissenyadors, jardiners, torns 
 * i projectes.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import principal.Component;
import principal.GestorEstudisException;

/**
 *
 * @author root
 */
public class Estudi implements Component, Serializable{

    private int codi;
    private static int properCodi = 1; //El proper codi a assignar
    private String nom;
    private String adreca;
    private List<Component> components = new ArrayList();

    /*
     TODO
     CONSTRUCTOR
     Paràmetres: valors per tots els atributs de la classe menys els vectors i el
     codi.
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     - Assignar a l'atribut codi el valor de l'atribut properCodi i actualitzar
     properCodi amb el següent codi a assignar.
     */
    public Estudi(String nom, String adreca) {
        codi = properCodi;
        properCodi++;
        this.nom = nom;
        this.adreca = adreca;
    }

    public Estudi(int codi, String nom, String adreca) {
        this.codi = codi;
        this.nom = nom;
        this.adreca = adreca;
    }

    /*
     TODO Mètodes accessors    
     */
    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public static int getProperCodi() {
        return properCodi;
    }

    public static void setProperCodi() {
        properCodi++;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }


    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou estudi. Les dades
     a demanar són les que li passem per paràmetre al constructor.
     - Heu de tenir en compte que el nom i adreça poden ser frases, per exemple, New Estudi
     o C/Gran Via, 4.
     Retorn: El nou estudi creat.
     */
    public static Estudi addEstudi() {

        String nom;
        String adreca;

        System.out.println("Nom de l'estudi:");
        nom = DADES.nextLine();
        System.out.println("Adreça de l'estudi:");
        adreca = DADES.nextLine();
        return new Estudi(nom, adreca);
    }

    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte. En aquest cas
     no es pot modificar el contingut dels vectors, només els dels atributs 
     nom i adreca. Evidentment, tampoc podeu modificar el codi.
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte
     actual, abans de modificar-los.
     Retorn: cap
     */
    public void updateComponent() {

        System.out.println("\nNom de l'estudi: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.nextLine();
        System.out.println("\nAdreça de l'estudi: " + adreca);
        System.out.println("\nEntra la nova adreça:");
        adreca = DADES.next();
    }

    public void showComponent() {
        System.out.println("\nLes dades de l'estudi amb codi " + codi + " són:");
        System.out.println("\nNom: " + nom);
        System.out.println("\nAdreça: " + adreca);
    }

    /*
     DISSENYADOR
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix un nou dissenyador o dissenyadora al vector de dissenyadors de l'estudi actual
     si aquest o aquesta no existeix. Per afegir-lo heu de fer servir el mètode de la classe 
     Dissenyador escaient i per comprovar la seva existència el mètode d'aquesta
     classe que ens ajuda en aquesta tasca.
     - actualitza la posició del vector de dissenyadors si s'afegeix el dissenyador o dissenyadora.
     - mostra el missatge "El dissenyador o dissenyadora ja existeix" si no s'ha afegit el
     dissenyador.
     Retorn: cap
     */
    public void addDissenyador(Dissenyador dissenyador) {

        if (dissenyador == null) {
            dissenyador = Dissenyador.addDissenyador();
        }

        if (selectComponent(1, dissenyador.getNif()) == -1) {
            components.add(dissenyador);
        } else {
            System.out.println("\nEl dissenyador o dissenyadora ja existeix");
        }
    }

    /*
     JARDINER
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix un nou jardiner o jardinera al vector de jardiners de l'estudi actual
     si aquest no existeix. Per afegir-lo heu de fer servir el mètode de la classe 
     Jardiner escaient i per comprovar la seva existència el mètode d'aquesta
     classe que ens ajuda en aquesta tasca.
     - actualitza la posició del vector de jardiners si s'afegeix el jardiner o jardinera.
     - mostra el missatge "El jardiner o jardinera ja existeix" si no s'ha afegit el
     jardiner o jardinera.
     Retorn: cap
     */
    public void addJardiner(Jardiner jardiner) {

        if (jardiner == null) {
            jardiner = Jardiner.addJardiner();
        }

        if (selectComponent(2, jardiner.getNif()) == -1) {
            components.add(jardiner);
        } else {
            System.out.println("\nEl jardiner o jardinera ja existeix");
        }
    }

    /*
     TORN
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix un nou torn al vector de torns de l'estudi actual si aquest no existeix. 
     Per afegir-lo heu de fer servir el mètode de la classe Torn escaient i per comprovar
     la seva existència el mètode d'aquesta classe que ens ajuda en aquesta tasca.
     - actualitza la posició del vector de torns si s'afegeix el torn.
     - mostra el missatge "El torn ja existeix" si no s'ha afegit el torn.
     Retorn: cap
     */
    public void addTorn(Torn torn) {

        if (torn == null) {
            torn = Torn.addTorn();
        }

        if (selectComponent(3, torn.getCodi()) == -1) {
            components.add(torn);
        } else {
            System.out.println("\nEl torn ja existeix");
        }
    }

    /*
     PROJECTE
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix un nou projecte al vector de projectes d'aquest estudi (estudi actual)
     si aquesta no existeix. Per afegir-lo heu de fer  servir el mètode de la classe 
     Projecte pertinent i per comprovar la seva existència el mètode d'aquesta classe 
     que ens ajuda en aquesta tasca.
     - actualitza la posició del vector de projectes si s'afegeix el projecte.
     - mostra el missatge "El projecte ja existeix" si no s'ha afegit el projecte.
     Retorn: cap
     */
    public void addProjecte(Projecte projecte) {

        if (projecte == null) {
            projecte = Projecte.addProjecte();
        }

        if (selectComponent(4, projecte.getCodi()) == -1) {
            components.add(projecte);
        } else {
            System.out.println("\nEl projecte ja existeix");
        }
    }

    public int selectComponent(int tipusComponent, Object id) {

        if (id == null) {
            //Demanem quin tipus de component vol seleccionar i el seu identificador (id)
            switch (tipusComponent) {
                case 1:
                    System.out.println("NIF del dissenyador o dissenyadora?:");
                    break;
                case 2:
                    System.out.println("NIF del jardiner o jardinera?:");
                    break;

                case 3:
                    System.out.println("Codi del torn?:");
                    break;
                case 4:
                    System.out.println("Codi del projecte?:");
                    break;
            }

            id = DADES.next();
        }

        int posElement = -1; //Posició que ocupa el component seleccionat dins el vector de components de l'estudi

        //Seleccionem la posició que ocupa el component dins el vector de components
        // de l'estudi
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) instanceof Dissenyador && tipusComponent == 1) {
                if (((Dissenyador) components.get(i)).getNif().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof Jardiner && tipusComponent == 2) {
                if (((Jardiner) components.get(i)).getNif().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof Torn && tipusComponent == 3) {
                if (((Torn) components.get(i)).getCodi().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof Projecte && tipusComponent == 4) {
                if (((Projecte) components.get(i)).getCodi() == Integer.parseInt((String) id)) {
                    return i;
                }
            }
        }

        return posElement;
    }

    public void addTornJardiner() throws GestorEstudisException {

        Jardiner jardinerSel = null;
        int pos = selectComponent(2, null);

        if (pos >= 0) {

            jardinerSel = (Jardiner) this.getComponents().get(pos);

            pos = selectComponent(3, null);

            if (pos >= 0) {
                jardinerSel.setTorn((Torn) getComponents().get(pos));
            } else {
                throw new GestorEstudisException("5");
            }

        } else {
            throw new GestorEstudisException("6");
        }

    }

    public void addTreballadorProjecte(int tipus) throws GestorEstudisException {
        Projecte projecteSel = null;
        int pos = selectComponent(4, null);

        if (pos >= 0) {

            projecteSel = (Projecte) this.getComponents().get(pos);

            pos = selectComponent(tipus, null);

            if (pos >= 0) {
                if (tipus == 1) {
                    if (projecteSel.getTreballadors().containsKey("dissenyador")) {
                        throw new GestorEstudisException("3");
                    }
                } else { //És jardiner
                    if (projecteSel.getTreballadors().containsKey(((Treballador) getComponents().get(pos)).getNif())) {
                        throw new GestorEstudisException("4");
                    }
                }
                projecteSel.addTreballador((Treballador) getComponents().get(pos));
            } else {
                throw new GestorEstudisException("7");
            }

        } else {
            throw new GestorEstudisException("8");
        }
    }

}
