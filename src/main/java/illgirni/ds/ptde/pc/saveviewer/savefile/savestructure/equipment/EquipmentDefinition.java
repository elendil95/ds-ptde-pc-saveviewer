package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The equipment of the character is distributed over four sections in the slot content:
 * <ol>
 *  <li>equipped accessories inventory pointers</li>
 *  <li>equipped accessories item ids</li>
 *  <li>equipped consumables</li>
 *  <li>equipped/attuned magics</li>
 * </ol>
 * 
 * For an empty "equipment slot" there is always a special value/id indicating that the 
 * slot is empty. The value is different for different accessory types.
 * 
 * @author illgirni
 *
 */
@Bean
public class EquipmentDefinition {
    
    /**
     * Indicator for an empty weapon slot.
     */
    private static final long BARE_FIRST_INDICATOR = Long.parseLong("000DBBA0", 16);
    
    /**
     * Indicator for an empty ammunition slot.
     */
    private static final long NO_AMMUNITION_INDICATOR = Long.parseLong("FFFFFFFF", 16);
    
    /**
     * Indicator for an empty ring slot.
     */
    private static final long NO_RING_INDICATOR = Long.parseLong("FFFFFFFF", 16);
    
    /**
     * Indicator for an empty consumable slot.
     */
    private static final long NO_CONSUMABLE_INDICATOR = Long.parseLong("FFFFFFFF", 16);
    
    /**
     * The maximum number of attunable magics (attunement slots).
     */
    private static final int ATTUNED_MAGIC_BLOCKS = 12;
    
    /**
     * Length of an attunement slot block.
     */
    private static final int ATTUNED_MAGIC_BLOCK_LENGTH = 8;
    
    /**
     * The section within the slot content containing the equipped accessory ids
     * and consumables.
     */
    public ByteBlockSectionDefinition<ByteBlock> getEquipmentItemIdsDefinition() {
        return new ByteBlockSectionDefinition<>(624, 80, JavaTypeToDataType.BYTE_BLOCK);
    }
    
    /**
     * The section within the slot content containing the inventory pointers for the equipped accessories
     * .
     */
    public ByteBlockSectionDefinition<ByteBlock> getEquipmentInventoryIndexesDefinition() {
        return new ByteBlockSectionDefinition<>(516, 80, JavaTypeToDataType.BYTE_BLOCK);
    }
    
    /**
     * The section within the slot content containing the attuned magics.
     */
    public ByteBlockSectionDefinition<ByteBlock> getAttunedMagicDefinition() {
        return new ByteBlockSectionDefinition<>(58064, 96, JavaTypeToDataType.BYTE_BLOCK);
    }
    
    /**
     * The attunement slot sections within the attuned magic section.
     */
    public List<AttunedMagicDefinition> getAttunedMagicDefinitions() {
        final List<AttunedMagicDefinition> attunedMagicDefinitions = new ArrayList<>();
        
        for (int attunedMagicIndex = 0; attunedMagicIndex < ATTUNED_MAGIC_BLOCKS; attunedMagicIndex++) {
            attunedMagicDefinitions.add(new AttunedMagicDefinition(attunedMagicIndex * ATTUNED_MAGIC_BLOCK_LENGTH));
        }
        
        return attunedMagicDefinitions;
    }
    
