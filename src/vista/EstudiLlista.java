package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author FTA
 */
public class EstudiLlista {
    
    private JFrame frame;
    
    private final int AMPLADA = 600;
    private final int ALCADA = 200;
    
    private JTable tEstudi;

    private JButton bSortir;   
    

    public EstudiLlista() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Llista d'Estudis" i layout Grid d'una columna
            Heu de crear la taula amb un nou objecte EstudiTableModel com a model
            Heu de crear el botó del formulari
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada que proposen les propietats d'aquest nom
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra        
        */ 
        
        //Definimos la ventana del menú
        frame = new JFrame("Llista d´Estudis");
        frame.setLayout(new GridLayout(0,1));
        
        //Creamos la tabla con un objeto EstudiTableModel como el modelo
        tEstudi = new JTable(new EstudiTableModel());
        
        //Creamos los botones del formulario
        bSortir = new JButton("Sortir");
        
        frame.add(new JScrollPane(tEstudi));
        frame.add(bSortir);
        
        //Se muestra la ventana con propiedades por defecto
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

    public JTable gettEstudi() {
        return tEstudi;
    }

    public void settEstudi(JTable tEstudi) {
        this.tEstudi = tEstudi;
    }       
    
    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }
}
