package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;

/**
 * An item in the inventory.
 * 
 * @author illgirni
 *
 */
public class InventoryItem {

  /**
   * The index/offset in the inventory item list.
   */
  private long index;

  /**
   * The main item type.
   */
  private ItemIdSpace idSpace;

  /**
   * The item id.
   */
  private long id;

  /**
   * The amount of the item in the inventory.
   */
  private long amount;

  /**
   * If the item is usable.
   */
  private boolean enabled;

  /**
   * The current item durability.
   */
  private long durability;

  /**
   * The lost durability.
   */
  private long durabilityLoss;

  /**
   * Sort position in the inventory group.
   */
  private long sorting;

  /**
   * The item name.
   */
  private String name;

  /**
   * The index/offset in the inventory item list.
   */
  public long getIndex() {
    return index;
  }

  /**
   * @see #getIndex()
   */
  public void setIndex(long index) {
    this.index = index;
  }

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
   * The amount of the item in the inventory.
   */
  public long getAmount() {
    return amount;
  }

  /**
   * @see #getAmount()
   */
  public void setAmount(long amount) {
    this.amount = amount;
  }

  /**
   * If the item is usable.
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * @see #isEnabled()
   */
  public void setEnabled(boolean enbaled) {
    this.enabled = enbaled;
  }

  /**
   * The current item durability.
   */
  public long getDurability() {
    return durability;
  }

  /**
   * @see #getDurability()
   */
  public void setDurability(long durability) {
    this.durability = durability;
  }

  /**
   * The lost durability.
   */
  public long getDurabilityLoss() {
    return durabilityLoss;
  }

  /**
   * @see #getDurabilityLoss()
   */
  public void setDurabilityLoss(long durabilityLoss) {
    this.durabilityLoss = durabilityLoss;
  }

  /**
   * Sort position in the inventory group.
   */
  public long getSorting() {
    return sorting;
  }

  /**
   * @see #getSorting()
   */
  public void setSorting(long sorting) {
    this.sorting = sorting;
  }

  /**
   * The item name.
   */
  public String getName() {
    return name;
  }

  /**
   * @see #getName()
   */
  public void setName(String name) {
    this.name = name;
  }

}
