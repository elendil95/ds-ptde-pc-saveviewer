package illgirni.ds.ptde.pc.saveviewer.ui.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import illgirni.ds.ptde.pc.saveviewer.ui.layout.LayoutUtils;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.BasicDialogs;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog.PreferencesDialog;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceSetting;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceValue;

/**
 * Controller for the user preferences dialog.
 * 
 * @author illgirni
 *
 */
public class PreferencesEditController {
    
    /**
     * Opens the preference dialog and waits for it to finish. After which the changes 
     * to the preferences are saved.
     */
    public void show() {
        final PreferencesDialog dialog = new PreferencesDialog();
        final Optional<List<PreferenceValue>> newPreferences = dialog.showAndWait();
        
        if (newPreferences.isPresent()) {
            savePreferences(newPreferences.get());
        }
    }
    
    /**
     * Saves the new preference values.
     * 
     * @param newPreferences The new preference values.
     */
    private void savePreferences(List<PreferenceValue> newPreferences) {
        final Properties windowPreferences = new Properties();
        Integer fontSizePreference = null;
        
        for (final PreferenceValue newPreference : newPreferences) {
            if (!newPreference.getUseDefault().get()) {
                if (newPreference.getPreference() == PreferenceSetting.FONT_SIZE) {
                    //font size needs to be saved separately in a CSS file.
                    fontSizePreference = newPreference.getValue().get();
                    
                } else {
                    windowPreferences.setProperty(newPreference.getPreference().getPreferencesKey(), newPreference.getValue().get() + "");
                }
                
            }
        }
        
        saveWindowPreferences(windowPreferences);
        saveFontPreferences(fontSizePreference);
        LayoutUtils.reloadPreferences();
    }
    
    /**
     * Saves the window size and layout preferences. If all settings are the 
     * default values deletes the existing preferences file instead.
     * 
     * @param windowPreferences The new window size and layout settings.
     */
    private void saveWindowPreferences(final Properties windowPreferences) {
        final File preferencesFile = LayoutUtils.getPreferencesFile();
        
        if (preferencesFile != null) {
            if (!windowPreferences.isEmpty()) {
                OutputStream preferencesOutput = null;
                
                try {
                    preferencesOutput = new BufferedOutputStream(new FileOutputStream(preferencesFile));
                    windowPreferences.store(preferencesOutput, "Window size settings");
                    
                } catch (IOException e) {
                    BasicDialogs.showErrorDialog("Could not save window settings.", e.getMessage(), e);
                    
                } finally {
                    if (preferencesOutput != null) {
                        try {
                            preferencesOutput.close();
                        } catch (IOException e) {
                            //close silently
                        }
                    }
                }
            } else if (preferencesFile.isFile()) {
                preferencesFile.delete();
            }
            
        }
    }
    
    /**
     * Saves the font size preference to a CSS file.
     * 
     * @param fontSizePreference The preferred font size.
     */
    private void saveFontPreferences(Integer fontSizePreference) {
        final File fontSizeFile = LayoutUtils.getFontSizeFile();
        
        if (fontSizeFile != null) {
            final String fontSizeFileContent;
            
            if (fontSizePreference != null) {
                fontSizeFileContent = MessageFormat.format(LayoutUtils.FONT_SIZE_FILE_CONTENT_FORMAT, fontSizePreference);
                
                try {
                    Files.write(fontSizeFile.toPath(), fontSizeFileContent.getBytes());
                } catch (IOException e) {
                    BasicDialogs.showErrorDialog("Could not save font size settings.", e.getMessage(), e);
                }
                
            } else if (fontSizeFile.isFile()) {
                fontSizeFileContent = "";
                
                try {
                    Files.write(fontSizeFile.toPath(), fontSizeFileContent.getBytes());
                } catch (IOException e) {
                    BasicDialogs.showErrorDialog("Could not save font size settings.", e.getMessage(), e);
                }
                
            } 
            
        }
    }

}