    /**
     * The accessory definition for the main left hand weapon.
     */
    public EquippedItemDefinition getLeftWeapon1Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 0, 0, BARE_FIRST_INDICATOR);
    }
    
    /**
     * The accessory definition for the main right hand weapon.
     */
    public EquippedItemDefinition getRightWeapon1Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 4, 4, BARE_FIRST_INDICATOR);
    }
    
    /**
     * The accessory definition for the secondary left hand weapon.
     */
    public EquippedItemDefinition getLeftWeapon2Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 8, 8, BARE_FIRST_INDICATOR);
    }
    
    /**
     * The accessory definition for the secondary right hand weapon.
     */
    public EquippedItemDefinition getRightWeapon2Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 12, 12, BARE_FIRST_INDICATOR);
    }
    
    /**
     * The accessory definition for the main arrows.
     */
    public EquippedItemDefinition getArrows1Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 16, 16, NO_AMMUNITION_INDICATOR);
    }
    
    /**
     * The accessory definition for the main bolts.
     */
    public EquippedItemDefinition getBolts1Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 20, 20, NO_AMMUNITION_INDICATOR);
    }
    
    /**
     * The accessory definition for the secondary arrows.
     */
    public EquippedItemDefinition getArrows2Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 24, 24, NO_AMMUNITION_INDICATOR);
    }
    
    /**
     * The accessory definition for the secondary arrows.
     */
    public EquippedItemDefinition getBolts2Definition() {
        return new EquippedItemDefinition(ItemIdSpace.WEAPONRY, 28, 28, NO_AMMUNITION_INDICATOR);
    }
    
    /**
     * The accessory definition for the head gear.
     */
    public EquippedItemDefinition getHeadDefinition() {
        return new EquippedItemDefinition(ItemIdSpace.ARMOR, 32, 32, Long.parseLong("000DBBA0", 16));
    }
    
    /**
     * The accessory definition for the chest/body gear.
     */
    public EquippedItemDefinition getBodyDefinition() {
        return new EquippedItemDefinition(ItemIdSpace.ARMOR, 36, 36, Long.parseLong("000DBF88", 16));
    }
    
    /**
     * The accessory definition for the hand gear.
     */
    public EquippedItemDefinition getHandsDefinition() {
        return new EquippedItemDefinition(ItemIdSpace.ARMOR, 40, 40, Long.parseLong("000DC370", 16));
    }
    
    /**
     * The accessory definition for the legs/feet gear.
     */
    public EquippedItemDefinition getLegsDefinition() {
        return new EquippedItemDefinition(ItemIdSpace.ARMOR, 44, 44, Long.parseLong("000DC758", 16));
    }
    
    /**
     * The accessory definition for the main ring.
     */
    public EquippedItemDefinition getRing1Definition() {
        return new EquippedItemDefinition(ItemIdSpace.RINGS, 52, 52, NO_RING_INDICATOR);
    }
    
    /**
     * The accessory definition for the secondary ring.
     */
    public EquippedItemDefinition getRing2Definition() {
        return new EquippedItemDefinition(ItemIdSpace.RINGS, 56, 56, NO_RING_INDICATOR);
    }
    
    /**
     * The definition for the first equipped consumable.
     */
    public EquippedItemDefinition getConsumable1Definition() {
        return new EquippedItemDefinition(ItemIdSpace.OTHER, 60, 60, NO_CONSUMABLE_INDICATOR);
    }
    
    /**
     * The definition for the second equipped consumable.
     */
    public EquippedItemDefinition getConsumable2Definition() {
        return new EquippedItemDefinition(ItemIdSpace.OTHER, 64, 64, NO_CONSUMABLE_INDICATOR);
    }
    
    /**
     * The definition for the third equipped consumable.
     */
    public EquippedItemDefinition getConsumable3Definition() {
        return new EquippedItemDefinition(ItemIdSpace.OTHER, 68, 68, NO_CONSUMABLE_INDICATOR);
    }
    
    /**
     * The definition for the fourth equipped consumable.
     */
    public EquippedItemDefinition getConsumable4Definition() {
        return new EquippedItemDefinition(ItemIdSpace.OTHER, 72, 72, NO_CONSUMABLE_INDICATOR);
    }
    
    /**
     * The definition for the fifth equipped consumable.
     */
    public EquippedItemDefinition getConsumable5Definition() {
        return new EquippedItemDefinition(ItemIdSpace.OTHER, 76, 76, NO_CONSUMABLE_INDICATOR);
    }
    
}
