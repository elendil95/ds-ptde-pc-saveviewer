package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Gift;

/**
 * Parses the starting gift from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class GiftParser extends AbstractIndicatorParser<Gift> {
    
    /** {@inheritDoc} */
    @Override
    public Gift parseFromIndicator(final long indicator) throws UnknownIndicatorException {
        if (indicator == 0) {
            return Gift.NONE;
        } else if (indicator == 1) {
            return Gift.GODDESS_S_BLESSING;
        } else if (indicator == 2) {
            return Gift.BLACK_FIREBOMB;
        } else if (indicator == 3) {
            return Gift.TWIN_HUMANITIES;
        } else if (indicator == 4) {
            return Gift.BINOCULARS;
        } else if (indicator == 5) {
            return Gift.PENDANT;
        } else if (indicator == 6) {
            return Gift.MASTER_KEY;
        } else if (indicator == 7) {
            return Gift.TINY_BEING_S_RING;
        } else if (indicator == 8) {
            return Gift.OLD_WITCH_S_RING;
        } else {
            throw new UnknownIndicatorException(Gift.class, indicator);
        }
    }
    
}
