package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpPointGroupBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.WarpPointStateDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.WarpStateDefinition;

/**
 * Parses the information about the (un)locked warp points and if warping is (un)locked in general
 * from the corresponding byte blocks.
 * 
 * @author illgirni
 *
 */
@Bean
public class WarpStateParser extends AbstractSaveElementParser {

  /**
   * The warp (un)locked definition.
   */
  @Inject
  private WarpStateDefinition warpStateDefinition;

  /**
   * The warp point unlocked parser for a single warp point.
   */
  @Inject
  private WarpPointStateParser warpPointStateParser;

  /**
   * The parser to determine if parsing is unlocked in general.
   */
  @Inject
  private WarpUnlockedParser warpUnlockedParser;

  /**
   * Parses the warp points unlocked information form the provided bit blocks and the warping
   * unlocked information from the warp unlocked byte block.
   * 
   * @param warpPointGroupBitBlocks Collection of bit blocks with warp points information.
   * @param warpUnlockedBlock Byte block with the warping unlocked information.
   * @return The warp state of the playthrough.
   * 
   * @throws ParserException
   */
  public WarpState parse(final List<WarpPointGroupBitBlock> warpPointGroupBitBlocks,
      final ByteBlock warpUnlockedBlock) throws ParserException {
    final WarpState warpState = new WarpState();

    // warpState.setWarpingUnlocked(true);
    //
    // for (final WarpUnlockedDefinition unlockedDefinition :
    // warpStateDefinition.getWarpUnlockedDefinitions()) {
    // if (!warpUnlockedBlock[unlockedDefinition.getBitOffset()]) {
    // warpState.setWarpingUnlocked(false);
    // }
    // }

    warpState.setWarpingUnlocked(warpUnlockedParser.parse(warpUnlockedBlock));

    for (final WarpPointStateDefinition warpPointStateDefinition : warpStateDefinition
        .getWarpPointStateDefinitions()) {
      WarpPointGroupBitBlock matchingGroupBitBlock = null;

      for (final WarpPointGroupBitBlock groupBitBlock : warpPointGroupBitBlocks) {
        if (groupBitBlock.getRepresentors().contains(warpPointStateDefinition.getRepresentor())) {
          matchingGroupBitBlock = groupBitBlock;
          break;
        }
      }

      if (matchingGroupBitBlock != null) {
        warpState.addWarpPoint(
            warpPointStateParser.parse(warpPointStateDefinition, matchingGroupBitBlock));
      } else {
        throw new RuntimeException(
            "Missing warp point definition: " + warpPointStateDefinition.getRepresentor());
      }

    }

    return warpState;
  }

}
