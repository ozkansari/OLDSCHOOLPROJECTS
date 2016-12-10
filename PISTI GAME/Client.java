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

//Java core packages
import java.io.*;
import java.net.*;

// Java extension packages
import javax.swing.JOptionPane;

public class Client {
	
	static int serverPort ;
	static String hostName = "", portNumber="", playerName="" ;
	private static boolean yourTurn = false;
	
	/**
	 *  main() function: executes Client side
	 * 
	 *  @param String[] args
	 *  @throws IOException
	 */
    public static void main(String[] args) throws IOException {
    	
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        initialize( args );
        
        try {
        	// make connection
            clientSocket = new Socket( hostName, serverPort);
            
            // get streams 
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostName );
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostName );
            System.exit(1);
        }
        
        System.out.println("java PistiClient cse.yeditepe.edu.tr " + serverPort + " "+ hostName );
        out.println( playerName );
        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        
        while ( (fromServer = in.readLine()) != null ) {
     
        	System.out.println( fromServer );
        	
        	if( fromServer.equalsIgnoreCase( "Your Turn" ) ) {
        		yourTurn = true;
        	}
        	else if( fromServer.equalsIgnoreCase( "Game Over!" )  ) {
        		out.println( fromServer );
        		try {
					Thread.sleep( 10000 ); // wait 10 seconds before exit
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
        		out.close();
        		in.close();
        		stdIn.close();
        		clientSocket.close();
        	}

        	// If the player's turn
            while( yourTurn ) {
            	
	            if ( (fromUser = stdIn.readLine()) != null) { 
	    		    	
	    		    System.out.println("> " + playerName + ": " + fromUser);
	    		    out.println(fromUser); 							// send input to server (Players class)
	    		    
	    		    // check server's message
	    		    fromServer = in.readLine();
	    		    if( fromServer.equalsIgnoreCase("Valid move") )  {
	    		    	yourTurn = false;             
	    		    }
	    		    else if(fromServer.equalsIgnoreCase( "Invalid move, try again" ) ) {
	    		    	yourTurn = true;
	    		    	System.out.println( "Invalid move, try again" );
	    		    } 
	    		    else if( fromServer.equalsIgnoreCase( "input again" )	 ){
	    		    	yourTurn = true;
	    		    } 
	    		    else {
	    		    	System.out.println( fromServer );
	    		    	fromServer = in.readLine();
	    		    	System.out.println( fromServer );
	    		    	fromServer = in.readLine();
	    		    	System.out.println( fromServer );
	    		    	fromServer = in.readLine();
	    		    	System.out.println( fromServer );
	    		    }

	            }
	            
            } // -end inner while
	
        } // -end outer while

    } //-end function Client.main( args)
      
    /**
     * @param String[] args
     * 
     */
    static void initialize( String[] args )
    {
    	try{
    		hostName = new String( args[0] );
    		portNumber = new String( args[1] );
    		playerName = new String( args[2] );
    	} 
    	catch( IndexOutOfBoundsException outOfbonds ) {	
    		outOfbonds.getLocalizedMessage();
    		hostName = JOptionPane.showInputDialog( "Enter host name: ", "localhost" );
    		portNumber = JOptionPane.showInputDialog( "Enter port number: ", "4444" );
    		playerName = JOptionPane.showInputDialog( "Enter name: ", "player-?" );
    	}
    	
    	// set host name
    	if( hostName == null || hostName.equals("")){
    		hostName = "localhost";
    	}
    	
		// set portNumber
    	if( portNumber == null || portNumber.equals("")){
    		serverPort = 4444;
    	} else {
			serverPort = Integer.parseInt( portNumber );
		}
		
		// set playerName
		if( playerName == null || playerName.equals("")){
			playerName = new String( "Player-?" );
		} 	
		
    } //-end function initialize( String )
   
   
} // -end class Client - 
