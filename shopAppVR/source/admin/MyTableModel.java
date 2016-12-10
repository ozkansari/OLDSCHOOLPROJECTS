/**
 * 
 */
package admin;

import javax.swing.table.AbstractTableModel;

/**
 * @author ozkansari
 *
 */
class MyTableModel extends AbstractTableModel {
	
    private String[] columnNames;
    private Object[][] data;

    public MyTableModel( String[] columnNames, Object[][] data){
    	super();
    	this.columnNames = columnNames;
    	this.data = data;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

 }
