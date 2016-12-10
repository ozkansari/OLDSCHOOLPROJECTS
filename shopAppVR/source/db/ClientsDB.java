package db;
/**
 * 
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.PreparedStatement;

import admin.ClientsType;


/**
 * @author ozkansari
 *
 */
public class ClientsDB {
	
	Connection dbConnection;
	
    /**
     *
     */
	public ClientsDB(Connection newDbConnection)
	{
		dbConnection = newDbConnection;
	}
	
    /**
     *	Inserts new client to database 
     */
	public boolean insertNewClient( ClientsType newClient )
	{
		int numrows=0;
		
		try {
			
			/* Prepare SQL statement */
			PreparedStatement addClient = dbConnection.prepareStatement(
					" INSERT INTO `clients` ( `Client_id` , `Name` , `Surname` , `Address` , `Telephone` ) " +
					" VALUES (\"" + 
								newClient.getClient_id() + "\",\"" + 
								newClient.getName() + "\",\"" + 
								newClient.getSurname() + "\",\"" + 
								newClient.getAddress() + "\",\"" + 
								newClient.getTelephone() +
					"\" ); "
			);
			
			/* Execute prepared statement */
			numrows = addClient.executeUpdate();

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

}
