/**
 *
 */

package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;


/**
 * @author ozkansari
 *
 */
public class UpdateProductScreen extends JFrame {
    
    // Variables declaration - do not modify
    private JTextField amount_JText;
    private JComboBox product_jComboB;
    private JButton update_Btn;
    private JLabel selectProduct_Lbl;
    private JLabel newAmount_Lbl;
    private JTextPane jTextPane2;
    
    Object[] data;
    db.ProductsDB productDB;  
    
    public UpdateProductScreen(){
        
        super("shopApp>Update Product "); // set title of the window

	MenuHandler handler = new MenuHandler(); 
                
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // create database connection
        db.DatabaseConn dbConnection = new db.DatabaseConn();
        productDB = new db.ProductsDB( dbConnection.getConnection() );
        
        // Get table data
        data = productDB.fetchProductNames();
        
        // create variables
        selectProduct_Lbl = new javax.swing.JLabel("Select Product");
        newAmount_Lbl = new javax.swing.JLabel("New Amount");
        product_jComboB = new javax.swing.JComboBox( data );
        amount_JText = new javax.swing.JTextField("0", 3);
        update_Btn = new javax.swing.JButton("UPDATE");

        // Add action Listeners
        product_jComboB.addActionListener(handler);
        amount_JText.addActionListener(handler);
        update_Btn.addActionListener(handler);

        // set the position in the screen
        int inset = 250;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

        initComponents();
        
        // set window properties
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    private boolean validateInput( ) {
        
        if(amount_JText.getText().isEmpty() ) {
            
            JOptionPane.showMessageDialog( UpdateProductScreen.this,
                    "An entry cannot be empty. Please check your input.",
                    "Error", JOptionPane.WARNING_MESSAGE );
            return false;
        }
        
        return true;
        
    } // --end-validateInput-method--

    /** INNER CLASS FOR MENU EVENT HANDLING
     *
     */
    class MenuHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            
            // detect the source of the event generated and perform their respective task
            
            // if 'LIST PRODUCTS' is selected
            if (event.getSource() == amount_JText) {
                
            }
            
            // if 'COMBOBOX' is selected
            if (event.getSource() == product_jComboB) {

            }
            
            // if 'UPDATE BUTTON' is selected
            if (event.getSource() == update_Btn) {
                
                if( validateInput() == false ) {
                    return;
                }
           
                int selected = product_jComboB.getSelectedIndex();
                productDB.updateProduct(Integer.parseInt(amount_JText.getText()), data[selected].toString()  );
                
                // close
		setVisible(false);
            }

        } //--end- actionPerformed() -function--
        
    } // --end-inner-class- "MenuHandler" --
    
    
     // <editor-fold defaultstate="collapsed" desc=" Initilization ">
    private void initComponents() {

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextPane2 = new javax.swing.JTextPane();
        jTextPane2.setBackground(new java.awt.Color(255, 255, 153));
        jTextPane2.setEditable(false);
        jTextPane2.setFont(new java.awt.Font("Arial", 1, 12));
        jTextPane2.setForeground(new java.awt.Color(204, 0, 51));
        jTextPane2.setText("SELECT AND UPDATE PRODUCT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(newAmount_Lbl)
                                    .addComponent(selectProduct_Lbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(product_jComboB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(amount_JText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(update_Btn)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectProduct_Lbl)
                    .addComponent(product_jComboB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newAmount_Lbl)
                    .addComponent(amount_JText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(update_Btn)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>   
    
}
