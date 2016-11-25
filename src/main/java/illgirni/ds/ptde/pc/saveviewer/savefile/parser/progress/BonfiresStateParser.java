package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BonfireState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BonfiresState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.BonfireStatesDefinition;

/**
 * Parses the bonfire kindle states from the byte block with the information
 * of the already potentially accessible bonfires in the game.
 * 
 * @author illgirni
 *
 */
@Bean
public class BonfiresStateParser extends AbstractSaveElementParser {
    
    /**
     * The definition of the block partitioning into single bonfires.
     */
    @Inject
    private BonfireStatesDefinition bonfireStatesDefinition;
    
    /**
     * The kindle state parser for a single bonfire.
     */
    @Inject
    private BonfireStateParser bonfireStateParser;
    
    /**
     * Parses the kindle states of the bonfires from the byte block.
     * 
     * @param bonfiresBlock The byte block with (only) the bonfire information.
     * @return The kindle state of the bonfires.
     * 
     * @throws ParserException
     */
    public BonfiresState parse(final ByteBlock bonfiresBlock) throws ParserException {
        final BonfiresState bonfiresState = new BonfiresState();
        
        final int bonfireStateBlockLength = bonfireStatesDefinition.getBonfireStateBlockLength();
        
        //number of bonfire states is dynamic
        for (int offset = 0; offset + bonfireStateBlockLength < bonfiresBlock.getLength(); offset += bonfireStateBlockLength) {
            final ByteBlock bonfireStateBlock = blockSectionParser.parse(bonfireStatesDefinition.getBonfireStateDefinition(offset), bonfiresBlock);
            final BonfireState bonfireState = bonfireStateParser.parse(bonfireStateBlock); 
            
            bonfiresState.addBonfireState(bonfireState);
        }
        
        
        
        return bonfiresState;
    }

}
