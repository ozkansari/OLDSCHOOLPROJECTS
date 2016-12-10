/*
 * Created on 06.May.2005
 *
 */

/**
 * PISTI GAME
 * 
 * @author Ozkan SARI
 *	ozkan_yellow@hotmail.com
 *
 */

// Java core packages
import java.net.*;
import java.io.*;

/**  
 *  class Players that manages each player as a thread
 */
public class Players extends Thread {
	
    // Player Attributes
	int pointsGained;
	int playerNumber;
	String playerName;
    Cards currentCard = null;
    Cards hand[] = new Cards [4];
    Cards[] cardsGained = new Cards[52];	
	
	// Connection Variables
    public Socket socket = null;
    
    protected boolean suspended = true;
    
	PrintWriter out = null;
	BufferedReader in = null;
	private static boolean gameOver = false;

	/*  ***********************************************************************************************/
	/*	-- CONSTRUCTOR -------------------------------------------------------------------------------*/
	/*  ***********************************************************************************************/	
	/**  
	 *  The "Players()" constructor method that set up Players thread 
	 * 
	 *  @param Socket socket, @param int number 
	 */
    public Players(Socket socket, int number ) {
    	
    	super("Players");
    	
    	playerNumber = number;   // Mark playerNumber as its distinctive feature
    	
    	this.socket = socket;    // set connection
    	
    	// obtain streams from Socket
	    try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    } // --end-Players-constructor--  
    
    
	/*  ***********************************************************************************************/
	/*	-- THREAD METHODS ----------------------------------------------------------------------------*/
	/*  ***********************************************************************************************/
    
    /**  The "Players.run()" method that controls thread's execution :
     *   It controls the information that is sent to the client and received from the client.
     */
    public void run() 
    {
    	String inputLine, outputLine;
    	
    	if( isHandEmpty() )
    		Server.dealCards(playerNumber );
    	
    	try {
	    	
    		// get playerName
    		playerName = in.readLine();
    		
	    	// - Main Loop Of the Program
	    	while( !gameOver ) {
	    		
		    	// if inputLine is null, break
		    	if ( (inputLine = in.readLine()) == null ) break;
		    	
		    	if( inputLine.equalsIgnoreCase( "Game Over!" )){
		    		sleep(10000); // wait 10 seconds before exit
		    		out.println( "Game Over!" );
		    		gameOver = true;
			    	closeConnection();
		    	}
		    	
		    	
				if( inputLine.length() < 4 ) {
					out.println( "Invalid move, try again" );
					continue;
				}
				
		    	try{
					if( inputLine.substring(0,10).compareTo( "Show Cards" ) == 0 ||
						inputLine.substring(0,10).compareTo( "Show cards" ) == 0 ||
						inputLine.substring(0,10).compareTo( "show cards" ) == 0){
						
						showCardsGained();
						out.println( "input again" );
						continue;
					}
		    	} 
		    	catch( IndexOutOfBoundsException outOfbonds ) {
		    		outOfbonds.getLocalizedMessage();
		    	}
	    		
		    	try {
		    		currentCard = new Cards( processInput(inputLine) );
		    	} catch ( NullPointerException e ) {
		    		e.printStackTrace();
		    		out.println( "Invalid move, try again" );
		    		continue;
		    	}
		    	
		    	if( makeMove( currentCard, playerNumber ) ) {
		    		Server.turnNumber++;
		    	} 
		    	else {
		    		out.println( "Invalid move, try again" );
		    	}
		    	
	    	} // - end while loop
	    	
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException sleepException) {
			sleepException.printStackTrace();
		}
		
    } //-end function Players.run()-
    
    
	/*  ***********************************************************************************************/
	/*	-- CLASS "Players" PUBLIC METHODS ------------------------------------------------------------*/
	/*  ***********************************************************************************************/

    /** The "Players.processInput(String)" method  :
     *  @param String inputLine
     * 	@return Cards
     */
	private Cards processInput( String inputLine )
	{	
		Cards play = new Cards(); ;
		
		// check if the command is valid
		if( inputLine.substring(0,4).compareTo( "Play" ) == 0 &&
			inputLine.substring(0,4).compareTo( "play" ) == 0    ){
			return null;
		}
		
		char cardSuit = inputLine.charAt(6);									// get suit as a char
		int cardValueInt = Character.getNumericValue( inputLine.charAt(5) ) ; 	// get cardValue as int
		char cardValueChar = inputLine.charAt(5);								// get cardValue as char
		
		if( cardValueInt == 1 && 0 == Integer.parseInt( inputLine.charAt(6) + "" ) ) {
			cardSuit = inputLine.charAt(7);
			cardValueInt = 10;
		}
		
		if( cardValueInt >= 0 && cardValueInt <= 10){ 	
			// Ace has the ASCII value 10, which needs to be checked
			if( cardValueChar == 'A' || cardValueChar == 'a' ) { 	
				play.setRank( "A" );
			} else {
				play.setRank( (cardValueInt + "") );
			}
			
		} else {
			
			if( cardValueChar == 'A' || cardValueChar == 'a' ) { // is Ace?
				play.setRank( "A" );
			} else if( cardValueChar == 'K' || cardValueChar == 'k' ){ // is King?
				play.setRank( "K" );
			} else if( cardValueChar == 'Q' || cardValueChar == 'q' ){ // is Queen?
				play.setRank( "Q" );
			} else if( cardValueChar == 'J' || cardValueChar == 'j' ){ // is Jack?
				play.setRank( "J" );
			} else {
				return null;
			}
		
		}
		

		// check the type of the card
		if( cardSuit == 's' || cardSuit == 'S' ){ 		// is Spades?
			play.setSuit( "s" );
		} else if( cardSuit == 'h' || cardSuit == 'H' ){ // is Hearts?
			play.setSuit( "h" );
		} else if( cardSuit == 'd' || cardSuit == 'D' ){ // is Diamonds?
			play.setSuit( "d" );
		} else if( cardSuit == 'c' || cardSuit == 'C' ){ // is Clubs?
			play.setSuit( "c" );
		} else {
			return null;
		}
		
		return play;
		
	} //-end function processInput(String)-
    
