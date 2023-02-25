package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.BonfireStrength;

/**
 * Parses the bonfire kindle state from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class BonfireStrengthParser extends AbstractIndicatorParser<BonfireStrength> {

  /** {@inheritDoc} */
  @Override
  public BonfireStrength parseFromIndicator(final long indicator) throws UnknownIndicatorException {
    if (indicator == 0) {
      return BonfireStrength.OFF;

    } else if (indicator == 10) {
      return BonfireStrength.LIT;

    } else if (indicator == 20) {
      return BonfireStrength.KINDLED_1;

    } else if (indicator == 30) {
      return BonfireStrength.KINDLED_2;

    } else if (indicator == 40) {
      return BonfireStrength.KINDLED_3;

    } else {
      throw new UnknownIndicatorException(BonfireStrength.class, indicator);

    }
  }

}
