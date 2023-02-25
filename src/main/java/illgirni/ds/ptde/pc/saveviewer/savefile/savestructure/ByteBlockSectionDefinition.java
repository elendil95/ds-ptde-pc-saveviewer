package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;

/**
 * General definition of a block of bytes in a binary file. The data type of the block's data is
 * also part of this definition: Once as plain Java type and once as type as defined in the binary
 * binary file (e.g. long vs. unsigned int 32).
 * 
 * @author illgirni
 *
 * @param <T> The plain Java type a parser should return for this block.
 */
public class ByteBlockSectionDefinition<T> {

  /**
   * The offset of the block in the binary data.
   */
  private final int offset;

  /**
   * For a String this should be the number of characters and not the number of bytes! Otherwise
   * this should be the number of bytes in the block. A negative value means "as much as possible".
   */
  private final int length;

  /**
   * The binary data type.
   */
  private final DataType type;

  /**
   * @param offset Block offset in the binary data.
   * @param length Number of bytes in the block.
   * @param type Mapping between plain Java type and binary file data type.
   */
  public ByteBlockSectionDefinition(final int offset, final int length,
      final JavaTypeToDataType<T> type) {
    this.offset = offset;
    this.length = length;
    this.type = type.getType();
  }

  /**
   * The offset of the block in the binary data.
   */
  public int getOffset() {
    return offset;
  }

  /**
   * For a String this should be the number of characters and not the number of bytes! Otherwise
   * this should be the number of bytes in the block. A negative value means "as much as possible".
   */
  public int getLength() {
    return length;
  }

  /**
   * The binary data type.
   */
  public DataType getType() {
    return type;
  }

  /**
   * Mappings of all the "interesting" types. Introduced for compile time mapping between plain Java
   * type and binary data type ({@link DataType}).
   * <p/>
   * This is created to try out how to get compile time safety with generics and enums. We could
   * actually get rid of {@link DataType}.
   * 
   * @author illgirni
   *
   * @param <E> The plain Java type.
   */
  public static class JavaTypeToDataType<E> {

    /**
     * Bits of a single bytes vs. boolean array.
     */
    public static JavaTypeToDataType<boolean[]> BITS_OF_BYTE =
        new JavaTypeToDataType<>(DataType.BITS_OF_BYTE);

    /**
     * Byte representing a true/false value vs. boolean.
     */
    public static JavaTypeToDataType<Boolean> BOOLEAN = new JavaTypeToDataType<>(DataType.BOOLEAN);

    /**
     * Block to block.
     */
    public static JavaTypeToDataType<ByteBlock> BYTE_BLOCK =
        new JavaTypeToDataType<>(DataType.BYTE_BLOCK);

    /**
     * MD5 checksum vs. byte array.
     */
    public static JavaTypeToDataType<byte[]> CHECKSUM = new JavaTypeToDataType<>(DataType.CHECKSUM);

    /**
     * 4 byte signed Integer vs. Integer.
     */
    public static JavaTypeToDataType<Integer> INT_32 = new JavaTypeToDataType<>(DataType.INT_32);

    /**
     * UTF-16 (little endian) encoded block vs. String.
     */
    public static JavaTypeToDataType<String> STRING_UTF16 =
        new JavaTypeToDataType<>(DataType.STRING_UTF16);

    /**
     * 4 byte unsigned integer vs. Long.
     */
    public static JavaTypeToDataType<Long> UINT_32 = new JavaTypeToDataType<>(DataType.UINT_32);

    /**
     * 2 byte unsigned integer vs. Long.
     */
    public static JavaTypeToDataType<Long> UINT_16 = new JavaTypeToDataType<>(DataType.UINT_16);

    /**
     * 1 byte unsigned integer vs. Long.
     */
    public static JavaTypeToDataType<Long> UINT_8 = new JavaTypeToDataType<>(DataType.UINT_8);

    /**
     * The enum value representing the binary type.
     */
    private final DataType type;

    /**
     * @param type The enum value representing the binary type.
     */
    private JavaTypeToDataType(final DataType type) {
      this.type = type;
    }

    /**
     * The enum value representing the binary type.
     */
    public DataType getType() {
      return type;
    }
  }

}
