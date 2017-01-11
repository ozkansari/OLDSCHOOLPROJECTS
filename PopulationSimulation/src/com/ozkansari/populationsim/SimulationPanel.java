package com.ozkansari.populationsim;
/**
 * SimulationPanel.java
 * 
 * 
 */

import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Ozkan SARI
 *
 * Simulation Panel where simulation is displayed
 */
public class SimulationPanel extends JPanel implements MouseListener
{
    Board region, nextRegion;
    Generation generation;
    
    public SimulationPanel() 
    {
        // register the mouse event listener
        addMouseListener( this );
	}
    
    public Generation StartSimulation() 
    {
		// calculate some values tgo create region
		int n = SimulationConstants.GRID_DIMENSION;
        int cell_size = SimulationConstants.setCellSize(getHeight()/n) ;
        int x0 = SimulationConstants.setX0_screen( getWidth() - getWidth() );
        int y0 = SimulationConstants.setY0_screen( getHeight() - getHeight() );
        
        // create region
        region = new Board( x0, y0, getWidth(), getHeight(), cell_size );
		
		// start generation
        generation = new Generation( region );
        if( SimulationConstants.RANDOM_INITIALIZE_POPULATION )
        	generation.initPopulationRandomly();
        
        // first paint
        repaint();
        
        return generation;
	}

	/**
	 * 
	 */
	public boolean nextGeneration() {
		boolean gameOver;
		
		if( SimulationConstants.SINGLE_STEP_GENERATION && !SimulationConstants.firstGeneration ){
            JOptionPane.showMessageDialog( SimulationPanel.this,
	                  "Single Step Generation is completed",
	                  "Completed Task", JOptionPane.INFORMATION_MESSAGE );
            repaint();
            return true ;
			// this.setVisible(false);
		}
		
		// if new generation is same as the old one stop
		gameOver = generation.createNextGeneration( );
		
		if( gameOver ) {
            JOptionPane.showMessageDialog( SimulationPanel.this,
	                  "Simulation Completed",
	                  "Completed Task", JOptionPane.INFORMATION_MESSAGE );
			// this.setVisible(false);
			// return;
		}
		
		// first generation is create
		SimulationConstants.setFirstGeneration(false);
		
		// paint the contents of the window
		repaint();
		
		return gameOver;
	}


	public void paint( Graphics g ) 
	{  	
        region.drawBoard(g);
        region.drawPopulation(g);
    }
	
	/*
	 * 
	 */
	public Board getRegion() {
		return region;
	}   
	
    // MOUSE LISTENERS (mouse event handlers) -------------------------------------- //
	
    public void mouseEntered( MouseEvent e ) {
      // called when the pointer enters the frame's rectangular area
    }
   
    public void mouseExited( MouseEvent e ) {
      // called when the pointer leaves the frame's rectangular area
    }
  
    public void mouseClicked( MouseEvent e ) {
      // called after a press and release of a mouse button
      // with no motion in between
      // (If the user presses, drags, and then releases, there will be
      // no click event generated.)

    	if( SimulationConstants.INITIALIZE_POPULATION_MANUALLY ) {
    		Generation.initPopulationManually(e);
    		repaint();
    	}
   }
   
   public void mousePressed( MouseEvent e ) {  
       // called after a button is pressed down
   }
   
   public void mouseReleased( MouseEvent e ) {  
       // called after a button is released
   }
	
}
