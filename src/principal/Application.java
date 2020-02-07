package principal;

import model.Estudi;
import model.Projecte;
import model.Dissenyador;
import model.Jardiner;
import model.Torn;
import java.util.InputMismatchException;
import java.util.Scanner;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;

/**
 *
 * @author root
 */
public class Application {

    private final static Scanner DADES = new Scanner(System.in);

    static private Estudi[] estudis = new Estudi[4];
    static private int posicioEstudis = 0;
    static private Estudi estudiActual = null;
    static private String FITXER = "desti";
    static private GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) throws GestorEstudisException {
        try {
            menuPrincipal();
        } catch (GestorEstudisException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestorEstudisException {
        int opcio = 0;

        do {

            try {
                System.out.println("\nSelecciona una opció");
                System.out.println("\n0. Sortir");
                System.out.println("\n1. Gestió d'estudis");
                System.out.println("\n2. Gestió de projectes");
                System.out.println("\n3. Gestió de dissenyadors o dissenyadores");
                System.out.println("\n4. Gestió de jardiners o jardineres");
                System.out.println("\n5. Gestió de torns");
                opcio = DADES.nextInt();

                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        menuEstudis();
                        break;
                    case 2:
                        if (estudiActual != null) {
                            menuProjectes();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                        }
                        break;
                    case 3:
                        if (estudiActual != null) {
                            menuComponents(1);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                        }
                        break;
                    case 4:
                        if (estudiActual != null) {
                            menuComponents(2);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                        }
                        break;
                    case 5:
                        if (estudiActual != null) {
                            menuComponents(3);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                        }
                        break;
                    default:
                        System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                        break;
                }
            } catch (InputMismatchException e) {
                throw new GestorEstudisException("1");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GestorEstudisException("9");
            }
        } while (opcio != 0);
    }

    public static void menuEstudis() throws GestorEstudisException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio = 0;

        do {
            int indexSel = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar estudi");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. Llista d'estudis");
            System.out.println("\n5. Carregar estudi");
            System.out.println("\n6. Desar estudi");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    estudis[posicioEstudis] = Estudi.addEstudi();
                    posicioEstudis++;
                    break;
                case 2:
                    indexSel = selectEstudi();
                    if (indexSel >= 0) {
                        estudiActual = estudis[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquest estudi");
                    }
                    break;
                case 3:
                    indexSel = selectEstudi();
                    if (indexSel >= 0) {
                        estudis[indexSel].updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest estudi");
                    }
                    break;
                case 4:
                    for (int i = 0; i < posicioEstudis; i++) {
                        estudis[i].showComponent();
                    }
                    break;
                case 5: //Carregar estudi
                    posicioEstudis = 0;
                    estudis = new Estudi[1]; //Només podem carregar un estudi
                    gp.carregarEstudi("XML", FITXER);
                    estudis[posicioEstudis] = ((GestorXML)gp.getGestor()).getEstudi();
                    posicioEstudis++;
                    break;
                case 6: //Desar estudi
                    int pos = selectEstudi();
                    if (pos >= 0) {
                        gp.desarEstudi("XML", FITXER, estudis[pos]);
                    } else {
                        System.out.println("\nNo existeix aquest estudi");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    /*
     TODO Heu de desenvolupar el menuDissenyadors amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, l'atribut estudiActual no és null
       
     Opció 0. Sortir -->         Surt del menú i retorna al menú principal
     Opció 1. Alta -->           Crea un dissenyador en l'estudi actual. Penseu que Estudi sap crear dissenyadors o dissenyadores        
     Opció 2. Modificar ->     Permet modificar un dissenyador o dissenyadora que està donat d'alta a l'estudi actual
     (per comprovar l'existència del dissenyador o dissenyadora tenim un mètode en la classe Estudi que ens ajuda)
     Opció 3. Llista de dissenyadors i dissenyadores -> Imprimeix les dades dels dissenyadors i dissenyadores de l'estudi actual
        
     A més, heu de fer una estructura iterativa per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch-case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - definiu una variable opcio de tipus enter
     Nota important: 
     no controlem que l'usuari introdueixi una opció numèrica, ja que això ho farem amb la
     tècnica de les excepcions que veurem més endavant
     */
    public static void menuComponents(int tipus) throws GestorEstudisException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            if (tipus == 2) {
                System.out.println("\n3. Assignar torn");
                System.out.println("\n4. Llista de jardiners i jardineres");
            } else {
                System.out.println("\n3. Llista");
            }

            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            estudiActual.addDissenyador(null);
                            break;
                        case 2:
                            estudiActual.addJardiner(null);
                            break;
                        case 3:
                            estudiActual.addTorn(null);
                            break;
                    }
                    break;
                case 2:
                    int indexSel = estudiActual.selectComponent(tipus, null);
                    if (indexSel >= 0) {
                        estudiActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest component");
                    }
                    break;
                case 3:
                    if (tipus == 2) {
                        estudiActual.addTornJardiner();
                    } else {
                        for (int i = 0; i < estudiActual.getComponents().size(); i++) {
                            if (estudiActual.getComponents().get(i) instanceof Dissenyador && tipus == 1) {
                                estudiActual.getComponents().get(i).showComponent();
                            } else if (estudiActual.getComponents().get(i) instanceof Torn && tipus == 3) {
                                estudiActual.getComponents().get(i).showComponent();
                            }
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < estudiActual.getComponents().size(); i++) {
                        if (estudiActual.getComponents().get(i) instanceof Jardiner) {
                            estudiActual.getComponents().get(i).showComponent();
                        }
                    }
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    /*
     TODO Heu de desenvolupar el menuProjectes amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, l'atribut estudiActual no és null
     
     Opció 0. Sortir -->         Surt del menú i retorna al menú principal
     Opció 1. Alta -->           Crea un projecte en l'estudi actual. Penseu que Estudi sap crear projectes      
     Opció 2. Modificar ->     Permet modificar un projecte que existeix a l'estudi actual
     (per comprovar l'existència del projecte tenim un mètode en la classe Estudi que ens ajuda)
     Opció 3. Assignar dissenyador o dissenyadora ->   Assigna un dissenyador o dissenyadora a un projecte, però penseu que Estudi sap com fer-ho.
     Opció 4. Assignar jardiner o jardinera ->   Assigna un jardiner o jardinera a un projecte, però penseu que Estudi sap com fer-ho.
     Opció 5. Llista de projectes-> Imprimeix les dades dels projectes de l'estudi actual.
        
     A més, heu de fer una estructura iterativa per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch-case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - definiu una variable opcio de tipus enter
     Nota important: 
     no controlem que l'usuari introdueixi una opció numèrica, ja que això ho farem amb la
     tècnica de les excepcions que veurem més endavant
     */
    public static void menuProjectes() throws GestorEstudisException {
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Assignar dissenyador o dissenyadora");
            System.out.println("\n4. Assignar jardiner o jardinera");
            System.out.println("\n5. Llista de projectes");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    estudiActual.addProjecte(null);
                    break;
                case 2:
                    int indexSel = estudiActual.selectComponent(4, null);
                    if (indexSel >= 0) {
                        estudiActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest projecte");
                    }
                    break;
                case 3:
                    estudiActual.addTreballadorProjecte(1);
                    break;
                case 4:
                    estudiActual.addTreballadorProjecte(2);
                    break;
                case 5:
                    for (int i = 0; i < estudiActual.getComponents().size(); i++) {
                        if (estudiActual.getComponents().get(i) instanceof Projecte) {
                            estudiActual.getComponents().get(i).showComponent();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectEstudi() {

        System.out.println("\nCodi de l'estudi?:");
        int codi = DADES.nextInt();

        for (int i = 0; i < posicioEstudis; i++) {
            if (estudis[i].getCodi() == codi) {
                return i;
            }
        }
        return -1;
    }

}
