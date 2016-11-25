package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;

/**
 * Base for parsers of elements that contain other elements that need to be parsed.
 * 
 * @author illgirni
 *
 */
public abstract class AbstractSaveElementParser {
    
    /**
     * The generic byte block parser.
     */
    @Inject
    protected ByteBlockSectionParser blockSectionParser;
}
