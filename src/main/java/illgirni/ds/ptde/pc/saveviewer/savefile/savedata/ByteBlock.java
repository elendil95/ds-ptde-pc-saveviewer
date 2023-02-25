package illgirni.ds.ptde.pc.saveviewer.savefile.savedata;

/**
 * A generic block of bytes.
 * 
 * @author illgirni
 *
 */
public class ByteBlock {
  /**
   * The bytes.
   */
  private final byte[] blockData;

  /**
   * The offset in the block containing this block.
   */
  private final int offset;

  /**
   * @param blockData The bytes.
   * @param offset The offset in the block containing this block.
   */
  public ByteBlock(byte[] blockData, int offset) {
    this.blockData = blockData;
    this.offset = offset;
  }

  /**
   * The bytes.
   */
  public byte[] getBlockData() {
    return blockData;
  }

  /**
   * The offset in the block containing this block.
   */
  public int getOffset() {
    return offset;
  }

  /**
   * The number of bytes in this block.
   */
  public int getLength() {
    return blockData.length;
  }
}
