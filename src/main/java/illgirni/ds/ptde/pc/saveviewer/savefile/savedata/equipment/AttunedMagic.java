package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory.InventoryItem;

/**
 * An attuned magic.
 * 
 * @author illgirni
 *
 */
public class AttunedMagic {

  /**
   * The id space of the magic. Should always be {@link ItemIdSpace#OTHER}.
   */
  private ItemIdSpace idSpace;

  /**
   * The id of the magic.
   */
  private long id;

  /**
   * The inventory item corresponding to this magic.
   */
  private InventoryItem inventoryItem;

  /**
   * The id space of the magic. Should always be {@link ItemIdSpace#OTHER}.
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
   * The id of the magic.
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
   * The inventory item corresponding to this magic.
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
