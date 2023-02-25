package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;

/**
 * Parses an item main type from an indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class ItemIdSpaceParser extends AbstractIndicatorParser<ItemIdSpace> {

  /** {@inheritDoc} */
  @Override
  public ItemIdSpace parseFromIndicator(final long indicator) throws UnknownIndicatorException {
    if (indicator == 0) {
      return ItemIdSpace.WEAPONRY;
    } else if (indicator == 16) {
      return ItemIdSpace.ARMOR;
    } else if (indicator == 32) {
      return ItemIdSpace.RINGS;
    } else if (indicator == 64) {
      return ItemIdSpace.OTHER;
    } else if (indicator == 255) {
      return null;
    } else {
      throw new UnknownIndicatorException(ItemIdSpace.class, indicator);
    }
  }

}
