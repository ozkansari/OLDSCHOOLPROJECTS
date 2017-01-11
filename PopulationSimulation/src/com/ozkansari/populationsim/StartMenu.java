package com.ozkansari.populationsim;
/**
 * StartMenu.java
 * 
 */

import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 * @author Ozkan SARI
 *
 * Start Menu Frame to set options for the simulation.
 */
public class StartMenu extends JFrame {

	JTextField gridDimension;
	JCheckBox manualInitCheck, randomInitCheck, singleGenCheck ;
	JLabel gridDimLbl, manualInitLbl, randomInitLbl, singleGenLbl ;
	JButton startBtn;
	
	public StartMenu() {

		MenuHandler handler = new MenuHandler();
		
		// GUI
		JPanel p1 = new JPanel();
		gridDimLbl = new JLabel( "Grid Dimension" ); p1.add( gridDimLbl );
		gridDimension = new JTextField(3); p1.add( gridDimension );
		gridDimension.setText("5");
		
		JPanel p2 = new JPanel();
		manualInitCheck = new JCheckBox("Manual Initilization", true); p2.add(manualInitCheck);
		randomInitCheck = new JCheckBox("Random Initilization", true); p2.add(randomInitCheck);
		singleGenCheck = new JCheckBox("Single Generation");  p2.add(singleGenCheck);
		
		startBtn = new JButton( "START SIMULATION" );
		startBtn.addActionListener( handler );
		
		getContentPane().add(p1,"North");
    	getContentPane().add(p2,"Center");
    	getContentPane().add(startBtn,"South");
		
    	setLocation( 400, 400 );
        pack();
        setVisible( true );
        setResizable(false);
	}
	
	  /** INNER CLASS FOR MENU EVENT HANDLING
	   * 
	   */
		class MenuHandler extends SimulationConstants implements ActionListener {
			
			public void actionPerformed( ActionEvent event ) {
				
				// detect the source of the event generated and perform their respective task		
			    if( event.getSource() == startBtn ){ 
			    	
			    	// set constants
			    	try{ 
			    		setGRID_DIMENSION( Integer.parseInt(gridDimension.getText()) );
			    	} catch( Exception e) {
			    		setGRID_DIMENSION(10);
			    	}
			    	setINITIALIZE_POPULATION_MANUALLY( manualInitCheck.isSelected() );
			    	setRANDOM_INITIALIZE_POPULATION( randomInitCheck.isSelected() );
			    	setSINGLE_STEP_GENERATION( singleGenCheck.isSelected() );
			    	
			    	// start simulation
			    	closeFrame();
			    	SimulationFrame simulate = new SimulationFrame();
		    	}
					
			} //--end- actionPerformed() -function--
			
		} // --end-inner-class- "MenuHandler" --
	
	/**
	 * 
	 */private void closeFrame(){
		 setVisible(false);
	 }


}
