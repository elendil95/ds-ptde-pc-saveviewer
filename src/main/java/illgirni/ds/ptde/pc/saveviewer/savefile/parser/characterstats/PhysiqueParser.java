package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Physique;

/**
 * Parses the phasique form the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class PhysiqueParser extends AbstractIndicatorParser<Physique> {

  /** {@inheritDoc} */
  @Override
  public Physique parseFromIndicator(final long indicator) throws UnknownIndicatorException {
    if (indicator == 0) {
      return Physique.AVERAGE;
    } else if (indicator == 1) {
      return Physique.SLIM;
    } else if (indicator == 2) {
      return Physique.VERY_SLIM;
    } else if (indicator == 3) {
      return Physique.LARGE;
    } else if (indicator == 4) {
      return Physique.VERY_LARGE;
    } else if (indicator == 5) {
      return Physique.LARGE_UPPER_BODY;
    } else if (indicator == 6) {
      return Physique.LARGE_LOWER_BODY;
    } else if (indicator == 7) {
      return Physique.TOP_HEAVY;
    } else if (indicator == 8) {
      return Physique.TINY_HEAD;
    } else {
      throw new UnknownIndicatorException(Physique.class, indicator);
    }
  }

}
