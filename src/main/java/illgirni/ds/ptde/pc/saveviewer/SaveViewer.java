package illgirni.ds.ptde.pc.saveviewer;

import java.io.File;
import java.util.Locale;

import illgirni.ds.ptde.pc.saveviewer.ioc.InjectionContext;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;
import illgirni.ds.ptde.pc.saveviewer.savemanager.SaveManagerException;
import illgirni.ds.ptde.pc.saveviewer.savemanager.WorkspaceManager;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.SaveWorkspace;
import illgirni.ds.ptde.pc.saveviewer.ui.UIApplication;
import illgirni.ds.ptde.pc.saveviewer.ui.UIStarter;
import illgirni.ds.ptde.pc.saveviewer.ui.controller.MainController;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.BasicDialogs;

/**
 * Entry point into the application. Sets up all the managers, triggers file loading,
 * and starts the UI.
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveViewer implements UIApplication {
    
    /**
     * The workspace manager.
     */
    @Inject
    private WorkspaceManager workspaceManager;
    
    /**
     * Initializes the application context and triggers the actual application start-up.
     * 
     * @param args
     * 
     * @throws SaveManagerException
     */
    public static void main(String[] args) throws SaveManagerException {
        Locale.setDefault(Locale.ENGLISH);
        
        final InjectionContext context = new InjectionContext();
        final SaveViewer saveViewer = context.getBean(SaveViewer.class);
        UIStarter.start(saveViewer);
    }
    
    /**
     * Loads the workspace and the save file. After that starts the main UI.
     */
    public void start() {
        try {
            if (loadWorkspace() && loadSaveFile()) {
                //show main window
                new MainController(workspaceManager).show();
            }
        } catch (SaveManagerException e) {
            BasicDialogs.showErrorDialog("Something happened that shouldn't have.", e.getMessage(), e);
        }
    }
    
    /**
     * The directory containing the user settings files. This is actually just the workspace directory.
     * 
     * @see WorkspaceManager#getWorkspacePath()
     */
    @Override
    public File getSettingsDirectory() {
        try {
            final File workspacePath = workspaceManager.getWorkspacePath();
            
            //copy, so that no one modifies this.
            return new File(workspacePath.getAbsolutePath());
            
        } catch (SaveManagerException e) {
            //Discard silently. This only means we haven't set the workspace up yet or user provided non-existing path. 
            return null;
        }
    }
    
    /**
     * Loads the workspace.
     * 
     * @return {@code true} hen the workspace could be loaded; {@code false} otherwise.
     */
    private boolean loadWorkspace() {
        try {
            workspaceManager.getWorkspace();
            return true;
            
        } catch (SaveManagerException e) {
            BasicDialogs.showErrorDialog("Could not load workspace.", e.getMessage(), e);
            return false;
        }
        
    }
    
    /**
     * Loads the workspace's save file. Gives the user the option to choose a different save file
     * if the one defined in the workspace cannot be loaded.
     * 
     * @return {@code true} when the save file was loaded.
     * 
     * @throws SaveManagerException
     */
    private boolean loadSaveFile() throws SaveManagerException {
        boolean saveFileLoaded = false;
        final SaveWorkspace workspace = workspaceManager.getWorkspace();
        
        while (!saveFileLoaded) {
            try {
                workspaceManager.loadSaveFile();
                
                final SaveFile saveFile = workspace.getSaveFile();
                
                if (saveFile.isErroneous()) {
                    BasicDialogs.showErrorDialog("The save file contains errors. Please choose another one.");
                    saveFileLoaded = false;
                    
                } else {
                    saveFileLoaded = true;
                    workspaceManager.saveWorkspace();
                    
                }
                
            } catch (SaveManagerException e) {
                BasicDialogs.showWarnDialog("Could not load the save file. Please choose another one.", e.getMessage());
                saveFileLoaded = false; 
            }
            
            if (!saveFileLoaded) {
                final String saveFilePath = BasicDialogs.showSaveFilePathDialog(workspace.getSaveFilePath(), "DRAKS0005.sl2");
                
                if (saveFilePath != null) {
                    workspace.setSaveFilePath(saveFilePath);
                    
                } else {
                    //Stop when nothing was selected.
                    break;
                }
            }
            
        }
        
        return saveFileLoaded;
    }

}
