package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutsState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.TailcutStateDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.TailcutsStateDefinition;

/**
 * Parses the tail cut information for all tail owners from a collection of tail cut bit blocks.
 * 
 * @author illgirni
 *
 */
@Bean
public class TailcutsStateParser extends AbstractSaveElementParser {
    
    /**
     * The tail cut definitions.
     */
    @Inject
    private TailcutsStateDefinition tailcutsStateDefinition;
    
    /**
     * The "tail cut" state parser.
     */
    @Inject
    private TailcutStateParser tailcutStateParser;
    
    /**
     * Parses the tail cut for the tail owners from the bit blocks.
     * 
     * @param tailcutBitBlocks The "tail cut" bit blocks.
     * @return The parsed tail cuts information.
     */
    public TailcutsState parse(final List<TailcutBitBlock> tailcutBitBlocks) {
        final TailcutsState tailcutsState = new TailcutsState();
        
        for (final TailcutStateDefinition tailcutDefinition : tailcutsStateDefinition.getTailcutDefinitions()) {
            TailcutBitBlock matchingGroupBitBlock = null;
            
            for (final TailcutBitBlock groupBitBlock : tailcutBitBlocks) {
                if (groupBitBlock.getRepresentors().contains(tailcutDefinition.getRepresentor())) {
                    matchingGroupBitBlock = groupBitBlock;
                    break;
                }
            }
            
            if (matchingGroupBitBlock != null) {
                tailcutsState.addTailcutState(tailcutStateParser.parse(tailcutDefinition, matchingGroupBitBlock));
            } else {
                throw new RuntimeException("Missing tailcut definition: " + tailcutDefinition.getRepresentor());
            }
            
        }
        
        return tailcutsState;
    }

}
