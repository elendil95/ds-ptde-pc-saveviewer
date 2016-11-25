package illgirni.ds.ptde.pc.saveviewer.ui.controller;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.SaveFileSlotsPanel;

import javafx.scene.Node;

/**
 * Controller for the panel showing the slots in the save file.
 * 
 * @author illgirni
 *
 */
public class SaveFileSlotsController {
    
    /**
     * The save file slots panel.
     */
    private final SaveFileSlotsPanel slotsPanel = new SaveFileSlotsPanel();
    
    /**
     * @param saveFile The save file for which to show the slots.
     */
    public SaveFileSlotsController(final SaveFile saveFile) {
        refreshSaveSlots(saveFile);
    }
    
    /**
     * Clears the selection in the slot panel.
     */
    public void clearSelection() {
        slotsPanel.clearSelection();
    }
    
    /**
     * Replaces the slots displayed in the panel with the slots from the save file.
     * 
     * @param saveFile The save file.
     */
    public void refreshSaveSlots(final SaveFile saveFile) {
        slotsPanel.setSlots(saveFile.getSaveSlots());
    }
    
    /**
     * The save slot panel.
     */
    public Node getPanel() {
        return slotsPanel;
    }
    
}
