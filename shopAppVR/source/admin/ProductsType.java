package admin;
/**
 * 
 */

/**
 * @author ozkansari
 * 
 * 	Corresponding class type for the "products" table in the database
 * 	
 * 	 Field 			Type 	 Collation 			Null 	Default 							
 *   Product_id 	char(15) latin1_swedish_ci  No                 
 *   Product_name 	char(30) latin1_swedish_ci  Yes 	NULL                
 *   Amount 		int(15)   					Yes 	NULL                
 *   Price 			float   					No                 
 *   
 */
public class ProductsType {

	String Product_id;                 
	String Product_name;               
	int Amount;               
	float Price;
	
	
	/* GETTERS AND SETTERS */
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public String getProduct_id() {
		return Product_id;
	}
	public void setProduct_id(String product_id) {
		Product_id = product_id;
	}
	public String getProduct_name() {
		return Product_name;
	}
	public void setProduct_name(String product_name) {
		Product_name = product_name;
	}                

}
