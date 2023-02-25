package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.Arrays;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;

/**
 * A bit block containing the information for none tail owner, if the tail has been cut.
 * 
 * @author illgirni
 *
 */
public class TailcutBitBlock extends AbstractProgressGroupBitBlock<TailOwner> {

  /**
   * @param blockData The bits.
   * @param tailOwner The tail owner.
   */
  public TailcutBitBlock(boolean[] blockData, final TailOwner tailOwner) {
    super(blockData, Arrays.asList(tailOwner));
  }

}
