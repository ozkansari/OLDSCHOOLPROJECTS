/*
 * ClientHandle.java
 *
 * Created on 02 Jan 2007 Tue, 02:31
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
 * <p>
 * ClientHandle Thread is the thread handles messages coming from a corresponding client.
 * This thread corresponds to one specific client connected to the MessengerServer.
 * </p>
 *
 * @author ozkansari ( ozkan_yellow@hotmail.com )
 */
public class ClientHandle extends Thread {
    
    // Client attributes
    private int clientId;
    private String clientName;
    
    // Connection Variables
    private Socket socket = null;
    
    // Streams
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    
    /*  ***********************************************************************************************/
    /*  -- CONSTRUCTOR -------------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    /**
     * 
     * creates a newinstance of ClientHandle
     * 
     * @param socket connection socket of server with the corresponding client
     * @param id id of the corresponding client 
     */
    public ClientHandle( Socket socket, int id ) 
    {
    	super("Client-"+id);
    	
    	this.setClientId(id);       // mark clientId as its distinctive feature
        this.setSocket(socket);     // set connection socket
        
        // Get the input and output streams
        getStreams();
        
        
    } // --end-ClientHandle-constructor--  
    
    /*  ***********************************************************************************************/
    /*	-- THREAD METHODS ----------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    /**  The "ClientHandle.run()" method that controls thread's execution :
     *   It controls the information that is sent to the client and received from the client.
     *   
     *   @see java.lang.Thread#run()
     */
    public void run() {
        
    	Message inputMsg, outputMsg;
        
        while ( true ) {
            
            try {

                // wait and read message
                inputMsg = ( Message ) getInputStream().readObject();
                
                // forward message to proper destination
                MessengerServer.sendMessageTo( inputMsg, inputMsg.getDestinationId() );
                
                
                // clear your garbage
                inputMsg.setFileByte( null );
                inputMsg = null;
                
                // request garbage collection
                System.gc();

            } 
            // catch if the client's connection down
            catch( SocketException e ) {
                System.out.println( "Client Connection Reset: " + clientName + "[" + clientId + "]" );
                e.printStackTrace();
                
                // MessengerServer.clientHandleVector.remove( ClientHandle.this ); // NOT WORKING!!!!

                for( int i=0; i < MessengerServer.clientListVector.size() ; i++ ) {
                    
                    ClientInfo current = (ClientInfo) MessengerServer.clientListVector.get( i );
                    if( current.getUserId() == clientId ) {
                        MessengerServer.clientListVector.remove( current );
                    }
                }
                
                Message newClientMessage = new Message( clientId, -1, MyConstants.NOTIFY_OFFLINE_CLIENT, "" );
                MessengerServer.sendMessageToAllClients( newClientMessage );
                
                return;
            }  
            catch (IOException e) {
                e.printStackTrace();
            } catch ( ClassNotFoundException e ) {
                System.out.println( "Unknown object type received" );
                e.printStackTrace();
            } 
            
        }
        

    	
    } //--end-Players.run()-method--  

    
    /*  ***********************************************************************************************/
    /*	-- HELPER METHODS ----------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/    
    /**
     *  get streams to send and receive data
     */
    private void getStreams()
    {
        // obtain streams from Socket
        try {
            setOutputStream(new ObjectOutputStream(socket.getOutputStream()));
            setInputStream(new ObjectInputStream(socket.getInputStream() ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    /**
     * @return clientId id of the corresponding client with this server
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * @param clientId id of the corresponding client with this server
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * @return corresponding client's name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName corresponding client's name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return object output stream
     */
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * @param outputStream object output stream
     */
    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * @return object input stream
     */
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream object input stream
     */
    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return the connection socket of the corresponding client with this server 
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket sets the connection socket of the corresponding client with this server 
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
} // --end-ClientHandle-class--  
