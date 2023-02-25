package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;

/**
 * A bit block containing the information for a group of warp points if they are unlocked.
 * 
 * @author illgirni
 *
 */
public class WarpPointGroupBitBlock extends AbstractProgressGroupBitBlock<Bonfire> {

  /**
   * @param blockData The bits.
   * @param bonfires The warp points.
   */
  public WarpPointGroupBitBlock(boolean[] blockData, final List<Bonfire> bonfires) {
    super(blockData, bonfires);
  }

}
