package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * Sub-section definition of a single equipped accessory or consumable within the slot contents
 * accessories and consumables sections. It specifies:
 * <ul>
 * <li>the type of the item</li>
 * <li>the offset of the item id within the equipped item ids section</li>
 * <li>the offset of the inventory pointer within the equipped item inventory pointers section. We
 * need that pointer to determine the quantity of consumables and ammunition.</li>
 * <li>which value indicates that the equipped item slot is empty</li>
 * </ol>
 * 
 * @author illgirni
 *
 */
public class EquippedItemDefinition {

  /**
   * The main item type.
   */
  private final ItemIdSpace idSpace;

  /**
   * The offset of the item id in the item ids section.
   */
  private final int itemIdOffset;

  /**
   * The offset of the inventory pointer in the inventory pointers section.
   */
  private final int inventoryIndexOffset;

  /**
   * The "id" indicating that the equipped itme slot is empty.
   */
  private final long emptySlotIndicator;

  /**
   * @param idSpace The main item type.
   * @param itemIdOffset The offset of the item id in the item ids section.
   * @param inventoryIndexOffset The offset of the inventory pointer in the inventory pointers
   *        section.
   * @param emptySlotIndicator The "id" indicating that the equipped itme slot is empty.
   */
  public EquippedItemDefinition(final ItemIdSpace idSpace, final int itemIdOffset,
      final int inventoryIndexOffset, final long emptySlotIndicator) {
    this.idSpace = idSpace;
    this.itemIdOffset = itemIdOffset;
    this.inventoryIndexOffset = inventoryIndexOffset;
    this.emptySlotIndicator = emptySlotIndicator;
  }

  /**
   * The main item type.
   */
  public ItemIdSpace getIdSpace() {
    return idSpace;
  }

  /**
   * The offset of the item id in the item ids section.
   */
  public ByteBlockSectionDefinition<Long> getItemIdDefinition() {
    return new ByteBlockSectionDefinition<>(itemIdOffset, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The offset of the inventory pointer in the inventory pointers section.
   */
  public ByteBlockSectionDefinition<Long> getInventoryIndexDefinition() {
    return new ByteBlockSectionDefinition<>(inventoryIndexOffset, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The "id" indicating that the equipped itme slot is empty.
   */
  public long getEmptySlotIndicator() {
    return emptySlotIndicator;
  }

}
