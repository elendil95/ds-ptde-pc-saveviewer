package illgirni.ds.ptde.pc.saveviewer.ui;

import java.io.File;

/**
 * Base interface for an application with a user interface.
 * 
 * @author illgirni
 *
 */
public interface UIApplication {
    /**
     * Starts the user interface application.
     */
    public void start();
    
    /**
     * The directory containing the setting files of the application.
     */
    public File getSettingsDirectory();
}
