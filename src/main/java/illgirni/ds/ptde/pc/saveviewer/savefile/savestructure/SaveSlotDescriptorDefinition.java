package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * For a definition of a save slot in the save file this defines where to find and of which type the 
 * elements of the slot definition are within a single slot definition byte block.
 * <p/>
 * We are interested in three values of these blocks:
 * <ol>
 *  <li><i>Slot Name Offset:</i> Offset of the name of a save slot (not the name of the slot's character!) in the save file.</li>
 *  <li><i>Offset of the Slot:</i> Offset of the save slot within the save file.</li>
 *  <li><i>Slot length:</i> The length of the slot in bytes. This is always the same value!</li>
 * </ol>Structure definition of the definition of a slot's location, size, and name in the save file.
 */
@Bean
public class SaveSlotDescriptorDefinition {
    
    /**
     * The section in the slot definition for the slot name offset.
     */
    public ByteBlockSectionDefinition<Integer> getSlotNameOffsetDefinition() {
        return new ByteBlockSectionDefinition<>(20, 4, JavaTypeToDataType.INT_32);
    }
    
    /**
     * The section in the slot definition with the slot offset in the save file.
     */
    public ByteBlockSectionDefinition<Integer> getSlotOffsetDefinition() {
        return new ByteBlockSectionDefinition<>(16, 4, JavaTypeToDataType.INT_32);
    }
    
    /**
     * The section in the slot definition with the slot length.
     */
    public ByteBlockSectionDefinition<Integer> getSlotLengthDefinition() {
        return new ByteBlockSectionDefinition<>(8, 4, JavaTypeToDataType.INT_32);
    }
    
    
}
