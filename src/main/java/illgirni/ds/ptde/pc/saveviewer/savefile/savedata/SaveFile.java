package illgirni.ds.ptde.pc.saveviewer.savefile.savedata;

import java.util.ArrayList;
import java.util.List;

/**
 * Our internal representation of a save file.
 * 
 * @author illgirni
 *
 */
public class SaveFile {
    
    /**
     * The actual bytes of the save file.
     */
    private ByteBlock saveFileData;
    
    /**
     * The save slot definitions in the save file.
     */
    private List<SaveSlotDescriptor> saveSlotDescriptors = new ArrayList<>();
    
    /**
     * The save file's save slots. A slot may be empty. It should still be in here.
     */
    private List<SaveSlot> saveSlots = new ArrayList<>();
    
    /**
     * If the save file contains illegal data.
     */
    private boolean erroneous = false;
    
    /**
     * The actual bytes of the save file.
     */
    public ByteBlock getSaveFileData() {
        return saveFileData;
    }
    
    /**
     * @see #getSaveFileData()
     */
    public void setSaveFileData(byte[] saveFileData) {
        this.saveFileData = new ByteBlock(saveFileData, 0);
    }
    
    /**
     * Adds a save slot definition to the save file.
     */
    public void addSaveSlotDescriptor(final SaveSlotDescriptor saveGame) {
        if (!saveSlotDescriptors.contains(saveGame)) {
            saveSlotDescriptors.add(saveGame);
        }
    }
    
    /**
     * Removes a save slot definition form the save file.
     */
    public void removeSaveSlotDescriptor(final SaveSlotDescriptor saveGame) {
        saveSlotDescriptors.remove(saveGame);
    }
    
    /**
     * All the save file's save slot definitions.
     */
    public List<SaveSlotDescriptor> getSaveSlotDescriptors() {
        return saveSlotDescriptors;
    }
    
    /**
     * Adds a save slot to the save file.
     */
    public void addSaveSlot(final SaveSlot saveSlot) {
        if (!saveSlots.contains(saveSlot)) {
            saveSlots.add(saveSlot);
        }
    }
    
    /**
     * Removes a save slot from the save file.
     */
    public void removeSaveSlot(final SaveSlot saveSlot) {
        saveSlots.remove(saveSlot);
    }
    
    /**
     * All the save file's save slots.
     */
    public List<SaveSlot> getSaveSlots() {
        return saveSlots;
    }
    
    /**
     * If the save file contains illegal data.
     */
    public boolean isErroneous() {
        return erroneous;
    }
    
    /**
     * @see #isErroneous()
     */
    public void setErroneous(boolean erroneous) {
        this.erroneous = erroneous;
    }
     
}
