/**
 *
 */

package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.PreparedStatement;

import admin.ProductsType;


/**
 * @author ozkansari
 *
 */
public class ProductsDB {
    
    Connection dbConnection;
    
    /**
     *
     */
    public ProductsDB(Connection newDbConnection) {
        dbConnection = newDbConnection;
    }
    
    /**
     *	Inserts new product to database
     */
    public boolean insertNewProduct( ProductsType newProduct ) {
        int numrows=0;
        
        try {
            
            /* Prepare SQL statement */
            PreparedStatement addProduct = dbConnection.prepareStatement(
                    " INSERT INTO `products` ( `Product_id` , `Product_name` , `Amount` , `Price` ) " +
                    " VALUES (\"" +
                    newProduct.getProduct_id() + "\",\"" +
                    newProduct.getProduct_name() + "\"," +
                    newProduct.getAmount() + "," +
                    newProduct.getPrice() +
                    " ); "
                    );
            
            System.out.println( addProduct.toString() );
            
            /* Execute prepared statement */
            numrows = addProduct.executeUpdate();
            
            /* Check if operation is OK */
            if (numrows == 1) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     *
     * @return
     */
    public Object[][] fetchTableData() {
        
        Object[][] data;	// data table to be fetched
        
        try {
            /* PREPARE STATEMENTS */
            PreparedStatement productsListStm = dbConnection.prepareStatement( "SELECT p.* FROM `products` p" );
            
            ResultSet rs = productsListStm.executeQuery();
            
            /* Get size and Create table */
            rs.last();
            int count = rs.getRow();
            data = new Object[count][4];
            rs.beforeFirst();
            
            int i=0;
            while (rs.next()) {
                
                int j=0;
                data[i][j++] = rs.getObject("p.Product_id");
                data[i][j++] = rs.getObject("p.Product_name");
                data[i][j++] = rs.getObject("p.Amount");
                data[i][j++] = rs.getObject("p.Price");
                i++;
            }
            
            return data;
            
        } catch( SQLException sqlException ) {
            sqlException.printStackTrace();
            return null;
        }
        
    }
    
    
    /**
     *
     * @return
     */
    public Object[] fetchProductNames() {
        
        Object[] data;	// data table to be fetched
        
        try {
            /* PREPARE STATEMENTS */
            PreparedStatement productsListStm = dbConnection.prepareStatement( "SELECT p.* FROM `products` p" );
            
            ResultSet rs = productsListStm.executeQuery();
            
            /* Get size and Create table */
            rs.last();
            int count = rs.getRow();
            data = new Object[count];
            rs.beforeFirst();
            
            int i=0;
            while (rs.next()) {
                data[i] = rs.getObject("p.Product_name");
                i++;
            }
            
            return data;
            
        } catch( SQLException sqlException ) {
            sqlException.printStackTrace();
            return null;
        }
        
    }
    
    /**
     *
     * @return
     */
    public boolean updateProduct( int newAmount, String productName ) {
        
        try {
            /* PREPARE STATEMENTS */
            PreparedStatement productsListStm = dbConnection.prepareStatement
                    ( "UPDATE `products` SET `Amount` = '" + newAmount +
                    "' WHERE `Product_name` = '" + productName +
                    "'" );
            
            productsListStm.executeUpdate();
            
            return true;
            
        } catch( SQLException sqlException ) {
            sqlException.printStackTrace();
            return false;
        }
        
    }
    
    /**
     *
     * @return
     */
    public String[] fetchColumnNames() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
