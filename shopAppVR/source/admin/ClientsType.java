package admin;
/**
 * 
 */

/**
 * @author ozkansari
 * 
 * 	Corresponding class type for the "clients" table in the database
 * 	
 * 	 Field 		Type 	 Collation 			Null 	Default 							
 *   Client_id 	char(15) latin1_swedish_ci  No                 
 *   Name 		char(30) latin1_swedish_ci  Yes 	NULL                
 *   Surname 	char(30) latin1_swedish_ci  Yes 	NULL                
 *   Address 	char(50) latin1_swedish_ci  Yes 	NULL                
 *   Telephone 	char(30) latin1_swedish_ci  Yes 	NULL                
 *
 */
public class ClientsType {

	String Client_id;                 
	String Name;               
	String Surname;               
	String Address;                
	String Telephone;
	
	/* GETTERS AND SETTERS */
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getClient_id() {
		return Client_id;
	}
	public void setClient_id(String client_id) {
		Client_id = client_id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	

}
