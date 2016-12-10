/**
 * SimulationFrame.java
 */

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * @author Ozkan SARI
 *
 * Simulation Frame that includes simulation panel
 * 
 */
public class SimulationFrame extends JFrame {
	
	SimulationPanel boardPanel;
	Generation generationRef;
	
	JMenu fileMenu;
	JMenu helpMenu;
	JMenuItem startItem;
	JMenuItem loadItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenuItem helpItem;
	JMenuItem aboutItem;
	
	JButton nextButton; 

	public SimulationFrame() {
		
		// create simulation panel
		boardPanel = new SimulationPanel();

		// create an instance of inner action handler class
		MenuHandler handler = new MenuHandler();

		// set up the window ////////////////////////////////////////
		setBackground(Color.black);
		setSize(new Dimension(650, 700));
		//setSize( boardPanel.getSize() );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	    setDefaultLookAndFeelDecorated(true);

		//// MENUBAR ITEMS //////////////////////////////////////////

		// set up File main menu and its menu items
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		// set up File->Start sub menu item
		startItem = new JMenuItem("Start");
		startItem.setMnemonic('S');
		startItem.addActionListener(handler);
		fileMenu.add(startItem);
		
		// set up File->Load sub menu item
		loadItem = new JMenuItem("Load");
		loadItem.setMnemonic('L');
		loadItem.addActionListener(handler);
		fileMenu.add(loadItem);
		
		// set up File->Save sub menu item
		saveItem = new JMenuItem("Save");
		saveItem.setMnemonic('S');
		saveItem.addActionListener(handler);
		fileMenu.add(saveItem);
		
		// set up File->Exit sub menu item
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		exitItem.addActionListener(handler);
		fileMenu.add(exitItem);
		
		// set up Help main menu and its menu items
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		// set up Help->About... sub menu item
		aboutItem = new JMenuItem("About...");
		aboutItem.setMnemonic('A');
		aboutItem.addActionListener(handler);
		helpMenu.add(aboutItem);
		
		// set up Help->Help Content sub menu item
		helpItem = new JMenuItem("Help Content");
		helpItem.setMnemonic('C');
		helpItem.addActionListener(handler);
		helpMenu.add(helpItem);
		
		// create menu bar and attach it to MenuTest window
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);
		bar.add(helpMenu);

		// add frame elements ///////////////////////////////////////
		nextButton = new JButton("CREATE NEXT GENERATION");
		nextButton.addActionListener(handler);
		nextButton.setVisible(false); // nextButton is not used

		// set layout of the frame ////////////////////////////////// 
		setLayout(new BorderLayout());
		add(nextButton, BorderLayout.SOUTH);
		add(boardPanel, BorderLayout.CENTER);

		// show frame ///////////////////////////////////////////////
		setVisible(true);

		// start simulation /////////////////////////////////////////
		generationRef = boardPanel.StartSimulation();
		
		// schedule next generation /////////////////////////////////
		int delay = 5000;   // first delay for 5 sec.
		if( SimulationConstants.INITIALIZE_POPULATION_MANUALLY )
			delay= 10000;
	    scheduleSimulation( delay );
		
	}

	/**
	 * 
	 */
	private void scheduleSimulation( int delay ) {
	    int period = 1500;  // repeat every sec.
	    Timer timer = new Timer();
	    
	    timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if( boardPanel.nextGeneration() )
					this.cancel();
			}
		}, delay, period);
	}

	/**
	 * INNER CLASS FOR MENU EVENT HANDLING
	 * 
	 */
	class MenuHandler implements ActionListener {

		final JFileChooser fc = new JFileChooser();
		
		public void actionPerformed(ActionEvent event) {

		    // if 'CREATE NEXT GENERATION' is selected
		    if( event.getSource() ==  nextButton ){ 
		    	
		    	if( SimulationConstants.SINGLE_STEP_GENERATION && !SimulationConstants.firstGeneration ) {
		    		nextButton.setVisible(false);	
		    	}
		    	
		    	boardPanel.nextGeneration();
		    }
	
		    // if 'START' is selected
		    if( event.getSource() ==  startItem ){ 
		    	//nextButton.setVisible(true);
		    	SimulationConstants.setFirstGeneration(true);
		    	boardPanel.getRegion().clearBoard();
		    	generationRef.initPopulationRandomly();
		    	boardPanel.repaint();
			    scheduleSimulation( 5000 );
		    }
		    
		    // if 'LOAD' is selected
		    if( event.getSource() ==  loadItem ){ 
		    	int returnVal = fc.showOpenDialog(SimulationFrame.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File loadFile = fc.getSelectedFile();
		            generationRef.loadSimulationFromFile( loadFile );
		            SimulationConstants.setFirstGeneration(true);
		            boardPanel.repaint();
		        } else {
		        	// Open command cancelled by user
		        }
		    }
		    
		    // if 'SAVE' is selected
		    if( event.getSource() ==  saveItem ){ 
	            int returnVal = fc.showSaveDialog(SimulationFrame.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File saveFile = fc.getSelectedFile();
	                generationRef.saveSimulationToFile( saveFile );
	            } else {
	            	// Save command cancelled by user
	            }
		    }
			
		    // if 'ABOUT' is selected
		    if( event.getSource() ==  aboutItem ){ 
	               JOptionPane.showMessageDialog( SimulationFrame.this,
		                  "This program is created by Özkan SARI\n ozkan_yellow@hotmail.com",
		                  "About", JOptionPane.PLAIN_MESSAGE );
		    }
		    
		    // if 'HELP CONTENT' is selected
		    if( event.getSource() ==  helpItem ){
		    	
	               JOptionPane.showMessageDialog( SimulationFrame.this,
	            		   SimulationConstants.helpString, "Help", JOptionPane.PLAIN_MESSAGE );
		  
		    }
		    
		    // if 'EXIT' is selected
		    if( event.getSource() ==  exitItem  ){ 
				System.exit(0);
		    }

		} //--end- actionPerformed() -function--

	} // --end-inner-class- "MenuHandler" --
}
