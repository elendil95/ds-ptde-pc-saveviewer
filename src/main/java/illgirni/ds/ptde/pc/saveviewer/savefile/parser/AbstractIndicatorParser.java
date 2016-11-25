package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats.IndicatorStatDefinition;

/**
 * Base for parsers that need to parse bytes which represent indicator values (i.e. enumerated values).
 * 
 * @author illgirni
 *
 * @param <T> The type that of the parsed indicator value.
 */
public abstract class AbstractIndicatorParser<T> extends AbstractSaveElementParser {
    
    /**
     * The generic definition of an indicator (single) byte block.
     */
    @Inject
    private IndicatorStatDefinition indicatorStatDefinition;
    
    /**
     * Parses the indicator in the byte block (first byte in the block!)
     * 
     * @param indicatorBlock The block with the indicator.
     * @return The parsed value.
     * 
     * @throws UnknownIndicatorException
     */
    public final T parse(final ByteBlock indicatorBlock) throws UnknownIndicatorException {
        final long indicator = blockSectionParser.parse(indicatorStatDefinition.getDefinition(), indicatorBlock);
        
        return parseFromIndicator(indicator);
    }
    
    /**
     * Transforms the save file value to the corresponding internal value.
     * 
     * @param indicator The save file value.
     * @return The internal value.
     * @throws UnknownIndicatorException
     */
    public abstract T parseFromIndicator(final long indicator) throws UnknownIndicatorException;
}
