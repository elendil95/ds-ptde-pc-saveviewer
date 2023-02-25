package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * A save slot in the save file consists of three main parts (in this order):
 * <ol>
 * <li><i>Checksum:</i> An MD5 checksum over the remainder of the slot (slot content length + slot
 * content).</li>
 * <li><i>Slot content length:</i> The length of the save slot content in bytes. This is always the
 * same value.</li>
 * <li><i>Slot content:</i> The actual character data of the save slot.</li>
 * </ol>
 */
@Bean
public class SaveSlotDefinition {

  /**
   * Save slots always have the same size. This is that size (in bytes).
   */
  public int getRequiredSaveSlotSize() {
    return 393236; // in bytes
  }

  /**
   * The section with the checksum of the whole slot. This are the first sixteen bytes.
   */
  public ByteBlockSectionDefinition<byte[]> getCheckSumDefinition() {
    return new ByteBlockSectionDefinition<>(0, 16, JavaTypeToDataType.CHECKSUM);
  }

  /**
   * The section with length of the actual slot content. This are the first four bytes right after
   * the slots checksum.
   */
  public ByteBlockSectionDefinition<Integer> getSlotContentLengthDefinition() {
    return new ByteBlockSectionDefinition<>(16, 4, JavaTypeToDataType.INT_32);
  }

  /**
   * The section with the actual slot content. Simply the block of remaining bytes in the slot after
   * the slot content length.
   */
  public ByteBlockSectionDefinition<ByteBlock> getSlotContentDefinition() {
    return new ByteBlockSectionDefinition<>(20, -1, JavaTypeToDataType.BYTE_BLOCK);
  }

}
