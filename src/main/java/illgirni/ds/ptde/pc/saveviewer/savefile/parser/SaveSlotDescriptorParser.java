package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlotDescriptor;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.SaveSlotDescriptorDefinition;

/**
 * Uses the {@link SaveSlotDescriptorDefinition} to transform a block of bytes to a
 * {@link SaveSlotDescriptor}.
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveSlotDescriptorParser extends AbstractSaveElementParser {

  /**
   * The definition of a save slot descriptors contents.
   */
  @Inject
  private SaveSlotDescriptorDefinition slotDescriptorDefinition;

  /**
   * Parses the block of bytes into a {@link SaveSlotDescriptor}.
   * 
   * @param slotDescriptorData The bytes.
   * @param slotIndex The index of the slot in the save file.
   * @return The slot descriptor.
   */
  public SaveSlotDescriptor parse(final ByteBlock slotDescriptorData, int slotIndex) {
    final SaveSlotDescriptor slotDescriptor = new SaveSlotDescriptor();

    slotDescriptor.setDataBlock(slotDescriptorData);
    slotDescriptor.setIndex(slotIndex);
    slotDescriptor.setSlotOffset(blockSectionParser
        .parse(slotDescriptorDefinition.getSlotOffsetDefinition(), slotDescriptorData));
    slotDescriptor.setSlotLength(blockSectionParser
        .parse(slotDescriptorDefinition.getSlotLengthDefinition(), slotDescriptorData));
    slotDescriptor.setSlotNameOffset(blockSectionParser
        .parse(slotDescriptorDefinition.getSlotNameOffsetDefinition(), slotDescriptorData));

    return slotDescriptor;

  }

}
