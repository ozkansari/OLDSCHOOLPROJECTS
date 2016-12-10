/*
 * MessengerClient.java
 *
 * Created on 04 Jan 2007 Thu, 01:19
 *
 */

package client;

// Java core packages
import java.io.*;
import java.net.*;
import java.util.*;

// My packages
import common.Message;
import common.ClientInfo;
import common.MyConstants;
import util.FileTransferProgress;

/**
 * <p>
 * MessengerClient is the main class for client operations 
 * </p>
 * 
 * @author ozkansari
 */
public class MessengerClient implements Runnable {
    
    private Thread thread;
    private String serverHost;
    private int connectionPort;

    // connection variables
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    // chat variables
    private static Vector theClientListVector ;
    private static Vector theChatDialogVector = new Vector();
    
    // client info
    private ClientInfo thisClientInfo;
    
    // program variables
    private MessengerJFrame theMainFrameRef;
    private LoginJFrame theLoginScreen;
    private FileTransfer fileTransfer = null;
    
    /**
     * 
     * Creates a new instance of MessengerClient
     * 
     * @param host 
     * @param port
     * @param name
     */
    public MessengerClient( String host, int port, String name ) {
        
      // set server to which this client connects
      setServerHost(host);
      connectionPort = port;
      setClientInfo( name, -1, host+":"+port, true);
              
      try {
          // Create a Socket to make connection
          if( connectToServer() == true ) {

              // Get the input and output streams
              getStreams();

              // Init connection
              init();

              // start client
              thread = new Thread(this,"USER THREAD");
              thread.start();
          }
          else {
          
          }
          
      // process problems communicating with server
      } catch ( IOException ioException ) {
          ioException.printStackTrace();
      }
      
    }
    
    
    /**
     * connect to server and process messages from server
     */
    public void run() {
        // connect to server, get streams, process connection
        try {
            
            // process connection
            processConnection();
            
            // Close connection
            closeConnection();
        }
        
        // server closed connection
        catch ( EOFException eofException ) {
            System.out.println( "Server terminated connection" );
        }
        
        // process problems communicating with server
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
    }

    
    /**
     * connect to server
     * 
     * @return if connection try is OK
     * @throws IOException
     */
    private boolean connectToServer() throws IOException 
    {
        if( MyConstants.IS_DEBUG ) {
            System.out.println( "Attempting connection\n" );
        }
        
        try {
            // create Socket to make connection to server
            client = new Socket( InetAddress.getByName( getServerHost()), connectionPort );
            
        }
        // handle if not connecting server
        catch( ConnectException e ){
            return false;
        }
         
        // display connection information
        if( MyConstants.IS_DEBUG ) {
            System.out.println( "Connected to: " + client.getInetAddress().getHostName() );
        }
        
        return true;

    }
    
    /**
     *  get streams to send and receive data
     *  
     * @throws IOException  
     */
    private void getStreams() throws IOException 
    {
        // set up output stream for objects
        output = new ObjectOutputStream( client.getOutputStream() );
        
        // flush output buffer to send header information
        output.flush();
        
        // set up input stream for objects
        input = new ObjectInputStream( client.getInputStream() );

        if( MyConstants.IS_DEBUG ) {
            System.out.println(  "Got I/O streams"  );
        }
    }
    
    /**
     * init connection 
     *  
     * @throws IOException
     * 
     */
    private void init() throws IOException 
    {    
        // send client info to server
        Message newClientMessage = new Message( -1, -1, thisClientInfo );
        sendMessage( newClientMessage );
        
        // get list of online clients
        try {
            Message message = ( Message ) input.readObject();
            
            if( message.getMessageType() == MyConstants.CLIENT_LIST_MESSAGE ){
                theClientListVector = message.getClientListVector();
                thisClientInfo.setUserId( message.getDestinationId() );
            }
            
            if( MyConstants.IS_DEBUG ) {
                System.out.println( "got list of online clients" );
            }
           
        }
        catch ( ClassNotFoundException classNotFoundException ) {
            
            if( MyConstants.IS_DEBUG ) {
                System.out.println( "Unknown object type received" );
            }
        }
  
    }

