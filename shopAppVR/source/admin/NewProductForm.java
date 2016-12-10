package admin;

/**
 * @author Özkan SARI
 *
 */

//Java core packages
import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;


/**  DEFINITION OF CLASS "NewProductForm":
 *   
 * 
 */
public class NewProductForm extends JFrame {

	private JLabel Product_id_Lbl = new JLabel( "Product Id: " );
	private JLabel Product_name_Lbl = new JLabel( "Product Name: " );
	private JLabel Amount_Lbl = new JLabel( "Amount: " );
	private JLabel Price_Lbl = new JLabel( "Price: " );
	
	private JTextField Product_id_TxtFld = new JTextField(15);
	private JTextField Product_name_TxtFld = new JTextField(30);
	private JTextField Amount_TxtFld = new JTextField(15);
	private JTextField Price_TxtFld = new JTextField(10);

	private JButton add_Btn = new JButton( "ADD" );
		
	//	-- CONSTRUCTOR -------------------------------------------------------------------------------

	/**
	 * 
	 */
	public NewProductForm() 
	{
		super("shopApp"); // set title of the window

		MenuHandler handler = new MenuHandler(); 

		JFrame.setDefaultLookAndFeelDecorated(true);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(5, 2));
		p1.add(Product_id_Lbl);p1.add(Product_id_TxtFld);
		p1.add(Product_name_Lbl);p1.add(Product_name_TxtFld);
		p1.add(Amount_Lbl);p1.add(Amount_TxtFld);
		p1.add(Price_Lbl);p1.add(Price_TxtFld);

		JPanel p2 = new JPanel();
		add_Btn.addActionListener(handler);
		p2.add(add_Btn);

		getContentPane().add(p1, "North");
		getContentPane().add(p2, "Center");
		
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
	} // --end-NewProductForm-constructor--
		
	
	/**
	 * Validate entries
	 * 
	 * @return if inputs are correct
	 */
	private boolean validateInput( )
	{
            /* Check for empty fields */
            if(
                    Product_id_TxtFld.getText().isEmpty() 	||
                    Product_name_TxtFld.getText().isEmpty() 	||
                    Amount_TxtFld.getText().isEmpty() 		||
                    Price_TxtFld.getText().isEmpty()
               ) {
                
                JOptionPane.showMessageDialog( NewProductForm.this,
                        "An entry cannot be empty. Please check your input.",
                        "Error", JOptionPane.WARNING_MESSAGE );
                return false;
            }
            
            
            return true;

	} // --end-validateInput-method--		
		
	
	/**
	 * Gets input from corresponding fields 
	 * and return them as ProductsType object
	 * 
	 * @return
	 */
	private ProductsType getInput() 
	{
		ProductsType newInput = new ProductsType();
		
		newInput.setProduct_id(Product_id_TxtFld.getText().trim());
		newInput.setProduct_name(Product_name_TxtFld.getText().trim());
		newInput.setAmount( Integer.parseInt( Amount_TxtFld.getText().trim() ) );
		newInput.setPrice( Float.parseFloat( Price_TxtFld.getText().trim() ) );

		return newInput;
		
	} // --end-getInput-method--	
	
	
    /**
	 * INNER CLASS FOR MENU EVENT HANDLING
	 * 
	 */
	class MenuHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// detect the source of the event generated and perform their
			// respective task

			// if 'ADD NEW' is selected
			if (event.getSource() == add_Btn) {
				
				if( validateInput() == false ) {
					return;
				}
				
				// create database connection
				db.DatabaseConn dbConnection = new db.DatabaseConn();  
				db.ProductsDB productDB = new db.ProductsDB( dbConnection.getConnection() );
				
				// insert new product to database
				ProductsType newProduct = getInput();
				if( productDB.insertNewProduct(newProduct) == false ) {
		            JOptionPane.showMessageDialog( NewProductForm.this,
				                  "Product entry already exists. Please choose a different product id.",
				                  "Error", JOptionPane.WARNING_MESSAGE );
					return;
				}
				
				// close connection
				dbConnection.closeConnection();
				
				// close
				setVisible(false);
			}

		} // --end- actionPerformed() -function--


	} // --end-inner-class- "MenuHandler" --
		
		
} // --end-class-"NewProductForm"--






