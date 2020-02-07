package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author FTA
 */
public class MenuPrincipalVista {
    
    private JFrame frame;

    private JButton[] menuButtons = new JButton[3];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuPrincipalVista() {
        
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Menú Principal" i layout Grid d'una columna
            Heu de crear els botons del formulari. Cada botó serà un element de l'array de botons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Menú Estudis"
                        "2. Menú Dissenyador"
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada que proposen les propietats d'aquest nom
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
        */   
        
        //Definición de la ventana del menú principal
        frame = new JFrame("Menú Principal");
        frame.setLayout(new GridLayout(0,1));
        
        //Creamos los botones de la lista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton ("1. Menú Estudis");
        menuButtons[2] = new JButton ("Menú Dissenyador");
        
        //Añadimos los botones recien creados a la ventana "frame"
        for (JButton unBoton : menuButtons){
            frame.add(unBoton);
        }
        
        //Se muestra la ventana con los propiedades por defecto
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }
}
