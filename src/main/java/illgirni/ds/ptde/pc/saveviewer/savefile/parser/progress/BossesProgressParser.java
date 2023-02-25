package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossGroupBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossesProgress;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.BossProgressDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.BossesProgressDefinition;

/**
 * Parses the defeat information for all bosses from a collection of boss defeat bit blocks.
 * 
 * @author illgirni
 *
 */
@Bean
public class BossesProgressParser extends AbstractSaveElementParser {

  /**
   * The boss defeat definitions.
   */
  @Inject
  private BossesProgressDefinition bossesProgressDefinition;

  /**
   * The "boss defeated" state parser.
   */
  @Inject
  private BossProgressParser bossProgressParser;

  /**
   * Parses the defeat information for the bosses from the bit blocks.
   * 
   * @param bossGroupBitBlocks The "defeat" bit blocks.
   * @return The parsed defeat information.
   */
  public BossesProgress parse(final List<BossGroupBitBlock> bossGroupBitBlocks) {
    final BossesProgress bossesProgress = new BossesProgress();

    for (final BossProgressDefinition bossProgressDefinition : bossesProgressDefinition
        .getBossProgressDefinitions()) {
      BossGroupBitBlock matchingGroupBitBlock = null;

      for (final BossGroupBitBlock groupBitBlock : bossGroupBitBlocks) {
        if (groupBitBlock.getRepresentors().contains(bossProgressDefinition.getRepresentor())) {
          matchingGroupBitBlock = groupBitBlock;
          break;
        }
      }

      if (matchingGroupBitBlock != null) {
        bossesProgress
            .addBoss(bossProgressParser.parse(bossProgressDefinition, matchingGroupBitBlock));
      } else {
        // TODO throw ParserException instead.
        throw new RuntimeException(
            "Missing boss definition: " + bossProgressDefinition.getRepresentor());
      }

    }

    return bossesProgress;
  }

}
