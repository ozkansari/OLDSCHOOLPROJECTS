/*
 * ChatDialogJFrame.java
 *
 * Created on 04 Jan 2007 Thursday, 14:07
 *
 */

package client;

// Java core packages
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;

// My packages
import common.Message;
import common.ClientInfo;
import common.MyConstants;

/**
 * <p>
 * ChatDialogJFrame provides the chat dialog frame screen
 * </p>
 * 
 * @author ozkansari
 *
 */
public class ChatDialogJFrame extends JFrame {
    
	/** Display variables */
	private JTextField enterField;
    private JTextArea displayArea;
    private JButton fileSendButton;
    
    /** reference to MessengerClient class where sclient operations are prformed */
    private MessengerClient clientRef = null;
    
    /** Chat between "thisClient" and "otherclient"*/
    private ClientInfo thisClientInfo;
    private ClientInfo otherClientInfo;

    
    /**
     * 
     * initialize chatServer and set up GUI
     * 
     * @param client
     * @param otherClient
     */
    public ChatDialogJFrame( MessengerClient client, ClientInfo otherClient ) 
    {
        super(  );
        
        clientRef = client;
        setThisClientInfo(client.getClientInfo());
        setOtherClientInfo(otherClient);
        
        Container container = getContentPane();
        
        // create enterField and register listener
        enterField = new JTextField();
        enterField.setEnabled( true );
        
        enterField.addActionListener(
                
            new ActionListener() {

                /**
                 * sends message to server
                 * 
                 * (non-Javadoc)
                 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
                 */
                public void actionPerformed( ActionEvent event ) {

                    String msgString = thisClientInfo.getUserName() + " [" + thisClientInfo.getUserId() + "] " + "> "+ event.getActionCommand();
                    Message newClientMessage = new Message(  );
                    newClientMessage.setSourceId( getThisClientInfo().getUserId() );
                    newClientMessage.setDestinationId( getOtherClientInfo().getUserId() );
                    
                    newClientMessage.setSourceClientInfo( thisClientInfo );
                    newClientMessage.setDestinationClientInfo( otherClientInfo );
                    
                    newClientMessage.setMessageType( MyConstants.CLIENT_MESSAGE );
                    newClientMessage.setMsgString( msgString );
                    
                    clientRef.sendMessage( newClientMessage );
                    displayNewMessage( msgString );
                }
            
            }  // end anonymous inner class
        ); // end call to addActionListener

        container.add( enterField, BorderLayout.SOUTH );
        
        // create displayArea
        displayArea = new JTextArea();
        container.add( new JScrollPane( displayArea ),
                BorderLayout.CENTER );
        
        
        // create file send button
        fileSendButton = new JButton( "SEND FILE" );
        fileSendButton.addActionListener(
                
            new ActionListener() {

                // send message to server
                public void actionPerformed( ActionEvent event ) {

                    File selectedFile = askFileSend();
                    
                    clientRef.setFileTransfer( getContentPane() );
                    clientRef.getFileTransfer().initiateFileTransfer( getThisClientInfo(), getOtherClientInfo(), selectedFile );
                    //clientRef.getFileTransfer().sendFile( );
                    //clientRef.getFileTransfer().closeFileTransfer();
                    displayNewMessage( "FILE TO BE SEND " + selectedFile.getName() );
                }

            }  // end anonymous inner class
        ); // end call to addActionListener
        
        container.add( fileSendButton, BorderLayout.NORTH );
        
        // Window Listener to handle window closing
        this.addWindowListener(new WindowAdapter() {
            
            /* (non-Javadoc)
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            public void windowClosing(WindowEvent e) {
                    
                clientRef.getChatDialogVector().removeElement( ChatDialogJFrame.this );

            }
        });
        
        // set windo2 properties
        setSize( 300, 150 );
        setTitle( "from " + thisClientInfo.getUserName() + " [" + thisClientInfo.getUserId() + "] " + 
                  "to " + otherClientInfo.getUserName() + " [" + otherClientInfo.getUserId() + "] " );
        setVisible( true );
    }
    
    
    /**
     * @param newMsg
     */
    public void displayNewMessage( String newMsg )
    {
        displayArea.append( "\n" + newMsg );
        displayArea.setCaretPosition(
                displayArea.getText().length() );
        enterField.setText( "" );
    }
    
    /*  ***********************************************************************************************/
    /*	-- DISPLAYING METHODS ------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    /**
     * @param dialogMessage
     * @param dialogHeader
     * @param messageType
     */
    public void displayDialogToUser( String dialogMessage, String dialogHeader, int messageType ){
        JOptionPane.showMessageDialog( 
                this.getContentPane(), dialogMessage,
                dialogHeader, messageType 
        ); 
    }
    
    
    /**
     * @return  File created to be saved if receive request is accepted 
     * 			or null if it is not accepted
     */
    public File askFileAccept(  ) {
        
        File selectedFile = null;
        
        if( JOptionPane.showConfirmDialog( getContentPane(),
                "Do you want to accept file ?",
                "File transfer",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ) {

            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle( "Save transferred file as ... ");
            fc.setApproveButtonText( "SAVE FILE" );
            
            int returnVal = fc.showSaveDialog(ChatDialogJFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = fc.getSelectedFile();
            } else {
                // Save command cancelled by user
            }
            
        }
        
        return selectedFile;
        
    }
    
    /**
     * @return 	File to be send if send request is accepted 
     *  		or null if it is not accepted
     */
    public File askFileSend() {
        final JFileChooser fc = new JFileChooser();
        
        File selectedFile = null;
        fc.setDialogTitle( "Select a file to transfer ... ");  
        fc.setApproveButtonText( "SELECT" );
        
        int returnVal = fc.showOpenDialog(ChatDialogJFrame.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
        } else {
            // Save command cancelled by user
        }
        
        return selectedFile;
    }
    
    /**
     *  focus to frame
     */
    public void focusIt() {
        this.toFront();
    }
    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    public ClientInfo getThisClientInfo() {
        return thisClientInfo;
    }

    public void setThisClientInfo(ClientInfo thisClientInfo) {
        this.thisClientInfo = thisClientInfo;
    }

    public ClientInfo getOtherClientInfo() {
        return otherClientInfo;
    }

    public void setOtherClientInfo(ClientInfo otherClientInfo) {
        this.otherClientInfo = otherClientInfo;
    }
    

}
