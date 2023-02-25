package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;

/**
 * Parses the current covenant from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class CovenantParser extends AbstractIndicatorParser<Covenant> {

  /** {@inheritDoc} */
  @Override
  public Covenant parseFromIndicator(final long indicator) throws UnknownIndicatorException {
    if (indicator == 0) {
      return Covenant.NONE;
    } else if (indicator == 1) {
      return Covenant.WAY_OF_WHITE;
    } else if (indicator == 2) {
      return Covenant.PRINESS_S_GUARD;
    } else if (indicator == 3) {
      return Covenant.WARRIOR_OF_SUNLIGHT;
    } else if (indicator == 4) {
      return Covenant.DARKWRAITH;
    } else if (indicator == 5) {
      return Covenant.PATH_OF_THE_DRAGON;
    } else if (indicator == 6) {
      return Covenant.GRAVELORD_SERVANT;
    } else if (indicator == 7) {
      return Covenant.FOREST_HUNTER;
    } else if (indicator == 8) {
      return Covenant.BLADE_OF_THE_DARKMOON;
    } else if (indicator == 9) {
      return Covenant.CHAOS_SERVANT;
    } else {
      throw new UnknownIndicatorException(Covenant.class, indicator);
    }

  }

}
