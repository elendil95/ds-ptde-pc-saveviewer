package illgirni.ds.ptde.pc.saveviewer.ui;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Sets up the application to start the user interface. Wraps an {@link UIApplication} 
 * as singleton and starts it after initializing everything needed for a user interface.
 * <p/>
 * Provides static access to a number of properties of the started application and user 
 * interface "resources".
 * 
 * @author illgirni
 *
 */
public class UIStarter extends Application {
    
    /**
     * If the UI resources have been prepared.
     */
    private static boolean started = false;
    
    /**
     * The started UI application (singleton).
     */
    private static UIApplication application;
    
    /**
     * The representation of the "UI resources".
     */
    private static Application ui;
    
    /**
     * Starts the singleton {@link UIApplication}
     * 
     * @see UIApplication#start()
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        if (ui == null) {
            ui = this;
            application.start();
        }
    }
    
    /**
     * If the UI resources have not yet been prepared starts the given application
     * after preparing the resources.
     *  
     * @param application The application to start.
     */
    public static void start(UIApplication application) {
        if (!started) {
            UIStarter.started = true;
            UIStarter.application = application;
            
            UIStarter.launch();
        }
    }
    
    /**
     * If the UI resources have been prepared.
     */
    public static boolean isStarted() {
        return started;
    }
    
    /**
     * Opens the given URL.
     */
    public static void openUrl(String url) {
        if (!started || ui == null) {
            throw new RuntimeException("UI has not started");
        }
        
        ui.getHostServices().showDocument(url);
    }
    
    /**
     * The settings director of the started {@link UIApplication}.
     */
    public static File getApplicationSettingsDirectory() {
        return application.getSettingsDirectory();
    }
    
}
