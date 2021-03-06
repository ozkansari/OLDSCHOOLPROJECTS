/*
 * ServerMonitorJFrame.java
 *
 * Created on 14 Ocak 2007 Pazar, 23:17
 */

package server;

import javax.swing.JList;
import java.util.Vector;

/**
 * <p>
 * ServerMonitorJFrame is a monitoring frame to display server & client info 
 * </p>
 * 
 * @author  ozkansari
 */
public class ServerMonitorJFrame extends javax.swing.JFrame {
    
    /** Creates new form ServerMonitorJFrame */
    public ServerMonitorJFrame(  ) {

        initComponents();
        
        setTitle( "MyMessenger Server ");
        setVisible(true);
        
    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Messages_jTextArea = new javax.swing.JTextArea();
        messages_jLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        client_jList = new javax.swing.JList();
        clientList_jLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Information_jTextArea = new javax.swing.JTextArea();
        informationScreen_jLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jTextPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Monotype Corsiva", 0, 28));
        jTextPane1.setText("MyMessenger Server Information Screen");
        jScrollPane1.setViewportView(jTextPane1);

        Messages_jTextArea.setColumns(20);
        Messages_jTextArea.setEditable(false);
        Messages_jTextArea.setRows(5);
        jScrollPane2.setViewportView(Messages_jTextArea);

        messages_jLabel.setText("Messages Screen");

        client_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(client_jList);

        clientList_jLabel.setText("Client List");

        Information_jTextArea.setColumns(20);
        Information_jTextArea.setEditable(false);
        Information_jTextArea.setRows(5);
        jScrollPane4.setViewportView(Information_jTextArea);

        informationScreen_jLabel.setText("Information Screen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(messages_jLabel)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                            .addComponent(informationScreen_jLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(clientList_jLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messages_jLabel)
                    .addComponent(clientList_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(informationScreen_jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * 
     * @param msg displays given message in message JTextArea area 
     */
    public void displayMessage( String msg ) {
        Messages_jTextArea.append( msg + "\n" );
        Messages_jTextArea.setCaretPosition(
                Messages_jTextArea.getText().length() );
    }
    
    /**
     * 
     * @param msg displays given message in information JTextArea area 
     */
    public void displayInformation( String msg ) {
        Information_jTextArea.append( msg + "\n" );
        Information_jTextArea.setCaretPosition(
                Information_jTextArea.getText().length() );
    }
    
    /**
     * 
     * @param data set client list in JList area
     */
    public void setClientList( java.util.Vector data ){
        client_jList.setListData( data );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Information_jTextArea;
    private javax.swing.JTextArea Messages_jTextArea;
    private javax.swing.JLabel clientList_jLabel;
    private javax.swing.JList client_jList;
    private javax.swing.JLabel informationScreen_jLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel messages_jLabel;
    // End of variables declaration//GEN-END:variables
 
}
