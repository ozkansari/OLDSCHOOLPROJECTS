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
import javax.swing.*;

public class Server {
	
	private ServerSocket server;
	private Socket clientSocket = null;
	
	protected static Cards[] deckOf52;						// deck of 52 cards
	protected static Cards[] TableCards = new Cards[52];	// the table cards
	protected static Cards topTableCard ;					// the card on the top of the table cards
	
	protected static Players player[] = new Players[4];		// the 4 players on the game
	protected static int numOfPlayers;						// the number of players connected
	protected static int currentPlayer ;					// current player playing
	
	protected static int turnNumber = 1;
	protected static boolean firstTurn = true;
	protected static boolean pistiChance = false;

	/*  ***********************************************************************************************/
	/*	-- CONSTRUCTOR -------------------------------------------------------------------------------*/
	/*  ***********************************************************************************************/
	
	/**
	 * @param String portNumber
	 */
	public Server( String portNumber ) 
	{
		numOfPlayers = 0; 
		currentPlayer = 0;
		deckOf52 = Cards.getRegularDeck( deckOf52 );
		int serverPort, portNo;
	    
		// set portNumber
		serverPort = Integer.parseInt( portNumber );
		
	    // set up ServerSocket
		try {
			server = new ServerSocket( serverPort );
		}
		catch( IOException ioException ) {
	      	ioException.printStackTrace();
	      	System.exit( 1 );
		}
		
		System.out.println("java PistiServer " + serverPort );
		System.out.println( "PistiServer started successfully... waiting for clients." );
		
      	while( numOfPlayers != 4 )  {

      		try {
      			player[numOfPlayers] = new Players(server.accept(), numOfPlayers);
      			player[numOfPlayers].start();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit( 1 );
			}
			numOfPlayers++;
			
      		sendMessageToAllClients( "> SERVER: Listening for " + (4 - numOfPlayers) + " more client(s)" );
      		System.out.println( "> Listening for " + (4 - numOfPlayers) + " more client(s)" );
      		
			currentPlayer++;
      	}
      	
      	sendMessageToAllClients("> SERVER: All four players connected. Let the game begins!");
      	System.out.println( "> All four players connected. Let the game begins!" );
      	
      	Server.putTableCards();
      	
      	sendMessageToAllClients( "> SERVER: The 4th card on tbe table is: " +  topTableCard );
      	System.out.println( "> The 4th card on tbe table is: " +  topTableCard );
      		
        // Player 0 is suspended until other players connects. Resume it now.    
     	synchronized ( player[ 0 ] ) {
      		player[ 0 ].setSuspended( false );   
      		player[ 0 ].notify();
      		sendMessageToAllClients( "> SERVER: waiting for player " +  player[0].playerName + " to play");
      		player[0].out.println( "Your Turn" );
			currentPlayer = 0;
      	}

	} // --end-Server-constructor--

	/** 
	 *  @param int playerNo
	 */
	protected static void dealCards( int playerNo ) 
	{
		int randomIndex; 
		
		if( turnNumber > 40 ) {
			System.out.println("GAME OVER!!!!! ");
			calculatePoints();
			sendMessageToAllClients( "Game Over!" );
		}
		
		if( playerNo == 4 )
			sendMessageToAllClients( "> SERVER: TURN-" + (turnNumber %4) + " STARTS" );
		
		player[playerNo].out.print( "> SERVER: your hand is: " );
		System.out.print( " HAND #" + playerNo + " : ");
		
		for( int j=0; j<4; j++ ) {
			
			int numOftry = 0;
			
			// take a random Index
			do {
				randomIndex = (int) ( Math.random() * 51 );
				
				// if so much try
				if( ++numOftry >= 10 ){
					randomIndex = 0;
					while( deckOf52[randomIndex] == null )
						randomIndex++;
				}
				
			} while(deckOf52[randomIndex] == null);
			
			// take the random card from deck
			player[playerNo].hand[j] = new Cards( deckOf52[randomIndex]);
			
			// print the card
			player[playerNo].out.print( ", " + deckOf52[randomIndex]  );
			System.out.print( ", " + deckOf52[randomIndex] );
			
			// remove the card from deck
			deckOf52[randomIndex] = null;
		}
		
		player[playerNo].out.print( "\n" );
		System.out.print("\n");
	
	} //-end function Server.dealCards( int )
	
	
	/**
	 *  
	 */
	private static void calculatePoints() 
	{
			
		for( int i=0; i<4 ; i++ ) {
			
			player[i].pointsGained = 0;
			
			for( int j=0; player[i].cardsGained[j] != null ; j++ ) {
				
				if( player[i].cardsGained[j].isPisti() ){
					player[i].pointsGained += 10; //  2 cards that make pisti become 20 points
				} else if( player[i].cardsGained[j].isAce() ){
					player[i].pointsGained += 10;
				} else if( player[i].cardsGained[j].isSame( new Cards( "10", "d")) ){
					player[i].pointsGained += 10;
				} else if( player[i].cardsGained[j].isSame( new Cards( "2", "s")) ){
					player[i].pointsGained += 3;
				} else {
					player[i].pointsGained += 1;
				} 	
			}			
		}
		
		// Display points
		sendMessageToAllClients( "-----------------------------------------------------" );
		sendMessageToAllClients( " PLAYER #1: " + player[0].pointsGained );
		sendMessageToAllClients( " PLAYER #2: " + player[1].pointsGained );
		sendMessageToAllClients( " PLAYER #3: " + player[2].pointsGained );
		sendMessageToAllClients( " PLAYER #4: " + player[3].pointsGained );
		sendMessageToAllClients( "-----------------------------------------------------" );

		
	} //-end function Server.calculatePoints()

