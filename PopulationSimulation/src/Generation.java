/**
 * Generation.java
 */

import java.awt.event.MouseEvent;
import java.io.*;

/**
 * @author Ozkan SARI
 *
 * sets initial population manually or randomly, 
 * generates next generation,
 * save and load generation from file
 *  
 */
public class Generation {

	private static Board boardRef;
	int n_dim = SimulationConstants.GRID_DIMENSION;
	
	/**
	 * @param boardRef
	 */
	public Generation( Board board) {
		boardRef = board;
	}	

	public static void initPopulationManually(MouseEvent e){
		
	       int mx = e.getX(), my = e.getY();
	       int move_i, move_j ;
	       
	       int x0 = SimulationConstants.getX0_screen();
	       int y0 = SimulationConstants.getY0_screen();
	       int cell_size = SimulationConstants.getCellSize();
	       
	       switch(e.getButton()){
	          case MouseEvent.BUTTON1:
	              move_i = (int) ( (my-y0) / cell_size );
	              move_j = (int) ( (mx-x0) / cell_size );

	              boardRef.setSpecificCell( move_i, move_j, false );
	              
	              break;
	              
	          case MouseEvent.BUTTON3:
	              break;
	                
	           case MouseEvent.BUTTON2:
	        	   break;
	        	   
	           default:
	               break;
	       }
	}
	
	public void initPopulationRandomly()
	{
		int numberOfPeople = n_dim* (int) n_dim/8 ;
		
		for( int i=0; i<numberOfPeople; i++ ){
			int rand_i = (int) (Math.random()*n_dim );
			int rand_j = (int) (Math.random()*n_dim );
			
			boardRef.setSpecificCell( rand_i, rand_j, false );
			
		}
	}
	
	/**
	 * Determines and set new board values for the next generation. 
	 * 
	 * @return true if no new generation is created
	 */
	public boolean createNextGeneration( )
	{
		int neighbourCount = 0;
		boolean noChange = true; // if no change in the board
		boolean emptyCell;
		
		Cell [][] boardValues = boardRef.getBoard();
		Cell [][] newBoard = new Cell [n_dim][n_dim];
		boardRef.initBoard(newBoard);
		
		for(int i=0 ; i<n_dim ; i++ ){
			for(int j=0 ; j<n_dim ;j++){
				
				emptyCell = boardValues[i][j].isEmptyCell();

				// zero neighbour count
				neighbourCount = 0;

				// count neighbours
				for( int k=-1; k<2; k++ ) {

					if( (i+k >=0) && (i+k < n_dim) ) {
						if( boardValues[i+k][j].isEmptyCell() == false ) 
							neighbourCount++;
					}

					if( (j-1>=0) && (i+k >=0) && (i+k < n_dim) ) {
						if( boardValues[i+k][j-1].isEmptyCell() == false ) 
							neighbourCount++;
					}

					if( (j+1 < n_dim) && (i+k >=0) && (i+k < n_dim) ) {
						if( boardValues[i+k][j+1].isEmptyCell() == false ) 
							neighbourCount++;
					}
				}

				// remove itself from neighbour count
				if( emptyCell == false )
					neighbourCount--;

				// generation rule
				if( neighbourCount == 2 || neighbourCount == 3 ){
					
					if( emptyCell ) {
						newBoard[i][j].setEmptyCell(false);
					  	noChange = false;
					}
					
				} else {
					if(emptyCell == false ) {
						newBoard[i][j].setEmptyCell(true);
					  	noChange = false;
					}
					
				}


			} // -end-inner-for-

		} // -end-outer-for
        
        // set new board for the next generation 
        if( noChange == false )
        	boardRef.copyBoard( newBoard );
        
        return noChange;
        
	} // -end-createNextGeneration()-method

	public void loadSimulationFromFile(File loadFile) 
	{
    	// Stream to read file
		FileInputStream fin;		
		DataInputStream data;
		
		try
		{
		    // Open an input stream
		    fin = new FileInputStream ( loadFile );
		    data = new DataInputStream(fin);
		    
	        for(int i=0 ; i<n_dim ; i++ ){
	            for(int j=0 ; j<n_dim ;j++){
	            	
	                boardRef.setSpecificCell(i, j,  
	                		data.readBoolean()
	                	);
	                	                
	            } // -end-inner-for-
	        } // -end-outer-for-

		    // Close our input stream
		    fin.close();		
		}
		catch (IOException e)
		{
			System.err.println ("Unable to read from file");
		}
		
	}

	public void saveSimulationToFile(File saveFile) {
		
		Cell [][] boardValues = boardRef.getBoard();
		
		// Stream to write file
		FileOutputStream fout;		
		DataOutputStream data ;
		try
		{
		    // Open an output stream
		    fout = new FileOutputStream ( saveFile );
		    data = new DataOutputStream(fout);
		    
	        for(int i=0 ; i<n_dim ; i++ ){
	            for(int j=0 ; j<n_dim ;j++){
	            	
	            	data.writeBoolean(
	            			boardRef.getSpecificCell(i, j) 
	            		);
	                
	            }
	        }

		    // Close our output stream
		    fout.close();		
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("Unable to write to file");
			System.exit(-1);
		}
		
		

	} //-end-saveSimulationToFile-method-


}
