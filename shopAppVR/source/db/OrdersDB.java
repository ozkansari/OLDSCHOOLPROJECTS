package db;
/**
 *
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.PreparedStatement;

import admin.OrdersType;


/**
 * @author ozkansari
 *
 */
public class OrdersDB {
    
    Connection dbConnection;
    
    /**
     *
     */
    public OrdersDB(Connection newDbConnection) {
        dbConnection = newDbConnection;
    }
    
    /**
     *
     */
    public ArrayList fetchAllOrders() {
        
        ArrayList<OrdersType> orderList = new ArrayList<OrdersType>();
        
        try {
            Statement statement = dbConnection.createStatement();
            String q = "SELECT a.* FROM `orders` a ORDER BY a.Cliend_id";
            ResultSet rs = statement.executeQuery(q);
            
            while (rs.next()) {
                
                OrdersType order = new OrdersType();
                
                order.setCliend_id(rs.getString("cliend_id"));
                order.setProduct_name(rs.getString("Product_name"));
                order.setOrder_date(rs.getTimestamp("Order_date"));
                
                orderList.add(order);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderList;
    }
    
    /**
     *
     * @return
     */
    public Object[][] fetchTableData() {
        
        Object[][] data;	// data table to be fetched
        
        try {
            /* PREPARE STATEMENTS */
            PreparedStatement orderListStm = dbConnection.prepareStatement( "SELECT o.* FROM `orders` o" );
            
            ResultSet rs = orderListStm.executeQuery();
            
            /* Get size and Create table */
            rs.last();
            int count = rs.getRow();
            data = new Object[count][3];
            rs.beforeFirst();
            
            int i=0;
            while (rs.next()) {
                
                int j=0;
                data[i][j++] = rs.getObject("o.Cliend_id");
                data[i][j++] = rs.getObject("o.Product_name");
                data[i][j++] = rs.getObject("o.Order_date");
                i++;
            }
            
            return data;
            
        } catch( SQLException sqlException ) {
            sqlException.printStackTrace();
            return null;
        }
        
    }
}
