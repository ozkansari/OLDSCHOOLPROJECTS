package admin;
/**
 * 
 */

import java.sql.Timestamp;

/**
 * @author ozkansari
 * 
 * 	Corresponding class type for the "orders" table in the database
 * 	
 * 	 Field 			Type 	 Collation 			Null 	Default 							
 * 	 Cliend_id 		char(15) latin1_swedish_ci  No                 
 * 	 Product_name 	char(15) latin1_swedish_ci  Yes 	NULL                
 *   Order_date 	timestamp  					No 		0000-00-00 00:00:00 
 *            
 */
public class OrdersType {

	String Cliend_id;                 
	String Product_name;               
	Timestamp Order_date;
	
	/* GETTERS AND SETTERS */
	
	public String getCliend_id() {
		return Cliend_id;
	}
	public void setCliend_id(String cliend_id) {
		Cliend_id = cliend_id;
	}
	public Timestamp getOrder_date() {
		return Order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		Order_date = order_date;
	}
	public String getProduct_name() {
		return Product_name;
	}
	public void setProduct_name(String product_name) {
		Product_name = product_name;
	}               

}
