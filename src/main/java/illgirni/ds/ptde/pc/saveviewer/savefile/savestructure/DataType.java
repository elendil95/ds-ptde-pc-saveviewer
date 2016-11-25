package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure;

/**
 * 
 * Data types of blocks of bytes.
 * 
 * @author illgirni
 *
 */
public enum DataType {
    
    /**
     * Boolean byte.
     */
    BOOLEAN,
    
    /**
     * Unsigned int (1 byte).
     */
    UINT_8,
    
    /**
     * Unsigned int (2 bytes).
     */
    UINT_16,
    
    /**
     * Unsigned int (4 bytes).
     */
    UINT_32,
    
    /**
     * Signed int (4 bytes).
     */
    INT_32,
    
    /**
     * UTF-16 encoded String.
     */
    STRING_UTF16,
    
    /**
     * MD5 checksum.
     */
    CHECKSUM,
    
    /**
     * Simple block of bytes.
     */
    BYTE_BLOCK, 
    
    /**
     * Collection of bits.
     */
    BITS_OF_BYTE
}
