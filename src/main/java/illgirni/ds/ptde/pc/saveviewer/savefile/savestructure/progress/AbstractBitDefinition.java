package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

/**
 * Base for the definition of simple yes/no values represented with a single bit in the save file.
 * Essentially used to define a binding between a bit and an enumeration value ("representor").
 * 
 * @author illgirni
 *
 * @param <T> The representor type.
 */
public abstract class AbstractBitDefinition<T extends Enum<?>> {

  /**
   * The value for which the bit defines yes or no.
   */
  private final T representor;

  /**
   * The offset of the bit within the byte.
   */
  private final int bitOffset;

  /**
   * @param representor The value for which the bit defines yes or no.
   * @param bitOffset The offset of the bit within the byte.
   */
  public AbstractBitDefinition(final T representor, final int bitOffset) {
    this.representor = representor;
    this.bitOffset = bitOffset;
  }

  /**
   * The value for which the bit defines yes or no.
   */
  public T getRepresentor() {
    return representor;
  }

  /**
   * The offset of the bit within the byte.
   */
  public int getBitOffset() {
    return bitOffset;
  }
}
