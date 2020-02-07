package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author FTA
 */
public class MenuEstudiVista {

    private JFrame frame;

    private JButton[] menuButtons = new JButton[7];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuEstudiVista() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Menú Estudi" i layout Grid d'una columna
            Heu de crear els botons del formulari. Cada botó serà un element de l'array de botons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Alta Estudi"
                        "2. Seleccionar Estudi"
                        "3. Modificar Estudi"
                        "4. LListar Estudis"
                        "5. Carregar Estudi"
                        "6. Desar Estudi"
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada que proposen les propietats d'aquest nom
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
         */
        
        frame = new JFrame("Menú Estudi");
        frame.setLayout(new GridLayout(0,1));
        
        //Creación de los botones de la lista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta Estudi");
        menuButtons[2] = new JButton("2. Seleccionar Estudi");
        menuButtons[3] = new JButton("3. Modificar Estudi");
        menuButtons[4] = new JButton("4. LListar Estudis");
        menuButtons[5] = new JButton("5. Carregar Estudi");
        menuButtons[6] = new JButton("6. Desar Estudi");
        
       //Añadimos los botones a la ventana
       for (JButton unBoton : menuButtons){
           frame.add(unBoton);
       }
       
       //Se muestra la ventana con propiedades por defecto
       frame.setSize(AMPLADA,ALCADA);
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
