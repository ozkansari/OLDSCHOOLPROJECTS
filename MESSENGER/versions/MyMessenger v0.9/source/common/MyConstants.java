/*
 * MyConstants.java
 *
 * Created on 23 Dec 2006 Saturday, 06:55
 */

package common;

/**
 * <p>
 * MyConstants holds the program constants
 * </p>
 * 
 * @author ozkansari
 */
public class MyConstants {
    
    public static boolean IS_DEBUG = true;
    
    // for message
    public static final int CLIENT_LIST_MESSAGE = 1000;
    public static final int NOTIFY_ONLINE_CLIENT = 1001;
    public static final int INIT_CLIENT_MESSAGE = 1002;
    public static final int NOTIFY_OFFLINE_CLIENT = 1003;
    public static final int FILE_SEND_MESSAGE = 9001;
    public static final int FILE_RECEIVE_MESSAGE = 9002;
    public static final int FILE_PACKET_MESSAGE = 9003;
    public static final int CLIENT_MESSAGE = 9004;
    public static final int SERVER_MESSAGE = 9005;

    public static int FILE_PACKET_SIZE = 1000000; // 1 MB
}