	/**
	 * 
	 */
	private static void putTableCards() 
	{
		int randomIndex;
		int topPos = 0;
		
		// find the top position of the cards on the table
		for( topPos=0; TableCards[topPos] != null ; topPos++ );
		
		// get all cards on the table
		for(int i=0; i<4 ; i++ ) {
			
			// get a random number
			do {
				randomIndex = (int) ( Math.random() * 51 );
			} while(deckOf52[randomIndex] == null);
			
			TableCards[topPos++] = new Cards( deckOf52[randomIndex] );
			deckOf52[randomIndex] = null;
		}
		topTableCard = TableCards[topPos-1];
		
	} //-end function Server.putTableCards()
	
	
	/**
	 *  @param Cards cardPlayed
	 */
	public static void nextPlayer( Cards cardPlayed ) 
	{
		int previousPlayer = (currentPlayer-1)%3;
		if( previousPlayer < 0 ) previousPlayer += 4;
		
    	// if hand is empty, deal cards again or end the game
    	if( player[ currentPlayer ].isHandEmpty() ) {
    		Server.dealCards( currentPlayer );
    	}
		
        // Player 0 is suspended until other players connects. Resume it now.    
      	synchronized ( player[ currentPlayer  ] ) {
      		player[ currentPlayer ].setSuspended( false );   
      		player[ currentPlayer ].notify();

      		if( cardPlayed != null ) {
		    	// let players know what card is played
      			sendMessageToAllClients( "> SERVER: " + player[previousPlayer].playerName + " played " + cardPlayed );
		    	System.out.println( "> Player " + previousPlayer + " played " + cardPlayed);
      		}
	    	
      		// let others know who they are waiting for
      		sendMessageToAllClients( "> SERVER: waiting for " + player[currentPlayer].playerName + " to play");
      		
      		// send message to the current player
      		player[ currentPlayer ].out.println("-----------------------------------------------------");
      		player[ currentPlayer ].out.println(" YOUR TURN " + player[currentPlayer].playerName );
      		player[ currentPlayer ].out.println("-----------------------------------------------------" );
      		player[ currentPlayer ].showHand();
      		player[ currentPlayer ].out.print(  " The top card on the table is: " );
      		if( Server.topTableCard==null) {
      			player[ currentPlayer ].out.println( "nothing" );
      		} else {
      			player[ currentPlayer ].out.println( Server.topTableCard );
      		}
      		player[ currentPlayer ].out.println("-----------------------------------------------------" );
      	}
 
    	player[ currentPlayer ].out.println( "Your Turn" );
      	
	} //-end function Server.nextPlayer( Cards )
	
	
	/**
	 *  @param String msg
	 */
	public static void sendMessageToAllClients( String msg )
	{	
		for( int i=0; i != numOfPlayers ; i++  ){
			
			player[i].setSuspended( false );	
			player[i].out.println( msg );
			player[i].setSuspended( true );
		}
		
	} //-end function Server.sendMessageToAllClients( String )
	
		
	/*  ***********************************************************************************************/
	/*	-- MAIN() FUNCTION ---------------------------------------------------------------------------*/
	/*  ***********************************************************************************************/

	/**
	 *  main() function: executes Server side
	 * 
	 *  @param String[] args
	 */
	public static void main( String args[] )
	{
		String portNumber = "";
		
    	try{
    		portNumber = new String( args[0] );
    	} 
    	catch( IndexOutOfBoundsException outOfbonds ) {	
    		outOfbonds.getLocalizedMessage();
    		portNumber = JOptionPane.showInputDialog( "Enter port number: ", "4444" );
    	}
    	
    	// set portNumber
    	if( portNumber == null || portNumber.equals("")){
    		portNumber = new String("4444");
    	} 
		    	
		Server application = new Server( portNumber );	
		
	} //-end function Server.main( String )


}  // - end class Server -
