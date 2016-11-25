package illgirni.ds.ptde.pc.saveviewer.savemanager;

/**
 * An exception to be thrown by the save manager in various error cases.
 * 
 * @author illgirni
 *
 */
public class SaveManagerException extends Exception {
    
    /**
     * serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * @param message Error message.
     */
    public SaveManagerException(final String message) {
        super(message);
    }
    
    /**
     * @param message Error message.
     * @param cause Cause of the exception.
     */
    public SaveManagerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
