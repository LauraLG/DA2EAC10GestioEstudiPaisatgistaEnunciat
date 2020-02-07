package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Estudi;

/**
 *
 * @author FTA
 */
public class EstudiTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"Codi", "Nom", "Adreca"};

    String[][] data = new String[ControladorPrincipal.getMAXESTUDIS()][3];

    public EstudiTableModel() {
        int i = 0;
        for (Estudi estudi : ControladorPrincipal.getEstudis()) {
            if (estudi != null) {
                data[i][0] = String.valueOf(estudi.getCodi());
                data[i][1] = estudi.getNom();
                data[i][2] = estudi.getAdreca();
                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }
    
}
