package illgirni.ds.ptde.pc.saveviewer.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;

/**
 * Utility to convert bytes in byte arrays, with the bytes in little endian order, to other types.
 * 
 * @author illgirni
 *
 */
public class ByteUtils {

  /**
   * Character set name for UTF-216 little endian encoding.
   */
  private static final String CHARSET_UTF_16_LE = "UTF-16LE";

  /**
   * Reads a four byte integer.
   * 
   * @param byteData The array with the integer data.
   * @param offset The offset of the integer data in the byte array.
   * @return The read integer.
   */
  // little endian encoding, with unsigned bytes
  public static Integer readInt32(final byte[] byteData, final int offset) {
    return Byte.toUnsignedInt(byteData[offset + 3]) << 24
        | Byte.toUnsignedInt(byteData[offset + 2]) << 16
        | Byte.toUnsignedInt(byteData[offset + 1]) << 8 | Byte.toUnsignedInt(byteData[offset]);
  }

  /**
   * Reads a four byte unsigned integer as long.
   * 
   * @param byteData The array with the integer data.
   * @param offset The offset of the integer data in the byte array.
   * @return The read unsigned integer.
   */
  // little endian encoding, with unsigned bytes
  public static Long readUInt32(final byte[] byteData, final int offset) {
    return Byte.toUnsignedLong(byteData[offset + 3]) << 24
        | Byte.toUnsignedLong(byteData[offset + 2]) << 16
        | Byte.toUnsignedLong(byteData[offset + 1]) << 8 | Byte.toUnsignedLong(byteData[offset]);
  }

  /**
   * Reads a three byte unsigned integer as long.
   * 
   * @param byteData The array with the integer data.
   * @param offset The offset of the integer data in the byte array.
   * @return The read unsigned integer.
   */
  // little endian encoding, with unsigned bytes
  public static Long readUInt24(final byte[] byteData, final int offset) {
    return Byte.toUnsignedLong(byteData[offset + 2]) << 16
        | Byte.toUnsignedLong(byteData[offset + 1]) << 8 | Byte.toUnsignedLong(byteData[offset]);
  }

  /**
   * Reads a three byte unsigned integer as long.
   * 
   * @param byteData The array with the integer data.
   * @param offset The offset of the integer data in the byte array.
   * @return The read unsigned integer.
   */
  // little endian encoding, with unsigned bytes
  public static Long readUInt16(final byte[] byteData, final int offset) {
    return Byte.toUnsignedLong(byteData[offset + 1]) << 8 | Byte.toUnsignedLong(byteData[offset]);
  }

  /**
   * Reads a one byte unsigned integer as long.
   * 
   * @param byteData The array with the integer data.
   * @param offset The offset of the integer data in the byte array.
   * @return The read unsigned integer.
   */
  // little endian encoding, with unsigned bytes
  public static Long readUInt8(final byte[] byteData, final int offset) {
    return Byte.toUnsignedLong(byteData[offset]);
  }

  /**
   * Reads a block of bytes from the byte array. The data in the block will be a copy of the read
   * section of the array.
   * 
   * @param byteData The byte array.
   * @param offset The offset in the array.
   * @param length The number of bytes to read into the block. When negative reads everything from
   *        the offset to the end of the array.
   * @return The read block.
   */
  public static ByteBlock readBlock(final byte[] byteData, final int offset, final int length) {
    if (length < 0) {
      return readBlock(byteData, offset);

    } else if (length == 0) {
      return new ByteBlock(new byte[0], offset);

    } else {
      return new ByteBlock(Arrays.copyOfRange(byteData, offset, offset + length), offset);

    }

  }

  /**
   * Reads a block of bytes from the byte array. The data in the block will be a copy of the read
   * section of the array. Reads from the offset to the end of the array.
   * 
   * @param byteData The byte array.
   * @param offset The offset in the array.
   * @return The read block.
   */
  public static ByteBlock readBlock(final byte[] byteData, final int offset) {
    return new ByteBlock(Arrays.copyOfRange(byteData, offset, byteData.length), offset);
  }

  /**
   * Reads a boolean value form the one byte in the byte array.
   * 
   * @param byteData The byte array.
   * @param offset The offset of the byte.
   * @return {@code true} when the unsigned integer value of the byte is larger than zero.
   */
  public static Boolean readBoolean(final byte[] byteData, final int offset) {
    return readUInt8(byteData, offset) > 0;
  }

  /**
   * Reads the bits of one byte in the byte array into a boolean array. The resulting array will
   * always have a length of eight. A {@code true} value in the array is equivalent to a
   * {@code 1}-bit.
   * 
   * @param byteData The byte array.
   * @param offset The offset of the byte.
   * @return The bits of the byte as boolean array.
   */
  public static boolean[] readBitsOfByte(final byte[] byteData, final int offset) {
    boolean[] bits = new boolean[8];

    // K.I.S.S.
    String bitString = Long.toBinaryString(readUInt8(byteData, offset));

    while (bitString.length() < 8) {
      bitString = '0' + bitString;
    }

    for (int bitIndex = 0; bitIndex < bits.length; bitIndex++) {
      bits[bitIndex] = bitString.charAt(bitIndex) == '1';
    }

    return bits;
  }

  /**
   * Reads an UTF-16 encoded String from the byte array. Read from the offset until two {qcode
   * 0}-bytes following each other are found.
   * 
   * @param byteData The byte array.
   * @param offset The offset at which the String starts.
   * @return The read String.
   */
  public static String readStringUtf16(final byte[] byteData, final int offset) {
    int stringEnd = offset;
    int stringLength = 0;

    while (stringEnd + 1 < byteData.length
        && !(byteData[stringEnd] == 0 && byteData[stringEnd + 1] == 0)) {
      stringEnd += 2;
      stringLength += 1;
    }

    return readStringUtf16(byteData, offset, stringLength);

  }

  /**
   * Reads an UTF-16 encoded String of fixed length from the byte array.
   * 
   * @param byteData The byte array.
   * @param offset The offset at which the String starts.
   * @param stringLength The number of characters in the String.
   * @return The read String.
   */
  // characters are encoded with UTF-16LE
  public static String readStringUtf16(final byte[] byteData, final int offset,
      final int stringLength) {
    if (stringLength < 0) {
      return readStringUtf16(byteData, offset);
    } else if (stringLength == 0) {
      return "";

    } else {
      try {
        final CharsetDecoder decoder = Charset.forName(CHARSET_UTF_16_LE).newDecoder();
        final CharBuffer stringCharacters =
            decoder.decode(ByteBuffer.wrap(byteData, offset, stringLength * 2));

        return stringCharacters.toString();

      } catch (CharacterCodingException e) {
        throw new RuntimeException(e);
      }

    }
  }

  /**
   * Reads a 16 byte checksum from the byte array.
   * 
   * @param byteData The byte array.
   * @param offset The offset of the checksum in the array.
   * @return The checksum bytes.
   */
  public static byte[] readCheckSum(final byte[] byteData, final int offset) {
    return Arrays.copyOfRange(byteData, offset, offset + 16);
  }

}
