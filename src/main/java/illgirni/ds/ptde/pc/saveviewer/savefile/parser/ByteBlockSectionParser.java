package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.DataType;
import illgirni.ds.ptde.pc.saveviewer.utils.ByteUtils;

/**
 * Parses sections of bytes defined by ByteBlocks. I.e. converts the bytes from a ByteBlock
 * to the type defined by a {@link ByteBlockSectionDefinition}.
 */
@Bean
public class ByteBlockSectionParser {
    
    /**
     * Parses a section of a byte block.
     * 
     * @param blockSection The definition of the section to parse.
     * @param byteBlock The block from which to take the bytes.
     * 
     * @return The parsed value.
     */
    public <T> T parse(final ByteBlockSectionDefinition<T> blockSection, final ByteBlock byteBlock) {
        if (blockSection.getLength() > 0) {
            //limited length. parse only a fixed amount of bytes.
            return parse(byteBlock.getBlockData(), blockSection.getOffset(), blockSection.getLength(), blockSection.getType());
            
        } else if (blockSection.getLength() < 0) {
            //unlimited length. data type is the defining thing.
            return parse(byteBlock.getBlockData(), blockSection.getOffset(), blockSection.getType());
            
        } else {
            return null;
        }
        
    }
    
    /**
     * Parses the value starting at {@code offset} from the block data. For target types with fixed
     * length the {@code length} argument is ignored. For target types with variable length stops 
     * after {@code length} bytes (for target type string after {@code length} characters) from the 
     * {@code offset}.
     * 
     * @param blockData The data.
     * @param offset Where in the data the value to parse starts.
     * @param length The number of bytes/characters to parse. 
     * @param type The target type.
     * 
     * @return The parsed value as target type.
     * 
     * @see ByteUtils
     */
    @SuppressWarnings("unchecked")
    private <T> T parse(byte[] blockData, int offset, int length, DataType type) {
        switch (type) {
            case BYTE_BLOCK:
                return (T) ByteUtils.readBlock(blockData, offset, length);
            case STRING_UTF16:
                return (T) ByteUtils.readStringUtf16(blockData, offset, length);
            default:
                return parse(blockData, offset, type);
        }
    }
    
    /**
     * Parses the value starting at {@code offset} from the block data. For target types with fixed
     * length stops after the number of bytes defined by the target type. For target types with variable 
     * length stops parses as much as possible and stops as defined by the type.
     * 
     * @param blockData The data.
     * @param offset Where in the data the value to parse starts.
     * @param type The target type.
     * 
     * @return The parsed value as target type.
     * 
     * @see ByteUtils
     */
    @SuppressWarnings("unchecked")
    private <T> T parse(byte[] blockData, int offset, DataType type) {
        switch (type) {
            case BYTE_BLOCK:
                return (T) ByteUtils.readBlock(blockData, offset);
            case INT_32:
                return (T) ByteUtils.readInt32(blockData, offset);
            case UINT_32:
                return (T) ByteUtils.readUInt32(blockData, offset);
            case UINT_16:
                return (T) ByteUtils.readUInt16(blockData, offset);
            case UINT_8:
                return (T) ByteUtils.readUInt8(blockData, offset);
            case STRING_UTF16:
                return (T) ByteUtils.readStringUtf16(blockData, offset);
            case BOOLEAN:
                return (T) ByteUtils.readBoolean(blockData, offset);
            case BITS_OF_BYTE:
                return (T) ByteUtils.readBitsOfByte(blockData, offset);
            case CHECKSUM: 
                return (T) ByteUtils.readCheckSum(blockData, offset);
            default:
                throw new IllegalArgumentException("Unsupported type to parse: " + type);
        }
    }
    
}
