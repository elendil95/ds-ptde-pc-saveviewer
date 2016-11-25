package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;

/**
 * Parses the "warping unlocked" information from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class WarpUnlockedParser extends AbstractIndicatorParser<Boolean> {

    /** {@inheritDoc} */
    @Override
    public Boolean parseFromIndicator(long indicator) throws UnknownIndicatorException {
        if (indicator == 0) {
            return false;
        } else if (indicator == 32) { //00100000
            return true;
        } else {
            //TODO log
            System.err.println("Unexpected indicator: " + indicator);
            return false;
        }
    }

}
