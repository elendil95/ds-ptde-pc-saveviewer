package illgirni.ds.ptde.pc.saveviewer.savefile.savedata;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CharacterStatistics;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.Equipment;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory.Inventory;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.Progress;

/**
 * The actual content (character and playthrough information) of a save slot.
 * 
 * @author illgirni
 *
 */
public class SaveSlotContent {
    
    /**
     * The character stats.
     */
    private CharacterStatistics characterStatistics;
    
    /**
     * The character's inventory.
     */
    private Inventory inventory;
    
    /**
     * The character's equipment.
     */
    private Equipment equipment;
    
    /**
     * The playthrough progress.
     */
    private Progress progress;
    
    /**
     * The checksum over the slot content.
     */
    private byte[] checkSum;
    
    /**
     * We treat the slot content as empty, if it does not have character information.
     */
    public boolean isEmpty() {
        return characterStatistics == null;
    }
    
    /**
     * The character stats.
     */
    public CharacterStatistics getCharacterStatistics() {
        return characterStatistics;
    }
    
    /**
     * @see #getCharacterStatistics()
     */
    public void setCharacterStatistics(CharacterStatistics characterStatistics) {
        this.characterStatistics = characterStatistics;
    }
    
    /**
     * The character's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    /**
     * @see #getInventory()
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    /**
     * The character's equipment.
     */
    public Equipment getEquipment() {
        return equipment;
    }
    
    /**
     * @see #getEquipment()
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
    
    /**
     * The playthrough progress.
     */
    public Progress getProgress() {
        return progress;
    }
    
    /**
     * @see #getProgress()
     */
    public void setProgress(Progress progress) {
        this.progress = progress;
    }
    
    /**
     * The checksum over the slot content.
     */
    public byte[] getCheckSum() {
        return checkSum;
    }
    
    /**
     * @see #getCheckSum()
     */
    public void setCheckSum(byte[] checkSum) {
        this.checkSum = checkSum;
    }
    
}
