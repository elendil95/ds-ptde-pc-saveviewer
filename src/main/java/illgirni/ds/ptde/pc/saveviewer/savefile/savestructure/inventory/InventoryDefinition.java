package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.inventory;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The inventory section in the slot content starts always starts at the same offset and always has the same size. The items in 
 * the inventory are all defined with the same number of bytes. For each slot in the inventory an item is defined. The values of 
 * the item define, if the inventory slot is empty or not.
 * <p/>
 * Also, the items in the inventory are simply listed. There are no particular sections for consumables, weapons, magics, etc.
 * 
 * @author illgirni
 *
 */
@Bean
public class InventoryDefinition {
    
    /**
     * The number of item slots in the inventory.
     */
    private static final int ITEM_SLOTS = 2048;
    
    /**
     * The length of a single inventory item slot.
     */
    private static final int ITEM_SLOT_LENGTH = 28;
    
    /**
     * The section in the slot content containing the inventory.
     */
    public ByteBlockSectionDefinition<ByteBlock> getInventoryDefinition() {
        return new ByteBlockSectionDefinition<>(716, 57344, JavaTypeToDataType.BYTE_BLOCK);
    }
    
    /**
     * The definitions of the item slots within the inventory section.
     */
    public List<ByteBlockSectionDefinition<ByteBlock>> getInventoryItemDefinitions() {
        final List<ByteBlockSectionDefinition<ByteBlock>> itemDefinitions = new ArrayList<>(ITEM_SLOTS);
        
        for (int itemIndex = 0; itemIndex < ITEM_SLOTS; itemIndex++) {
            itemDefinitions.add(new ByteBlockSectionDefinition<>(itemIndex * ITEM_SLOT_LENGTH, ITEM_SLOT_LENGTH, JavaTypeToDataType.BYTE_BLOCK));
        }
        
        return itemDefinitions;
    }
}
