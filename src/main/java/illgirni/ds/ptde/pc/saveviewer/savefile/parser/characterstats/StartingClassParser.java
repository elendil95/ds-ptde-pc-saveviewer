package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.StartingClass;

/**
 * Parses the starting class from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class StartingClassParser extends AbstractIndicatorParser<StartingClass> {

  /** {@inheritDoc} */
  @Override
  public StartingClass parseFromIndicator(long indicator) throws UnknownIndicatorException {
    if (indicator == 0) {
      return StartingClass.WARRIOR;
    } else if (indicator == 1) {
      return StartingClass.KNIGHT;
    } else if (indicator == 2) {
      return StartingClass.WANDERER;
    } else if (indicator == 3) {
      return StartingClass.THIEF;
    } else if (indicator == 4) {
      return StartingClass.BANDIT;
    } else if (indicator == 5) {
      return StartingClass.HUNTER;
    } else if (indicator == 6) {
      return StartingClass.SORCERER;
    } else if (indicator == 7) {
      return StartingClass.PYROMANCER;
    } else if (indicator == 8) {
      return StartingClass.CLERIC;
    } else if (indicator == 9) {
      return StartingClass.DEPRIVED;
    } else {
      throw new UnknownIndicatorException(StartingClass.class, indicator);
    }
  }

}