    /**
     *  process connection with server
     *  
     * @throws IOException
     */
    private void processConnection() throws IOException 
    {
        
        // process messages sent from server
        do {
            
            // read message and display it
            try {
                
                // read message
                Message message = ( Message ) input.readObject();
                System.out.println( "msg read is  " + message );
                
                // check message type and perform suitable operation
                switch(  message.getMessageType() ) {
                    
                    case MyConstants.NOTIFY_ONLINE_CLIENT :
                    {
                        // add new online user to the list and refresh user list tree
                        if( message.getNewClientInfo().getUserId() != thisClientInfo.getUserId() ) {
                            theClientListVector.add( message.getNewClientInfo() );
                            theMainFrameRef.refreshUserList();
                        }
                    }
                    break;
                    
                    case MyConstants.NOTIFY_OFFLINE_CLIENT :
                    {
                        for( int i=0; i < theClientListVector.size() ; i++ ) {
                            
                            // remove offline user from the list and refresh user list tree
                            ClientInfo current = (ClientInfo) theClientListVector.get( i );
                            if( current.getUserId() == message.getSourceId() ) {
                                theClientListVector.remove( current );
                            }
                        }
                        
                        // find the appropriate dialog and display message on it
                        for( int i=0; i < theChatDialogVector.size() ; i++ ) {
                            
                            ChatDialogJFrame current = (ChatDialogJFrame) theChatDialogVector.get( i );
                            
                            if( current.getThisClientInfo().getUserId() == getClientInfo().getUserId() ) {
                                current.displayDialogToUser( "Destination Client is now offline", "Client Disconnected", javax.swing.JOptionPane.WARNING_MESSAGE );
                                theChatDialogVector.remove( current );
                                current.setVisible( false );
                            }
                        }
                        theMainFrameRef.refreshUserList();
                    }
                    break;
                    
                    case MyConstants.CLIENT_MESSAGE:
                    {
                        // !!! BUGGY: message.getSourceClientInfo().getUserId() is -1 ??? I don't know why
                        ClientInfo temp = message.getSourceClientInfo(); temp.setUserId( message.getSourceId() );
                        ChatDialogJFrame current = getChatDialogOf( message.getSourceId(), temp);
                        current.displayNewMessage( message.getMsgString() );
                    }
                    break;
                    
                    case MyConstants.FILE_SEND_MESSAGE:
                    {
                        // !!! BUGGY: message.getSourceClientInfo().getUserId() is -1 ??? I don't know why
                        ClientInfo temp = message.getSourceClientInfo(); temp.setUserId( message.getSourceId() );
                        
                        ChatDialogJFrame current = getChatDialogOf( message.getSourceId(), temp);
                        current.displayNewMessage( "File send request" );
                        
                        File selectedFile = current.askFileAccept(  );
                        if( selectedFile != null ) {
                            int fileLength = Integer.parseInt( message.getMsgString() );
                            fileTransfer = new FileTransfer(selectedFile, fileLength, current.getContentPane() );
                            sendMessage( new Message( message.getDestinationId(), message.getSourceId(), MyConstants.FILE_RECEIVE_MESSAGE, "OK" ));
                        }
                    }
                    break;
                    
                    case MyConstants.FILE_RECEIVE_MESSAGE:
                    {
                        if( message.getMsgString().compareToIgnoreCase( "OK") == 0 ){
                            getFileTransfer().sendFile( );
                        }
                    }
                    break;
                    
                    case MyConstants.FILE_PACKET_MESSAGE:
                    {
                        getFileTransfer().readFilePacket( message );
                    }
                    break;
                }

                if( MyConstants.IS_DEBUG ) {
                    System.out.println( message );
                }
                
                // clear your own garbage
                message.setFileByte( null );
                message.setDestinationClientInfo( null );
                message.setSourceClientInfo( null );
                message = null;
                                
                // collect garbage
                // Get a Runtime object
                Runtime r = Runtime.getRuntime();
                r.gc();
                System.out.println( "---------------- Free Memory: " + r.freeMemory() );
                
            }
            // if server is down
            catch( SocketException e ) {
                System.out.println( "Server Connection Reset " );
                e.printStackTrace();
                
                theLoginScreen.displayDialogToUser( "Server connecition reset", "Server down", javax.swing.JOptionPane.ERROR_MESSAGE );
                theMainFrameRef.setVisible( false );
                
                // show login screen again
                theLoginScreen.setVisible( true );
                return;
            }  
            // catch problems reading from server
            catch ( ClassNotFoundException classNotFoundException ) {
                
                if( MyConstants.IS_DEBUG ) {
                    System.out.println( "Unknown object type received" );
                }
            }
            
        } while ( true );
        
    }  // end method process connection


