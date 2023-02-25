package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * This definition defines the offset of one attuned magic id within the slot content's attuned
 * magics section.
 * <p/>
 * For an attunement slot it is only important to know which magic is attuned in that slot. We don't
 * need to known about the available quantity of that magic in our inventory, because there is
 * always only one in the slot.
 * 
 * @author illgirni
 *
 */
public class AttunedMagicDefinition {

  /**
   * The attunement magic id offset within the attuned magics section.
   */
  private final int attunementSlotOffset;

  /**
   * @param attunementSlotOffset The attunement magic id offset within the attuned magics section.
   */
  public AttunedMagicDefinition(int attunementSlotOffset) {
    this.attunementSlotOffset = attunementSlotOffset;
  }

  /**
   * The actual definition of one attuned magic id within the attuned magics section.
   */
  public ByteBlockSectionDefinition<Long> getMagicIdDefinition() {
    return new ByteBlockSectionDefinition<>(attunementSlotOffset, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The inventory item type.
   */
  public ItemIdSpace getIdSpace() {
    return ItemIdSpace.OTHER;
  }

  /**
   * The "id" indicating that the attunement slot is empty.
   */
  public long getEmptySlotIndicator() {
    return Long.parseLong("FFFFFFFF", 16);
  }

}
