/*
 * Message.java
 *
 * Created on 02 Jan 2007 Tue, 02:49
 *
 */

package common;

import java.io.File;
import java.util.Vector;

/**
 * <p>
 * Message is the serializable class that holds message content
 * </p>
 * 
 * @author ozkansari
 */
public class Message implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id = -1;
    
    // Message Content
    private String theMsgString = "";
    private Vector theClientListVector = null;
    private ClientInfo theNewClientInfo = null;
   
    // for file transfer
    private byte [] fileByte;
    private String fileName = "";
    
    // Message info
    private int theMessageType = 0;
    private int theSourceId = 0;
    private int theDestinationId = 0;
    private ClientInfo theSourceClientInfo = null;
    private ClientInfo theDestinationClientInfo = null;
            
    /**
     * Creates a new instance of Message as CLIENT_LIST_MESSAGE message 
     */
    public Message( int sourceId, int destinationId, Vector clientListVector ) {
        theSourceId = sourceId;
        theDestinationId = destinationId;
        theMessageType = MyConstants.CLIENT_LIST_MESSAGE;
        theClientListVector = clientListVector;
    }
    
    /**
     * Creates a new instance of Message as NOTIFY_ONLINE_CLIENT message 
     */
    public Message( int sourceId, int destinationId, ClientInfo newClientInfo ) {
        theSourceId = sourceId;
        theDestinationId = destinationId;
        theMessageType = MyConstants.NOTIFY_ONLINE_CLIENT;
        theNewClientInfo = newClientInfo;
    }
    
    /** 
     * Creates a new instance of Message 
     */
    public Message( int sourceId, int destinationId, int messageType,  String msgString ) {
        theMessageType = messageType;
        theMsgString = msgString;
        theSourceId = sourceId;
        theDestinationId = destinationId;
    }
    
    /** 
     * Creates a new instance of Message */
    public Message( ClientInfo sourceClientInfo, ClientInfo destinationClientInfo, int messageType,  String msgString  ) {
        setSourceClientInfo(sourceClientInfo);
        setDestinationClientInfo(destinationClientInfo);
        theMessageType = messageType;
        theMsgString = msgString;
    }
    
    /** 
     * Creates a new instance of Message 
     * */
    public Message(  ) {
        
    }
    
    
    public String toString() {
        
        String message = "";
        
        try {
            
        	// set message info according to the message type
            switch( theMessageType ){
                
                case MyConstants.CLIENT_LIST_MESSAGE:
                    message = "[CLIENT_LIST_MESSAGE  ] - # of online clients : " + theClientListVector.size();
                    break;
                case MyConstants.CLIENT_MESSAGE:
                    message = "[CLIENT_MESSAGE       ] - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + " > " + theMsgString;
                    break;
                case MyConstants.FILE_PACKET_MESSAGE:
                    message = "[FILE_PACKET_MESSAGE - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + "> Packet ID: " + getId();
                    break;
                case MyConstants.FILE_RECEIVE_MESSAGE:
                    message = "[FILE_RECEIVE_MESSAGE ] - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + " > Answer : " + theMsgString ;
                    break;
                case MyConstants.FILE_SEND_MESSAGE:
                    message = "[FILE_SEND_MESSAGE    ] - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + " > File Name: " + fileName + "File Size: " + theMsgString ;
                    break;
                case MyConstants.INIT_CLIENT_MESSAGE:
                    message = "[INIT_CLIENT_MESSAGE  ] - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + " > ";
                    break;
                case MyConstants.NOTIFY_OFFLINE_CLIENT:
                    message = "[NOTIFY_OFFLINE_CLIENT] - The User gone offline : ID: " + theSourceId /* + "USER NAME: " + theSourceClientInfo.getUserName() + "HOSTNAME: " + theSourceClientInfo.getHostname()*/ ;
                    break;
                case MyConstants.NOTIFY_ONLINE_CLIENT:
                    message = "[NOTIFY_ONLINE_CLIENT ] - New User : ID: " + getSourceId() /*+ "USER NAME: " + theSourceClientInfo.getUserName() + "HOSTNAME: " + theSourceClientInfo.getHostname() */ ;
                    break;
                case MyConstants.SERVER_MESSAGE:
                    message = "[SERVER_MESSAGE       ] - TO: " + theDestinationId + " > " + theMsgString;
                    break;
                default:
                    message = "[?] - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + " > ";
            }
            
        } catch( NullPointerException e ){
            message = "[NULL] - FROM: " + "[" + getSourceId() + "]" +  " TO : " + "[" + getDestinationId() + "]" + " > ";
        }
        
        return message ;
    }
    
    /*  ***********************************************************************************************/
    /*	-- GETTER AND SETTER METHODS -----------------------------------------------------------------*/
    /*  ***********************************************************************************************/

    public String getMsgString() {
        return theMsgString;
    }

    public void setMsgString(String msgString) {
        this.theMsgString = msgString;
    }

    public int getMessageType() {
        return theMessageType;
    }

    public void setMessageType(int messageType) {
        this.theMessageType = messageType;
    }

    public int getSourceId() {
        return theSourceId;
    }

    public void setSourceId(int sourceId) {
        this.theSourceId = sourceId;
    }

    public int getDestinationId() {
        return theDestinationId;
    }

    public void setDestinationId(int destinationId) {
        this.theDestinationId = destinationId;
    }

    public java.util.Vector getClientListVector() {
        return theClientListVector;
    }

    public void setClientListVector(java.util.Vector clientListVector) {
        this.theClientListVector = clientListVector;
    }

    public ClientInfo getNewClientInfo() {
        return theNewClientInfo;
    }

    public void setNewClientInfo(ClientInfo newClientInfo) {
        this.theNewClientInfo = newClientInfo;
    }

    public ClientInfo getSourceClientInfo() {
        return theSourceClientInfo;
    }

    public void setSourceClientInfo(ClientInfo sourceClientInfo) {
        this.theSourceClientInfo = sourceClientInfo;
    }

    public ClientInfo getDestinationClientInfo() {
        return theDestinationClientInfo;
    }

    public void setDestinationClientInfo(ClientInfo destinationClientInfo) {
        this.theDestinationClientInfo = destinationClientInfo;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
}
