package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * Definition of the main save file structure.
 * <p/>
 * The save file always has a fixed size in bytes - which is 4,326,432 (about 4,2 MByte).
 * <p/>
 * The save file has four main sections that we are interested in:
 * <ol>
 * <li><i>Magic Number:</i> Magic number indicating that this is a dark souls save file and for
 * which version.</li>
 * <li><i>Save Slot Definitions:</i> A listing of where to find the save slots (characters) in the
 * save file. This makes the save file somewhat self-describing.</li>
 * <li><i>Save Slot Names:</i> A listing of names of the save slots. The names do not correspond to
 * the character names!</li>
 * <li><i>Save Slots:</i> A listing of the actual save slots (with character data or empty). The
 * entries in the slot list are separated by twelve bytes with value {@code 0}.</li>
 * </ol>
 * Save slots actually always have the same size. The first slot can always be found at the same
 * position. And there is always the same gap between the slots. This makes the save slot
 * definitions kinda redundant. But it's good for validation.
 * <p/>
 * Curiously the save file contains actually eleven slots and not ten as available in game! The
 * eleventh slot contains the data shown on the character loading screen.
 *
 */
@Bean
public class SaveFileDefinition {

  /**
   * The magic number with which the save file must start.
   */
  public long getRequiredMagicNumber() {
    return 876891714L; // big endian: 0x424E4434; little endian: 0x424e4434 (BND4)
  }

  /**
   * The size (in bytes) that the save file must have.
   */
  public int getRequiredSaveFileSize() {
    return 4326432; // in bytes
  }

  /**
   * The section in the save file containing the magic number (which are the first 4 bytes of the
   * save file).
   */
  public ByteBlockSectionDefinition<Long> getMagicNumberDefinition() {
    return new ByteBlockSectionDefinition<Long>(0, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The section with the definitions of the save slots. This method returns only the definition for
   * the first ten slot definitions/descriptors, because the eleventh is not available in game,
   * which is a waste of about 400 KByte per file...
   */
  public List<ByteBlockSectionDefinition<ByteBlock>> getSlotDescriptorDefinitions() {
    List<ByteBlockSectionDefinition<ByteBlock>> slotDescriptorDescriptors = new ArrayList<>();

    for (int slotIndex = 0; slotIndex < getNumberOfSaveSlots(); slotIndex++) {
      slotDescriptorDescriptors.add(new ByteBlockSectionDefinition<>(
          getSlotDescriptorsOffset() + (slotIndex * getSlotDescriptorLength()),
          getSlotDescriptorLength(), JavaTypeToDataType.BYTE_BLOCK));
    }

    return slotDescriptorDescriptors;
  }

  /**
   * Definition of the data of a slot in the save file.
   * 
   * @param offset The offset of the slot data in the save file.
   * @param length The length of the slot data.
   */
  public ByteBlockSectionDefinition<ByteBlock> getSlotDefinition(final int offset,
      final int length) {
    return new ByteBlockSectionDefinition<>(offset, length, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * How to read the slot name in the save file.
   * 
   * @param offset Offset of the slot name in the save file.
   */
  public ByteBlockSectionDefinition<String> getSlotNameDefinition(final int offset) {
    return new ByteBlockSectionDefinition<>(offset, -1, JavaTypeToDataType.STRING_UTF16);
  }

  /**
   * Definition of the block containing the data for a save slot as shown on the loading screen.
   * 
   * @param slotIndex The index of the slot.
   */
  public ByteBlockSectionDefinition<ByteBlock> getSlotLoadScreenDefinition(int slotIndex) {
    // TODO give the 56 a name
    final int offset =
        getLoadingScreenSlotOffset() + 56 + (slotIndex * getLoadScreenCharacterBlockLength());

    return new ByteBlockSectionDefinition<>(offset, getLoadScreenCharacterBlockLength(),
        JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The offset of the hidden 11th slot containing the load screen data.
   */
  public int getLoadingScreenSlotOffset() {
    return 3933184;
  }

  /**
   * The length of a data block shown on the character loading screen.
   */
  private int getLoadScreenCharacterBlockLength() {
    return 368;
  }

  /**
   * The number of save slots in a save file. A save file always has the same number of slots. But
   * the single slots can be empty.
   */
  private int getNumberOfSaveSlots() {
    return 10;
  }

  /**
   * Offset in the save file at which the slot descriptors can be found.
   */
  private int getSlotDescriptorsOffset() {
    return 64;
  }

  /**
   * Length of a slot descriptor.
   */
  private int getSlotDescriptorLength() {
    return 32;
  }

}
