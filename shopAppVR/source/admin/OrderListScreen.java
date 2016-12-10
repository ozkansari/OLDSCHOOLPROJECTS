/**
 * 
 */

package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * @author ozkansari
 *
 */
public class OrderListScreen extends JFrame {
	
	JButton newProduct_Btn;
	JTable table;
	
	public OrderListScreen(){
		
		super("shopApp>Product Inventory"); // set title of the window
		
		MenuHandler handler = new MenuHandler(); 

		JFrame.setDefaultLookAndFeelDecorated(true);
		
		// create database connection
		db.DatabaseConn dbConnection = new db.DatabaseConn();  
		db.OrdersDB orderDB = new db.OrdersDB( dbConnection.getConnection() );
		
		// Get table data
		Object[][] data = orderDB.fetchTableData();
		String[] columnNames = { "CLIENT ID", "PRODUCT", "ORDER DATE" };
			
		// Create table		
		table = new JTable( new MyTableModel(columnNames, data) );
		table.setPreferredScrollableViewportSize(new Dimension(500, 700));
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		JScrollPane tableScrollPane = new JScrollPane(table);
		
		getContentPane().add(tableScrollPane, "Center");
		
		// set the position in the screen
		int inset = 250;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height
				- inset * 2);
		
		// set window properties
		setSize(300, 200);
		setVisible(true);
		setResizable(false);
		setAlwaysOnTop(true);
	}

	
	
    /**
	 * INNER CLASS FOR MENU EVENT HANDLING
	 * 
	 */
	class MenuHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// detect the source of the event generated and perform their
			// respective task

			// if '' is selected
			if (event.getSource() == "") {
				
			}

		} // --end- actionPerformed() -function--


	} // --end-inner-class- "MenuHandler" --
	
}