    /**
     *  close streams and socket
     *  
     * @throws IOException
     */
    public void closeConnection() throws IOException {
        
        if( MyConstants.IS_DEBUG ) {
            System.out.println( "Closing connection" );
        }
 
        output.close();
        input.close();
        client.close();
    }
    
    
    /**
     * 
     * gets chatDialogFrame for the specied client idS
     * 
     * @param id
     * @param forNewCreation
     * @return corresponding ChatDialogJFrame of given id
     */
    public ChatDialogJFrame getChatDialogOf( int id, ClientInfo forNewCreation ){
        
        boolean dialogExists = false;
        
        // find the appropriate dialog and display message on it
        for( int i=0; i < theChatDialogVector.size() ; i++ ) {
            
            ChatDialogJFrame current = (ChatDialogJFrame) theChatDialogVector.get( i );
            
            if( current.getOtherClientInfo().getUserId() == id ) {
                dialogExists = true;
                current.focusIt();
                return current;
            }
            
        }
        
        // Karþýdan mesaj var ama chatDialog yok -> yarat o zaman
        if( dialogExists == false ) {
            
            ChatDialogJFrame chatDialog = new ChatDialogJFrame( this , forNewCreation  );
            theChatDialogVector.addElement( chatDialog );
            chatDialog.focusIt();
            chatDialog.setVisible( true );
            System.out.println("dialog is created with destination id = " + forNewCreation.getUserId()  );
            
            return chatDialog;
        }
        
        return null;
    }
    

    /**
     * send message to server
     * 
     * @param message
     */
    public synchronized void sendMessage( Message message ) 
    {
        
        // send message object to server
        try {
            output.writeObject( message );
            //output.flush();
            
            if( MyConstants.IS_DEBUG ) {
                System.out.println( message );
            }

        }
        
        // process problems sending object
        catch ( IOException ioException ) {
            
            if( MyConstants.IS_DEBUG ) {
                System.out.println( "\nError writing object" );
            }

        }
    }
    
    
    /**
     * 
     * @author ozkansari
     *
     */
    public class FileTransfer {

        int length ;
        int current = 0;
        int packetSize = MyConstants.FILE_PACKET_SIZE;
        String statMessage = "";
        FileOutputStream out;
        java.io.FileInputStream fin;
        File selectedFile;
        ClientInfo senderClientInfo, receiverClientInfo;
        
        FileTransferProgress progress;
        java.awt.Container chatDialogContainer;
        boolean firstTimeRead = true;
        
