package admin;

import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;


/**  DEFINITION OF CLASS "NewProductForm":
 *   
 * 
 */
public class NewCustomerForm extends JFrame {

	private JLabel Client_id_Lbl = new JLabel( "Client Id: " );
	private JLabel Name_Lbl = new JLabel( "Name: " );
	private JLabel Surname_Lbl = new JLabel( "Surname: " );
	private JLabel Address_Lbl = new JLabel( "Address: " );
	private JLabel Telephone_Lbl = new JLabel( "Telephone: " );
	
	private JTextField Client_id_TxtFld = new JTextField(15);
	private JTextField Name_TxtFld = new JTextField(30);
	private JTextField Surname_TxtFld = new JTextField(30);
	private JTextField Address_TxtFld = new JTextField(50);
	private JTextField Telephone_TxtFld = new JTextField(30);
	
	private JButton signIn_Btn = new JButton( "SIGN IN" );
		
	//	-- CONSTRUCTOR -------------------------------------------------------------------------------

	/**
	 * 
	 */
	public NewCustomerForm() 
	{
		super("shopApp"); // set title of the window

		MenuHandler handler = new MenuHandler(); 

		JFrame.setDefaultLookAndFeelDecorated(true);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(5, 2));
		p1.add(Client_id_Lbl);p1.add(Client_id_TxtFld);
		p1.add(Name_Lbl);p1.add(Name_TxtFld);
		p1.add(Surname_Lbl);p1.add(Surname_TxtFld);
		p1.add(Address_Lbl);p1.add(Address_TxtFld);
		p1.add(Telephone_Lbl);p1.add(Telephone_TxtFld);

		JPanel p2 = new JPanel();
		signIn_Btn.addActionListener(handler);
		p2.add(signIn_Btn);

		getContentPane().add(p1, "North");
		getContentPane().add(p2, "Center");
		
		// set the position in the screen
		int inset = 250;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

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
				Client_id_TxtFld.getText().isEmpty() 	||
				Name_TxtFld.getText().isEmpty() 		||
				Surname_TxtFld.getText().isEmpty() 		||
				Telephone_TxtFld.getText().isEmpty() 	||
				Address_TxtFld.getText().isEmpty() 
		) {
            JOptionPane.showMessageDialog( NewCustomerForm.this,
	                  "An entry cannot be empty. Please check your input.",
	                  "Error", JOptionPane.WARNING_MESSAGE );
			return false;
		}

		return true;
	
	} // --end-validateInput-method--		
		
	
	/**
	 * Gets input from corresponding fields 
	 * and return them as ClientsType object
	 * 
	 * @return
	 */
	private ClientsType getInput() 
	{
		ClientsType newInput = new ClientsType();
		
		newInput.setClient_id(Client_id_TxtFld.getText().trim());
		newInput.setName(Name_TxtFld.getText().trim());
		newInput.setSurname(Surname_TxtFld.getText().trim());
		newInput.setTelephone(Telephone_TxtFld.getText().trim());
		newInput.setAddress(Address_TxtFld.getText().trim());

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

			// if 'SIGN IN' is selected
			if (event.getSource() == signIn_Btn) {
				
				if( validateInput() == false ) {
					return;
				}
				
				// create database connection
				db.DatabaseConn dbConnection = new db.DatabaseConn();  
				db.ClientsDB clientDB = new db.ClientsDB( dbConnection.getConnection() );
				
				// insert new client to database
				ClientsType newClient = getInput();
				if( clientDB.insertNewClient(newClient) == false ) {
		            JOptionPane.showMessageDialog( NewCustomerForm.this,
				                  "Client entry already exists. Please choose a different client id.",
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






