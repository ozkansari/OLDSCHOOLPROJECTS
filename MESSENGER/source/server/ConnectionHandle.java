/*
 * ConnectionHandle.java
 *
 * Created on 03 Jan 2007 Wed, 19:58
 *
 */

package server;

// Java core packages
import java.net.*;
import java.io.*;
import java.util.*;

// My packages
import common.Message;
import common.ClientInfo;
import common.MyConstants;

/**
 * 
 * <p>
 * This ConnectionHandle Class listens for new connections and if new connection request comes from a client, <br>
 * new ClientHandle Thread is created and it is added to cliendHandleVector <br>
 * <br>
 * This ConnectionHandle Class constructor <br>
 *  - sets references to MessengerServer's clientHandleVector, clientListVector, serverSocket <br>
 * <br> 
 * Connected clients' information are stored in this class's <b> clientListVector:Vector </b> <br>
 * Connected clients' handler threads are stored inside <b> clientHandleVector:Vector </b> <br>
 * Server Socket of the corresponding client is in <b> serverSocketRef </b> <br>
 *  </p>
 *  
 * @author ozkansari
 */
public class ConnectionHandle extends Thread  {
    
	// Connection Variables
    private Socket clientSocket = null;
    private ServerSocket serverSocketRef;
    
    /* Vector References */
    private java.util.Vector clientHandleVectorRef;
    private java.util.Vector clientListVectorRef;


    /*  ***********************************************************************************************/
    /*	-- CONSTRUCTOR -------------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    /** Creates a new instance of ConnectionHandle */
    public ConnectionHandle(  ) {
        
        clientHandleVectorRef = MessengerServer.clientHandleVector;
        clientListVectorRef = MessengerServer.getClientListVector();
        serverSocketRef = MessengerServer.serverSocket;
        
    }
    
    /*  ***********************************************************************************************/
    /*	-- THREAD METHODS ----------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    /**  The "ConnectionHandle.run()" method that controls thread's execution :
     *   It waits for connections and handle connection requests.
     *   
     *   @see java.lang.Thread#run()
     */
    public void run() {
        
        // Get connections :::
        for( int clientId=0; true; clientId++ )  {
            
            ClientHandle current = null ;
                    
            // Create a new thread to handle the new client ::: 
            try {
                current = new ClientHandle(serverSocketRef.accept(), clientId );
                clientHandleVectorRef.addElement(current );
            } catch (IOException e) {
                e.printStackTrace();
                System.exit( 1 );
            }
            
            // Wait for message and get client info
            Message initMsg = null;
            try {
                initMsg = (Message) current.getInputStream().readObject();
                current.setClientName( initMsg.getNewClientInfo().getUserName() );
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Add new client to the online client list
            ClientInfo newClient = new ClientInfo( current.getClientName(), current.getClientId(), "", true );
            clientListVectorRef.addElement( newClient );

            // Run current client handler
            current.start();
            
            // Send online user list to the new client
            Message clientListMsg = new Message( clientId, clientId, clientListVectorRef );  
            MessengerServer.sendMessageTo( clientListMsg , clientId );
                    
            // Notify all clients :::
            Message newClientMessage = new Message( clientId, -1 , new ClientInfo(  current.getClientName(), current.getClientId() , "", true ) );
            MessengerServer.sendMessageToAllClients( newClientMessage );
            
            if( MyConstants.IS_DEBUG ) {
                System.out.println( "DEBUG> new clienthandle with " + current.getClientId() + " " + current.getClientName() );
                System.out.println( newClientMessage.toString() );
            }

        }
        
    }

    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    /**
     * @return
     */
    public Socket getClientSocket() {
        return clientSocket;
    }

    /**
     * @param clientSocket
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

}