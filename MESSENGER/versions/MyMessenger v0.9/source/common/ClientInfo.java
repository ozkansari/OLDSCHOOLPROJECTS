/* ClientInfo.java
 *
 * Created on 9 Jan 2007 Tue, 20:03
 *
 *  @author 		Ozkan SARI
 *  @description 	The ClientInfo object, used to identify a chat user.
 */

package common;

/**
 * <p>
 * ClientInfo is the class that holds client information
 * </p>
 * 
 * @author ozkansari
 */
public class ClientInfo implements java.io.Serializable 
{
    private int userId;
    private String userName;
    private String hostname;
    private boolean isOnline;
    private boolean isConference=false;
    
    public ClientInfo(String userName, int id, String hostname,boolean isOnline) {
        this.setHostname(hostname);
        this.setUserName(userName);
        this.setIsOnline(isOnline);
        this.setUserId( id );
    }
    
    public String toString() {
        return getUserName();
    }

    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isIsConference() {
        return isConference;
    }

    public void setIsConference(boolean isConference) {
        this.isConference = isConference;
    }
}