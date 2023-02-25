package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.ArrayList;
import java.util.List;

/**
 * Base of a block of bits, which define some kind of game progress in a "yes/no" fashion.
 * 
 * @author illgirni
 *
 * @param <T>
 */
public abstract class AbstractProgressGroupBitBlock<T extends Enum<?>> {

  /**
   * The bits in the block.
   */
  private final boolean[] blockData;

  /**
   * The values for which this block defines the progress.
   */
  private final List<T> representors;

  /**
   * @param blockData The bits in the block.
   * @param representors The values for which this block defines the progress.
   */
  public AbstractProgressGroupBitBlock(boolean[] blockData, final List<T> representors) {
    this.blockData = blockData;
    this.representors = new ArrayList<>(representors);
  }

  /**
   * The bits in the block.
   */
  public boolean[] getBlockData() {
    return blockData;
  }

  /**
   * The values for which this block defines the progress.
   */
  public List<T> getRepresentors() {
    return representors;
  }

}
