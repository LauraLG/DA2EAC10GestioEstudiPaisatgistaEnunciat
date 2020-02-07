package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Estudi;
import persistencia.GestorPersistencia;
import vista.MenuPrincipalVista;

/**
 *
 * @author FTA
 */
public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipalVista menuPrincipalVista;
    static private final int MAXESTUDIS = 4;
    static private Estudi[] estudis = new Estudi[MAXESTUDIS];
    static private int posicioEstudis = 0;
    static private Estudi estudiActual = null;
    static private int tipusElement = 0;
    static private GestorPersistencia gp = new GestorPersistencia();
    //FUTUR static private final String[] METODESPERSISTENCIA = {"XML","Serial","JDBC","DB4O"}; 
    static private final String[] METODESPERSISTENCIA = {"XML", "Serial"};

    public ControladorPrincipal() {
        /*
        TODO
        
        S'inicialitza la propietat menuPrincipalVista (això mostrarà el menú principal)
        A cada botó del menú, s'afegeix aquest mateix objecte (ControladorPrincipal) com a listener
        
         */
        menuPrincipalVista = new MenuPrincipalVista();
        
        //Añadimos el controlador como el listener de los botones
        for (JButton unBoton : menuPrincipalVista.getMenuButtons()){
            unBoton.addActionListener(this);
        }       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO

        S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es 
        correspon amb la posició que el botó ocupa a l'array de botons de menuPrincipalVista
        
         */
        
        JButton[] botons = menuPrincipalVista.getMenuButtons();
        for (int i = 0; i < botons.length; i++) {
            if (e.getSource()== botons[i]){
                bifurcaOpcio(i);
            }
        }
    }

    private void bifurcaOpcio(int opcio) {

        switch (opcio) {
            case 0:
                System.exit(0);
                break;
            case 1:
                menuPrincipalVista.getFrame().setVisible(false);
                ControladorEstudi controladorEstudi = new ControladorEstudi();
                break;
            case 2:
                JOptionPane.showMessageDialog(menuPrincipalVista.getFrame(), "No heu d'implementar aquest menú");
                break;
        }

    }

    public static MenuPrincipalVista getMenuPrincipalVista() {
        return menuPrincipalVista;
    }

    public static void setMenuPrincipalVista(MenuPrincipalVista menuPrincipalVista) {
        ControladorPrincipal.menuPrincipalVista = menuPrincipalVista;
    }

    public static Estudi[] getEstudis() {
        return estudis;
    }

    public static void setEstudis(Estudi[] estudis) {
        ControladorPrincipal.estudis = estudis;
    }

    public static int getPosicioEstudis() {
        return posicioEstudis;
    }

    public static void setPosicioEstudis() {
        ControladorPrincipal.posicioEstudis++;
    }

    public static Estudi getEstudiActual() {
        return estudiActual;
    }

    public static void setEstudiActual(Estudi estudiActual) {
        ControladorPrincipal.estudiActual = estudiActual;
    }

    public static int getTipusElement() {
        return tipusElement;
    }

    public static void setTipusElement(int tipusElement) {
        ControladorPrincipal.tipusElement = tipusElement;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }

    public static int getMAXESTUDIS() {
        return MAXESTUDIS;
    }

    public static String[] getMETODESPERSISTENCIA() {
        return METODESPERSISTENCIA;
    }

}