package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.inventory;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * Defines all the values hidden in a block of bytes representing an inventory item.
 * 
 * @author illgirni
 *
 */
@Bean
public class InventoryItemDefinition {
    
    /**
     * The main item type. This is a one byte indicator value. Well, actually it's four, but only
     * one byte is used.
     */
    public ByteBlockSectionDefinition<ByteBlock> getIdSpaceDefinition() {
        return new ByteBlockSectionDefinition<>(3, 1, JavaTypeToDataType.BYTE_BLOCK);
    }
    
    /**
     * The id of the item.
     */
    public ByteBlockSectionDefinition<Long> getIdDefinition() {
        return new ByteBlockSectionDefinition<>(4, 4, JavaTypeToDataType.UINT_32);
    }
    
    /**
     * The amount of the item in the inventory. Only really useful for consumable items and magics.
     * Otherwise it's always one.
     */
    public ByteBlockSectionDefinition<Long> getAmountDefinition() {
        return new ByteBlockSectionDefinition<>(8, 4, JavaTypeToDataType.UINT_32);
    }
    
    /**
     * The sort position of the item in the inventory.
     */
    public ByteBlockSectionDefinition<Long> getSortingDefinition() {
        return new ByteBlockSectionDefinition<>(12, 4, JavaTypeToDataType.UINT_32);
    }
    
    /**
     * Indication, if the item is enabled for use.
     */
    public ByteBlockSectionDefinition<Boolean> getEnabledDefinition() {
        return new ByteBlockSectionDefinition<>(16, 4, JavaTypeToDataType.BOOLEAN);
    }
    
    /**
     * The maximum durability of the item. This will of course only be a sensible value, 
     * if the item is equipable gear or a weapon. 
     */
    public ByteBlockSectionDefinition<Long> getDurabilityDefinition() {
        return new ByteBlockSectionDefinition<>(20, 4, JavaTypeToDataType.UINT_32);
    }
    
    /**
     * The current durability of the item. This will of course only be a sensible value, 
     * if the item is equipable gear or a weapon. 
     */
    public ByteBlockSectionDefinition<Long> getDurabilityLossDefinition() {
        return new ByteBlockSectionDefinition<>(24, 4, JavaTypeToDataType.UINT_32);
    }
    
}
