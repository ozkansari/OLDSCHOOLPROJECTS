/*
 * MessengerJFrame.java
 *
 * Created on 07 Jan 2007 Sun, 01:12
 *
 */

package client;

import java.util.*;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import javax.swing.JOptionPane;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;

import util.DynamicTree;
import common.ClientInfo;

/**
 * <p>
 * MessengerJFrame provides the main frame  
 * </p>
 * 
 * @author ozkansari
 */
public class MessengerJFrame extends JFrame  {
    
    private MessengerClient client;
    Vector onlineUsers;
    javax.swing.JTextPane programInfoTextPane = new javax.swing.JTextPane();
    
    // User List tree
    private DynamicTree treePanel;
    private JTree usrtree;
    DefaultMutableTreeNode onlineUsers_Parent;

    /**
     * Creates a new instance of MessengerJFrame
     */
    public MessengerJFrame( String clientName, MessengerClient client ) {
        
        super( "MYMesssenger [" + clientName + "]" );
        
        // set client connection     
        setClient( client );
        
        // set layout
        setLayout(new BorderLayout());
        
        // create and add tree
        createUserListTree();
        add(treePanel, BorderLayout.CENTER);
       
        // program info 
        programInfoTextPane.setBackground(new java.awt.Color(204, 204, 255));
        programInfoTextPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        programInfoTextPane.setEditable(false);
        programInfoTextPane.setFont(new java.awt.Font("Viner Hand ITC", 2, 28));
        programInfoTextPane.setText("          MyMessenger");
        programInfoTextPane.setAutoscrolls(false);
        programInfoTextPane.setFocusCycleRoot(false);
        programInfoTextPane.setFocusable(false);
        programInfoTextPane.setMargin(new java.awt.Insets(5, 5, 5, 5));
        programInfoTextPane.setVerifyInputWhenFocusTarget(false);
        add( programInfoTextPane , BorderLayout.SOUTH );
        
        // set up the window ////////////////////////////////////////
        setSize(new Dimension(300, 500));
        setLocation( 200, 200 );
        //setSize( boardPanel.getSize() );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);
        
        
        // Window Listener to handle window closing
        this.addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog( getContentPane(),
                        "Are you sure you want to quit MyMessenger ?",
                        "Quit MyMessenger",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        setVisible(true);
        
    }

    private void createUserListTree() {

        treePanel = new DynamicTree( this );
        
        String p1Name = new String("Online Users");
        onlineUsers_Parent = treePanel.addObject(null, p1Name);
        
        initUserList();
        
    }
    
    public void initUserList() 
    {
        onlineUsers = getClient().getClientListVector();
        
        for( int i=0; i != onlineUsers.size() ; i++  ){
            
            ClientInfo current = (ClientInfo) onlineUsers.get(i) ;
            
            // if it's the client itself, skip
            if( current.getUserId() == client.getClientInfo().getUserId() )
                continue;
            else
                treePanel.addObject(onlineUsers_Parent, current);
        
        }
        
    }
    
    public void refreshUserList() 
    {
        treePanel.clear();
        
        String p1Name = new String("Online Users");
        onlineUsers_Parent = treePanel.addObject(null, p1Name);
        
        onlineUsers = getClient().getClientListVector();
        
        for( int i=0; i != onlineUsers.size() ; i++  ){

            ClientInfo current = (ClientInfo) onlineUsers.get(i) ;
            
            // if it's the client itself, skip
            if( current.getUserId() == client.getClientInfo().getUserId() )
                continue;
            else
                treePanel.addObject(onlineUsers_Parent, current);
        
        }
    }
    

    /**
     *  adds new chat dialog
     */
    public void addNewChatDialog( ChatDialogJFrame newDialog, int otherClientId  ) {
        
        Vector theChatDialogVector = getClient().getChatDialogVector();
        theChatDialogVector.add( newDialog);
        
    }
    
    /**
     *  adds new chat dialog
     */
    public void openChatDialog( ClientInfo destinationClientInfo ) {
        
        ChatDialogJFrame current = getClient().getChatDialogOf( destinationClientInfo.getUserId() , destinationClientInfo);
        
    }
    
    /**
     *  removes chat dialog
     */
    public void removeNewChatDialog( ChatDialogJFrame newDialog ) {
        
        getClient().getChatDialogVector().remove( newDialog );
    }
    
    
    /*  ***********************************************************************************************/
    /*	-- DISPLAYING METHODS ------------------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    public void displayDialogToUser( String dialogMessage, String dialogHeader, int messageType ){
        JOptionPane.showMessageDialog( 
                this.getContentPane(), dialogMessage,
                dialogHeader, messageType 
        ); 
    }
    
    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    public MessengerClient getClient() {
        return client;
    }

    public void setClient(MessengerClient client) {
        this.client = client;
    }


    
}
