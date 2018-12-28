/*
 * Table model for material listing
 */
package views;

import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import core.ProgramException;
import core.persistence.Persistent;
import main.Main;

/**
 *
 * @author Ricard P. Barnes
 */
public class TableModel extends AbstractTableModel {

    private final String[] columnNames;
    private String[][] data;

    public TableModel(Persistent object, String[] columns) throws ProgramException, InstantiationException, IllegalAccessException {
        this.columnNames = columns;
        List<Persistent> list = Main.getController().getPersistence().getAll(object);
        Iterator<Persistent> iterator;
        if (list != null) {
            iterator = list.iterator();             
            data = new String[list.size()][object.getAttributeNumber()];
            for (int i = 0; i < list.size(); i++) {
                Persistent currentObject = iterator.next();
                for (int j = 0; j < currentObject.getAttributeNumber(); j++) {
                    data[i][j] = String.valueOf(currentObject.getAttribute(j));
                }
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

}
