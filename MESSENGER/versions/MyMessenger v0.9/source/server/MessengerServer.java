/*
 * MessengerServer.java
 *
 * Created on 02 Jan 2007 Tue, 02:26
 *
 */

package server;

// Java core packages
import java.net.*;
import java.io.*;
import java.util.*;

// My packages
import common.Message;
import common.MyConstants;

/**
 * <p>
 * This MessengerServer Class constructor <br>
 *  - creates server sockets <br>
 *  - creates a new thread, named ConnectionThread, to listen for new connections. <br>
 * <br>
 * Connected clients' information are stored in this class's clientListVector:Vector  <br>
 * Connected clients' handler threads are stored inside clientHandleVector:Vector  <br>
 * <br>
 * This MessengerServer Class also has methods to  <br>
 *  - send given message (msg:Object) to sepecified client identified by its id (client id:int)  <br>
 *  - send given message (msg:Object) to all clients in clientHandleVector:Vector  <br>
 * </p>
 *  
 * @author ozkansari
 */
public class MessengerServer  {
    
    /* Display monitor variable */
    static ServerMonitorJFrame monitoringFrame ;
            
    /* Connection variables */
    private int serverPort ;
    protected static ServerSocket serverSocket;
    
    /* handler that listens for new connections and that creates a new ClientHandler thread */
    ConnectionHandle connectionHandler;
    
    /* Vectors */
    protected static Vector clientHandleVector;     // stores client handle threads
    protected static Vector clientListVector;       // stores client information
    
    /**
     * Creates a new instance of MessengerServer
     *
     * @param port server port to listen for new connections
     */
    public MessengerServer( int port ) {
        
        serverPort = port;
        clientHandleVector = new Vector();
        clientListVector = new Vector();
        
        monitoringFrame = new ServerMonitorJFrame( );
        
        
        // create a ServerSocket :::
        try {
            serverSocket  = new ServerSocket(serverPort);
        } catch( IOException ioException ) {
            ioException.printStackTrace();
            System.exit( 1 );
        }
        
        if( MyConstants.IS_DEBUG ) {
            monitoringFrame.displayInformation("java MessengerServer " + serverPort );
            monitoringFrame.displayInformation( "MessengerServer started successfully... waiting for clients." );
        }
        
        // Create the thread that handles connections
        connectionHandler = new ConnectionHandle();
        connectionHandler.start();
    
    } //-end MessengerServer constructor
    
    
    /**
     *  @param msg message to be send
     */
    public static void sendMessageToAllClients( Message msg ) {
        
    	// check client handle vector to find the proper client to whom message will be sent
        for( int i=0; i != clientHandleVector.size() ; i++  ){
            
            try {
                
            	// send message object
                ( (ClientHandle) clientHandleVector.get(i) ).getOutputStream().writeObject( msg );
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        
        // display message on server monitor
        monitoringFrame.displayMessage( "[BROADCAST MESSAGE] " + msg );
        
        if( msg.getMessageType() == MyConstants.NOTIFY_ONLINE_CLIENT || 
            msg.getMessageType() == MyConstants.NOTIFY_OFFLINE_CLIENT    
          ){
          monitoringFrame.displayInformation( "Client List is refreshed" );
          monitoringFrame.setClientList( clientListVector );
        }
        
    } //-end function Server.sendMessageToAllClients( Message )
    
    
    /**
     *  @param msg message to be send
     *  @param clientId client's id where message to be send
     */
    public static void sendMessageTo( Object msg, int clientId ) {
        
    	// check client handle vector to find the proper client to whom message will be sent
        for( int i=0; i != clientHandleVector.size() ; i++  ){
            
            try {
                
                ClientHandle current = (ClientHandle) clientHandleVector.get(i) ;
                if ( current.getClientId() == clientId ) {
                	
                	// send message object
                    current.getOutputStream().writeObject( msg );
                    
                    monitoringFrame.displayMessage( msg.toString() );
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }

    } //-end function Server.sendMessageToAllClients( Message )
    
    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    /**
     * @return the Vector that keeps ClientHandle threads inside
     */
    public java.util.Vector getClientHandleVector() {
        return clientHandleVector;
    }
    
    /**
     * @param clientHandleVector the Vector that keeps ClientHandle threads inside
     */
    public void setClientHandleVector(java.util.Vector clientHandleVector) {
        this.clientHandleVector = clientHandleVector;
    }
    
    /**
     * @return the Vector that keeps ClientInfo objects
     */
    public static Vector getClientListVector() {
        return clientListVector;
    }
    
    /**
     * @param aClientListVector the Vector that keeps ClientInfo objects
     */
    public static void setClientListVector(Vector aClientListVector) {
        clientListVector = aClientListVector;
    }
    
    /*  ***********************************************************************************************/
    /*	-- MAIN() FUNCTION ---------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    /**
     *  main() function: executes Server side
     *
     *  @param args server program argument
     */
    public static void main( String args[] ) {
        
        MessengerServer application = new MessengerServer( 5000 );
        
    } //-end function Server.main( String )
    
    
}