    /** The "Players.makeMove()" method that determines if a move is valid :
     *  This method is synchronized because only one move can be made at a time.
     * 
     *  @param Cards cardPlayed , @param int player
     *  @return boolean
     */
    private synchronized boolean makeMove( Cards cardPlayed, int player ) 
    {
        // while not current player, must wait for turn
        while ( player != Server.currentPlayer ) {
           
        	// wait for turn
        	try {
        		synchronized( this ) {
        			wait();
        		}
        	}
        	// catch wait interruptions
        	catch( InterruptedException interruptedException ) {
        		interruptedException.printStackTrace();
        	}
        }
        
        // if card is removed successfully, this means that the move is valid. So, make move. 
        if( removeCardFromHand( cardPlayed ) ) {
        	
        	Cards top ;
        	
    		out.println( "Valid move" );
        	
        	if(Server.topTableCard != null ) {
        		top = new Cards( Server.topTableCard );
        		putCardOnTable( currentCard );
    	    	if( currentCard.hasSameRank( top ) || currentCard.isJack() ) {
    	    		
    	    		if( !currentCard.hasSameRank( top ) ) Server.pistiChance = false; // any card & J is not pisti
    	    		
    	    		winAllCards( Server.pistiChance );
    	    		
    	    	}  
    	    	
    	    	Server.pistiChance = false;
        	} else {
        		putCardOnTable( currentCard );	
        		Server.pistiChance = true;
        	}     	
         	
	    	// change current player
	    	Server.currentPlayer = ( Server.currentPlayer + 1 ) % 4;

	    	// tell waiting player to continue
	    	Server.nextPlayer( currentCard );      
	    	
	    	// tell this player to wait
	    	try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	    	// return true to tell the caller that the move was valid	    	
        	return true;
        }
        else {
        	// return false to tell the caller that the move is not valid
        	return false;
        }
   	
    } //-end function Players.makeMove( Cards , int )


	/*  ***********************************************************************************************/
	/*	-- CLASS "Players" UTILITY METHODS -------------------------------------------------------------------*/
	/*  ***********************************************************************************************/
    
    /** 
     *  @param Cards cardToremove
     *  @return boolean
     */
	private boolean removeCardFromHand( Cards cardToRemove ) 
	{
		for( int i=0; i < 4; i++) {
			if( hand[i] != null && hand[i].isSame( cardToRemove ) ) {
				hand[i] = null;
				return true;
			}
		}
		
		return false;
	} //-end function Players.removeCardFromHand( Cards )
    
    
	/**
	 *  @param Cards currentCard
	 */
	private void putCardOnTable( Cards currentCard ) 
	{
		// put the card to the top of the table
		for(int i=0; i <52 ; i++ ) {
			if( Server.TableCards[i] == null ) {
				Server.TableCards[i] = new Cards( currentCard );
				Server.topTableCard = currentCard;
				break;
			}
		}
		
	} //-end function Players.putCardOnTable( Cards )


	/**
	 *  @param boolean pisti
	 */
	private void winAllCards( boolean pisti ) 
	{
		int topPos = 0;
		
		// find the top position of the cards gained pile
		for( topPos=0; cardsGained[topPos] != null ; topPos++ );
		
		// get all cards on the table
		for(int i=0; Server.TableCards[i] != null ; i++ ) {
			
			cardsGained[ topPos + i] = new Cards( Server.TableCards[i] );
			if( pisti ) cardsGained[ topPos + i].setPisti();
			Server.TableCards[i] = null;
		}
		Server.topTableCard = null;
		
		// send proper message
		if( pisti ){
			Server.sendMessageToAllClients( "> SERVER: " + this.playerName + " made a pisti" );
		} else {
			Server.sendMessageToAllClients( "> SERVER: " + this.playerName + " win all cards" );
		}
		
	} //-end function Players.winAllCards( boolean )-
		
	
	/**
	 *  @return boolean
	 */
	public boolean isHandEmpty() 
	{
		for( int j=0; j<4; j++ ) {
			if( hand[j] != null ) return false ;	
		}
		
		return true;
	} //-end function Players.isHandEmpty():boolean-
    	
	/**
	 * 
	 */
	public void showHand() 
	{  
		out.println("-----------------------------------------------------");
		out.print( " Your Hand: ");
		
		for( int j=0; j<4; j++ ) {
			if( hand[j] != null ){
				out.print( hand[j] + " ," ); 	
			} 
		}
		
		out.println("\n-----------------------------------------------------");
		
	} //-end function Players.showHand()-
	
	
	/**
	 * 
	 */
	public void showCardsGained() 
	{  
		out.println("-----------------------------------------------------");
		out.print( " The Cards you gained: ");
		
		int j;
		for( j=0; cardsGained[j] != null ; j++ ) {
			out.print( cardsGained[j] + " ," ); 
		}
		
		if( j==0 ) out.print( "nothing" );
		
		out.println("\n-----------------------------------------------------");
		
	} //-end function Players.showCardsGained()-
	

	/**
	 *  @param boolean state
	 */
	public void setSuspended(boolean state) 
	{
		suspended = state;
	} //-end function Players.setSuspended(boolean)-
	
	/**
	 * 
	 */
	public void closeConnection() 
	{   
	    try {
	    	out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	} //-end function Players.setSuspended(boolean)-
	
    
} // - end class Players -
