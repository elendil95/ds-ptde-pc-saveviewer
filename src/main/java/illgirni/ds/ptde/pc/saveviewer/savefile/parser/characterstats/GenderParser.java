package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractIndicatorParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Gender;

/**
 * Parses the gender from the indicator value.
 * 
 * @author illgirni
 *
 */
@Bean
public class GenderParser extends AbstractIndicatorParser<Gender> {
    
    /** {@inheritDoc} */
    @Override
    public Gender parseFromIndicator(final long indicator) throws UnknownIndicatorException {
        if (indicator == 0) {
            return Gender.FEMALE;
        } else if (indicator == 1) {
            return Gender.MALE;
        } else {
            throw new UnknownIndicatorException(Gender.class, indicator);
        }
    }
    
}
