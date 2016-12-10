package db;
/*
 * Created on 02 August 2005 Tuesday
 *
 */

/**
 * @author ozkan sari 
 */

import java.sql.*;
import java.sql.DriverManager;


/**
 *
 */
public class DatabaseConn {
    
    private static Connection conn ;
    private static final String DBconnName = "ShopApp/localhost";
    
    public DatabaseConn()
    {
        //makeDbConnection();
        String serverName = "localhost";
        String userName = "root";
        String password = "";
        String dbName = "shopapp";  
        
        String url = "jdbc:mysql://"+ serverName +"/"+ dbName ;
        
        makeDbConnection( url, userName, password );
    }
    
    public Connection getConnection( )
    {
        return conn;
    }
    
    
    /*  ***********************************************************************************************/
    /*	-- makeDbConnection() FUNCTION --------------------------------------------------------------------*/
    /*  ***********************************************************************************************/
    
    public Connection makeDbConnection( String databaseURL, String user, String pw ) {
                
        try {
            // load database driver class
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();

            if( user == null || pw==null ) {
                user = "root";
                pw = "";
            }
            
            // connect to database
            conn = DriverManager.getConnection(databaseURL , user, pw);
            
            // System.out.println("->Database connection established");
            
            return conn;
        }
        // detect problems loading database driver
        catch ( ClassNotFoundException classNotFound ) {
            classNotFound.getMessage();
        }
        // detect database access problems
        catch ( SQLException sqlexception ) {
            sqlexception.getMessage();
        }
        // detect other problems
        catch (Exception e) {
            e.getMessage();
        }
        
        return null;
        
    } // --end function makeDbConnection()-
    
    
    /*  ***********************************************************************************************/
    /*	-- closeConnection() FUNCTION --------------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    public void closeConnection()
    {
        try {
            if( conn == null ) System.out.println( "ERROR" );
            conn.close();
            
            if( conn.isClosed() == false ) {
                System.out.println( "Database cannot be closed!!!");
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
        
    } // -end-method-closeConnection()
    
}