        /**
         * 
         * for reading file 
         * 
         * @param selectedFile
         * @param length
         * @param chatDialogContainer	
         */
        public FileTransfer( File selectedFile, int length, java.awt.Container chatDialogContainer ){
            
            this.length = length;
            this.selectedFile = selectedFile;
            this.chatDialogContainer = chatDialogContainer;
            
            try {
                out = new FileOutputStream(selectedFile, true);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            
            progress = new FileTransferProgress();
        }
        
        
        /**
         * 
         * for sending file
         * 
         * @param chatDialogContainer
         */
        public FileTransfer( java.awt.Container chatDialogContainer ){
            this.chatDialogContainer = chatDialogContainer;
            
            progress = new FileTransferProgress();
        }
        
        
        /**
         * 
         * send user a message to ask if he/she acccepts file 
         * 
         * @param thisClientInfo
         * @param otherClientInfo
         * @param sendFile
         */
        protected void initiateFileTransfer( ClientInfo thisClientInfo, ClientInfo otherClientInfo, File sendFile ) 
        {
            senderClientInfo = thisClientInfo;
            receiverClientInfo = otherClientInfo;
            selectedFile = sendFile;
            
            Message newSendFileMessage = new Message( thisClientInfo, otherClientInfo, MyConstants.FILE_SEND_MESSAGE, ""+ packetSize );
            newSendFileMessage.setSourceId( thisClientInfo.getUserId() );
            newSendFileMessage.setDestinationId( otherClientInfo.getUserId() );
            
            try {
                fin = new java.io.FileInputStream(sendFile);
                
                length = fin.available(); // Returns the number of bytes that can be read from this file input stream without blocking.
                newSendFileMessage.setMsgString( ""+ length );
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            sendMessage( newSendFileMessage );
                    
        }
        
        /**
         *  Sends file to client whose info given as otherClientinfo parameter
         */
        protected void sendFile(  ) {

            if( selectedFile != null ) {

                try {
                    //java.io.FileInputStream fin = new java.io.FileInputStream(selectedFile);

                    //length = fin.available();//Returns the number of bytes that can be read from this file input stream without blocking.

                            int loopCount = 0;
                            length = fin.available(); 
                            //fin.close();
                            
                            // start progress monitor screen
                            progress.startProgressMonitor( chatDialogContainer , length );
                            
                            while ( current < length ) {

                                // open file input stream (reader)
                                //fin = new java.io.FileInputStream(selectedFile);
                                
                                if( length -current  < packetSize ) {
                                    packetSize = fin.available() ;
                                }

                                // read file as byte
                                byte[] data = new byte[packetSize];
                                int amountRead = 0;
                                try {
                                    amountRead = fin.read(data);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                
                                // close file input stream
                                //fin.close();

                                //System.out.println( String.valueOf( data) );
                                
                                // set message
                                Message newSendFileMessage = null;
                                newSendFileMessage = new Message( senderClientInfo, receiverClientInfo, MyConstants.FILE_PACKET_MESSAGE, "" + amountRead );
                                newSendFileMessage.setFileByte(data);
                                newSendFileMessage.setSourceId( thisClientInfo.getUserId() );
                                newSendFileMessage.setDestinationId( receiverClientInfo.getUserId() );
                                newSendFileMessage.setId( loopCount++ );

                                // send packet
                                sendMessage( newSendFileMessage );

                                // make progress
                                current += packetSize;
                                
                                // set progress message
                                statMessage = current + "of " + length + "bytes is OK";
                                System.out.println( statMessage );
                                
                                // set progress
                                progress.setProgressValue( current );
                                progress.setProgressMessage( statMessage );
                                
                                // clear your garbage
                                newSendFileMessage.setFileByte( null );
                                newSendFileMessage.setDestinationClientInfo( null );
                                newSendFileMessage.setSourceClientInfo( null );
                                newSendFileMessage = null;
                                data = null;

                                // collect garbage
                                // Get a Runtime object
                                Runtime r = Runtime.getRuntime();
                                r.gc();
                                System.out.println( "---------------- Free Memory: " + r.freeMemory() );
                            }

                }catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch( OutOfMemoryError ex ){
                    ex.printStackTrace();
                    
                    Runtime r = Runtime.getRuntime();
                    r.gc();
                    System.out.println( "---------------- Free Memory: " + r.freeMemory() );

                }

            } 
            // reject file transfer
            else {

            }
        }
        
        /**
         * read current file packet
         * 
         * @param newMessage
         */
        protected void readFilePacket( Message newMessage) {

            // start progress monitor screen if it's the first time reading
            if( firstTimeRead ){
                progress.startProgressMonitor( chatDialogContainer , length );
                firstTimeRead = false;
            }
            
            if( length - current < packetSize )
                packetSize = length - current;

            try{
                byte[] currentData = newMessage.getFileByte();
                System.out.println( currentData );
                
                out.write(currentData,0, packetSize );
                
                current += currentData.length;
                statMessage = current + "of " + length + "bytes is OK";
                
                // clear your garbage
                currentData = null;
            
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println( statMessage );
            
            // set progress
            progress.setProgressValue( current );
            progress.setProgressMessage( statMessage );
            
            if( current >= length )
                closeFileTransfer();
            

        }
        
        /**
         * 
         */
        public void closeFileTransfer() {
            
            System.out.println( "closing file transfer" );
            
            try {
                if( fin != null )   
                    fin.close();

                if( out != null )
                    out.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    public static Vector getClientListVector() {
        return theClientListVector;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }


    public void setClientInfo(String clientName, int clientId, String host, boolean isOnline ) {
        
        if( thisClientInfo == null )
            thisClientInfo = new ClientInfo( clientName, clientId , host, isOnline );
        else {
            thisClientInfo.setHostname( host );
            thisClientInfo.setUserName( clientName );
        }
    }
    
    public ClientInfo getClientInfo() {
        return thisClientInfo;
    }

    public static Vector getChatDialogVector() {
        return theChatDialogVector;
    }

    public static void setChatDialogVector(Vector chatDialogVector) {
        chatDialogVector = chatDialogVector;
    }

    public void setMainFrameRef(MessengerJFrame theMainFrameRef) {
        this.theMainFrameRef = theMainFrameRef;
    }

    public void setLoginScreen(LoginJFrame theLoginScreen) {
        this.theLoginScreen = theLoginScreen;
    }

    public Socket getConnectionSocket() {
        return client;
    }

    protected FileTransfer getFileTransfer() {
        return fileTransfer;
    }

    protected void setFileTransfer( java.awt.Container chatDialogContainer ) {
        this.fileTransfer = new FileTransfer( chatDialogContainer );
    }
    
}
