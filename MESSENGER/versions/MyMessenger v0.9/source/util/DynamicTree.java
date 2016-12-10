/*
 * This code is based on an example provided by Richard Stanford,
 * a tutorial reader.
 */

package util;

import client.MessengerClient;
import client.MessengerJFrame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import common.ClientInfo;
import client.ChatDialogJFrame;

/**
 * <p>
 * DynamicTree provides the user list tree  
 * </p>
 * 
 * @author ozkansari
 *
 */
public class DynamicTree extends JPanel {
	
	// program variable
    private MessengerJFrame theMainFrame ;
    
    // tree variables
    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;
    protected JTree tree;
    private MyTreeListener treeListener;
    
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    
    /**
     * @param mainFrame JFrame where this JTree panel is displayed
     */
    public DynamicTree( MessengerJFrame mainFrame ) {
        super(new GridLayout(1,0));
        
        theMainFrame = mainFrame;
        
        rootNode = new DefaultMutableTreeNode("USER LIST");
        treeModel = new DefaultTreeModel(rootNode);
        treeListener = new MyTreeListener();
        treeModel.addTreeModelListener(treeListener);
        
        tree = new JTree(treeModel);
        tree.setEditable(false);
        tree.setRootVisible( false );

        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        //tree.setRootVisible( true);
        tree.addTreeSelectionListener(treeListener);
        tree.addMouseListener( treeListener );
        JScrollPane scrollPane = new JScrollPane(tree);
        
        
        add(scrollPane);
    }
    
    /** Remove all nodes except the root node. */
    public void clear() {
        rootNode.removeAllChildren();
        treeModel.reload();
    }
    
    /** Remove the currently selected node. */
    public void removeCurrentNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
            (currentSelection.getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                return;
            }
        }
        
        // Either there was no selection, or the root was selected.
        toolkit.beep();
    }
    
    /** Add child to the currently selected node. */
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();
        
        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode)
            (parentPath.getLastPathComponent());
        }
        
        return addObject(parentNode, child, true);
    }
    
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
            Object child) {
        return addObject(parent, child, true);
    }
    
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
            Object child,
            boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode =
                new DefaultMutableTreeNode(child);
        
        if (parent == null) {
            parent = rootNode;
        }
        
        treeModel.insertNodeInto(childNode, parent,
                parent.getChildCount());
        
        //Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }
    
    class MyTreeListener implements TreeModelListener, TreeSelectionListener, MouseListener {
        
    	/* (non-Javadoc)
         * @see javax.swing.event.TreeModelListener#treeNodesChanged(javax.swing.event.TreeModelEvent)
         */
        public void treeNodesChanged(TreeModelEvent e) {

        }
        
        /* (non-Javadoc)
         * @see javax.swing.event.TreeModelListener#treeNodesInserted(javax.swing.event.TreeModelEvent)
         */
        public void treeNodesInserted(TreeModelEvent e) {
        }
        
        /* (non-Javadoc)
         * @see javax.swing.event.TreeModelListener#treeNodesRemoved(javax.swing.event.TreeModelEvent)
         */
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        
        /* (non-Javadoc)
         * @see javax.swing.event.TreeModelListener#treeStructureChanged(javax.swing.event.TreeModelEvent)
         */
        public void treeStructureChanged(TreeModelEvent e) {
        }
        
        /* (non-Javadoc)
         * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
         */
        public void valueChanged(TreeSelectionEvent e) {
            
//            System.out.println("inside valueChanged method");
//            
//            DefaultMutableTreeNode node = null;
//            
//            
//            if (node == null) return;
//            node= (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//
//            if (node.isLeaf()) {
//                
//                ClientInfo destinationClientInfo = (ClientInfo)(node.getUserObject());
//                //ChatDialogJFrame chatDialog = new ChatDialogJFrame( theMainFrame.getClient(), destinationClientInfo  );
//                
//                theMainFrame.openChatDialog( destinationClientInfo );
//                
//                //theMainFrame.addNewChatDialog( chatDialog, destinationClientInfo.getUserId());
//                //System.out.println("dialog is created with " + theMainFrame.getClient().getClientInfo().getUserId() );
//                
//                
//            } else {
//                
//            }
        }
        
        /**
         * handles mouse cliecks inside JTree
         * 
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        public void mouseClicked(MouseEvent e) {
            
            System.out.println("inside mouseClicked method");
            
            int selRow = tree.getRowForLocation(e.getX(), e.getY());
            TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
            
            DefaultMutableTreeNode node;
            
            if(selRow > 0 ) {
            	
            	// if user double clicks
                if(e.getClickCount() == 2) {
                    
                	// get corresponding tree node
                    node = (DefaultMutableTreeNode)selPath.getLastPathComponent();
                    
                    // check if node is null
                    if (node == null) return;
                    
                    // if node ia a user (on tree leaf), open chat dialog
                    if (node.isLeaf()) {
                        ClientInfo destinationClientInfo = (ClientInfo)(node.getUserObject());
                        theMainFrame.openChatDialog( destinationClientInfo );
                    } else {
                        // for offline users
                    }
                    
                }
            }
 
        }
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
         */
        public void mousePressed(MouseEvent e) {
        }
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
         */
        public void mouseReleased(MouseEvent e) {
        }
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        public void mouseEntered(MouseEvent e) {
        }
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
