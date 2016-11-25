package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory.InventoryItem;

/**
 * An equipped accessory or consumable.
 * 
 * @author illgirni
 *
 */
public class EquippedItem {
    
    /**
     * The main item type.
     */
    private ItemIdSpace idSpace;
    
    /**
     * The item id.
     */
    private long id;
    
    /**
     * The index of the item in the inventory.
     */
    private long inventoryIndex;
    
    /**
     * The inventory item matching this item.
     */
    private InventoryItem inventoryItem;
    
    /**
     * The main item type.
     */
    public ItemIdSpace getIdSpace() {
        return idSpace;
    }
    
    /**
     * @see #getIdSpace()
     */
    public void setIdSpace(ItemIdSpace idSpace) {
        this.idSpace = idSpace;
    }
    
    /**
     * The item id.
     */
    public long getId() {
        return id;
    }
    
    /**
     * @see #getId()
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * The index of the item in the inventory.
     */
    public long getInventoryIndex() {
        return inventoryIndex;
    }
    
    /**
     * @see #getInventoryIndex()
     */
    public void setInventoryIndex(long inventoryIndex) {
        this.inventoryIndex = inventoryIndex;
    }
    
    /**
     * The inventory item matching this item.
     */
    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }
    
    /**
     * @see #getInventoryItem()
     */
    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
    
}
