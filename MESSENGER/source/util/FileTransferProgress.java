/*
 * FileTransferProgress.java
 *
 * Created on 14 Jan 2007 Sun, 01:22
 *
 */

package util;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * <p>
 * FileTransferProgress provides the progress bar for the file transfer 
 * </p>
 * 
 * @author ozkansari
 */
public class FileTransferProgress {
    
    public final static int ONE_SECOND = 1000;
    private ProgressMonitor progressMonitor;
    private Timer timer;
    
    /**
     * Creates a new instance of FileTransferProgress
     */
    public FileTransferProgress() {

        
    }
    
    
    /**
     * @param parent where this JProgress is displayed
     * @param taskLength length of the task in bytes
     */
    public void startProgressMonitor( Container parent, int taskLength) {
        
        progressMonitor = new ProgressMonitor( parent,
                "File is being transferred",
                "", 0, taskLength );
        progressMonitor.setProgress(0);
        progressMonitor.setMillisToDecideToPopup( 1 * ONE_SECOND);
    }
    
    /**
     * @param newValue value for the progress
     */
    public void setProgressValue( int newValue )
    {
        progressMonitor.setProgress( newValue);
    }
    
    /**
     * closes the JProgressMonitor
     */
    public void stopProgressMonitor(){
        progressMonitor.close();
    }
    
    /**
     * @param message progress message (eg. 80KB of 1000KB is finshed )
     */
    public void setProgressMessage( String message )
    {
        progressMonitor.setNote( message );
    }

    
}
