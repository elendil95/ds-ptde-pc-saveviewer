package illgirni.ds.ptde.pc.saveviewer.savefile.savedata;

/**
 * Definition of a save slot location, length, etc. in the save file.
 */
public class SaveSlotDescriptor {

  /**
   * The actual byte block with this definition's data.
   */
  private ByteBlock dataBlock;

  /**
   * The index of the descriptor.
   */
  private int index;

  /**
   * The offset of the save slot that this descriptor defines.
   */
  private int slotOffset;

  /**
   * The length of the save slot that this descriptor defines.
   */
  private int slotLength;

  /**
   * The offset of the save slot's name that this descriptor defines.
   */
  private int slotNameOffset;

  /**
   * The index of the descriptor.
   */
  public int getIndex() {
    return index;
  }

  /**
   * @see #getIndex()
   */
  public void setIndex(int index) {
    this.index = index;
  }

  /**
   * The actual byte block with this definition's data.
   */
  public ByteBlock getDataBlock() {
    return dataBlock;
  }

  /**
   * @see #getDataBlock()
   */
  public void setDataBlock(ByteBlock data) {
    this.dataBlock = data;
  }

  /**
   * The length of the save slot that this descriptor defines.
   */
  public int getSlotLength() {
    return slotLength;
  }

  /**
   * @see #getSlotLength()
   */
  public void setSlotLength(int slotLength) {
    this.slotLength = slotLength;
  }

  /**
   * The offset of the save slot that this descriptor defines.
   */
  public int getSlotOffset() {
    return slotOffset;
  }

  /**
   * @see #getSlotOffset()
   */
  public void setSlotOffset(int slotOffset) {
    this.slotOffset = slotOffset;
  }

  /**
   * The offset of the save slot's name that this descriptor defines.
   */
  public int getSlotNameOffset() {
    return slotNameOffset;
  }

  /**
   * @see #getSlotNameOffset()
   */
  public void setSlotNameOffset(int slotNameOffset) {
    this.slotNameOffset = slotNameOffset;
  }

}
