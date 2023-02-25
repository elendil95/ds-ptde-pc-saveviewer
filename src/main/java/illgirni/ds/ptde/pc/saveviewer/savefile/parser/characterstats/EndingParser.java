package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Ending;

/**
 * Parses the last ending from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class EndingParser extends AbstractIndicatorParser<Ending> {

  /** {@inheritDoc} */
  @Override
  public Ending parseFromIndicator(final long indicator) throws UnknownIndicatorException {
    if (indicator == 0) {
      return Ending.NONE;
    } else if (indicator == 1) {
      return Ending.LORD_S_SUCCESSOR;
    } else if (indicator == 2) {
      return Ending.DARK_LORD;
    } else {
      throw new UnknownIndicatorException(Ending.class, indicator);
    }
  }

}
