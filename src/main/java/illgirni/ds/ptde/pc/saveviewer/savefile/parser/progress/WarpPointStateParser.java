package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpPointGroupBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpPointState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.WarpPointStateDefinition;

/**
 * Parses the warp point unlocked information for a single warp point from a "warp point unlocked
 * bit block" containing information for possibly multiple warp points.
 * 
 * @author illgirni
 *
 */
@Bean
public class WarpPointStateParser extends AbstractSaveElementParser {

  /**
   * Parses the warp point unlocked information for the warp point definition from the bit block.
   * 
   * @param warpPointStateDefinition The warp point definition.
   * @param warpPointGroupBitBlock The "warp points bit block".
   * @return The unlocked information for the warp point.
   */
  public WarpPointState parse(WarpPointStateDefinition warpPointStateDefinition,
      WarpPointGroupBitBlock warpPointGroupBitBlock) {
    final WarpPointState warpPointState = new WarpPointState();

    warpPointState.setBonfire(warpPointStateDefinition.getRepresentor());
    warpPointState.setUnlocked(
        warpPointGroupBitBlock.getBlockData()[warpPointStateDefinition.getBitOffset()]);

    return warpPointState;
  }

}
