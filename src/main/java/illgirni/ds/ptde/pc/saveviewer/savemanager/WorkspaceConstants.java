package illgirni.ds.ptde.pc.saveviewer.savemanager;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

/**
 * Constants for the save viewers workspace.
 * 
 * @author illgirni
 *
 */
public final class WorkspaceConstants {
    
    private WorkspaceConstants() {
    }
    
    /**
     * Name of the system property with default location containig the save viewers workspace 
     * directory (user's home directory).
     */
    public static final String SYSTEM_PROPERTY__DEFAULT_WORKSPACE_LOCATION = "user.home";
    
    /**
     * Name of the system property defining the custom save viewer's workspace directory.
     */
    public static final String SYSTEM_PROPERTY__CUSTOM_WORKSPACE_LOCATION = "workspace";
    
    /**
     * Default name for the directory containing the workspace (content file, exported slots, and settings).
     */
    public static final String DEFAULT_WORKSPACE_DIRECTORY_NAME = "ds1saveviewer";
    
    /**
     * Name of the actual workspace file in the workspace directory.
     */
    public static final String WORKSPACE_FILE_NAME = "dsptde-sv-profile.xml";
    
    /**
     * Name of the directory in the workspace directory containing the exported save slots.
     */
    public static final String WORKSPACE_EXPORTED_SLOTS_DIRECTORY = "exportedslots";
    
    /**
     * Default path at which we expect the dark souls save file. This is:
     * $user.documents/NBGI/DarkSouls/DRAKS0005.sl2
     */
    public static final String DEFAULT_SAVE_FILE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() 
                                                            + File.separator 
                                                            + "NBGI" 
                                                            + File.separator 
                                                            + "DarkSouls" 
                                                            + File.separator 
                                                            + "DRAKS0005.sl2";
    
    /**
     * File extension for an exported save slot file.
     */
    public static final String SAVE_SLOT_FILE_EXTENSION = "sl2char";
    
}
