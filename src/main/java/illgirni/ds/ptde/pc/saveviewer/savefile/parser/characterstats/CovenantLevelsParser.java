package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CovenantLevel;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats.CovenantLevelsDefinition;

/**
 * Parses the progress of the "levelable" covenants from the corresponding byte section.
 * 
 * @author illgirni
 *
 */
@Bean
public class CovenantLevelsParser extends AbstractSaveElementParser {

  /**
   * The definition of the partitioning of the covenant levels definition.
   */
  @Inject
  private CovenantLevelsDefinition covenantLevelsDefinition;

  /**
   * Parses the covenant levels from the byte block.
   */
  public List<CovenantLevel> parse(final ByteBlock covenantLevelsBlock) {
    final List<CovenantLevel> covenantLevels = new ArrayList<>();

    for (final Covenant covenant : Covenant.values()) {
      final ByteBlockSectionDefinition<Long> covenantLevelDefinition =
          covenantLevelsDefinition.getCovenantLevelDefinition(covenant);
      final long covenantLevel;

      if (covenantLevelDefinition != null) {
        covenantLevel = blockSectionParser.parse(covenantLevelDefinition, covenantLevelsBlock);

      } else {
        covenantLevel = 0;
      }

      covenantLevels.add(new CovenantLevel(covenant, covenantLevel));
    }

    return covenantLevels;
  }

}
