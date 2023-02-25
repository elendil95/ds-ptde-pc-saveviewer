package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;

/**
 * Parses the cured state from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class CursedParser extends AbstractIndicatorParser<Boolean> {

  /** {@inheritDoc} */
  @Override
  public Boolean parseFromIndicator(final long indicator) {
    return indicator > 0;
  }

}
