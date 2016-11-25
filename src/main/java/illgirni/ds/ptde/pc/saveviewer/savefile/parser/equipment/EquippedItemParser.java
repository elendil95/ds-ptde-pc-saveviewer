package illgirni.ds.ptde.pc.saveviewer.savefile.parser.equipment;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.EquippedItem;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment.EquippedItemDefinition;

/**
 * Parses a single {@link EquippedMagic} from the blocks with the item id and inventory pointer.
 * 
 * @author illgirni
 *
 */
@Bean
public class EquippedItemParser extends AbstractSaveElementParser {
    
    /**
     * Uses the item definition to parse the corresponding {@link EquippedItem} from the id block and inventory pointer block.
     * Returns {@code null} when the equipment slot is empty.
     * 
     * @param itemDefinition The item definition.
     * @param itemIdsBlock The block with the item ids.
     * @param inventoryIndexesBlock The block with the inventory pointers.
     * @return The EquippedItem; {@code null} when equipment slot is empty.
     */
    public EquippedItem parse(final EquippedItemDefinition itemDefinition, final ByteBlock itemIdsBlock, final ByteBlock inventoryIndexesBlock) {
        final long itemId = blockSectionParser.parse(itemDefinition.getItemIdDefinition(), itemIdsBlock);
        
        if (itemId != itemDefinition.getEmptySlotIndicator()) {
            final EquippedItem item = new EquippedItem();
            
            item.setIdSpace(itemDefinition.getIdSpace());
            item.setId(itemId);
            item.setInventoryIndex(blockSectionParser.parse(itemDefinition.getInventoryIndexDefinition(), inventoryIndexesBlock));
            
            return item;
            
        } else {
            return null;
        }
        
    }
    
}
