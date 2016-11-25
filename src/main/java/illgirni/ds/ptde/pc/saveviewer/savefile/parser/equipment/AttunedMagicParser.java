package illgirni.ds.ptde.pc.saveviewer.savefile.parser.equipment;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.AttunedMagic;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.equipment.AttunedMagicDefinition;

/**
 * Parses a single {@link AttunedMagic} from a byte block containing all the attunement slots.
 * 
 * @author illgirni
 *
 */
@Bean
public class AttunedMagicParser extends AbstractSaveElementParser {
    
    /**
     * Uses the definition to parse a single attuned magic from the byte block.
     * If the attunement slot corresponding to definition is empty this method return {@code null}.
     * 
     * @param attunedMagicDefinition The attuned magic / attunement slot definition.
     * @param attunedMagicsBlock The byte block with the attunement data.
     * @return The parses AttunedMagic; {@code null} when the attunement slot is empty.
     */
    public AttunedMagic parse(final AttunedMagicDefinition attunedMagicDefinition, final ByteBlock attunedMagicsBlock) {
        final long itemId = blockSectionParser.parse(attunedMagicDefinition.getMagicIdDefinition(), attunedMagicsBlock);
        
        if (itemId != attunedMagicDefinition.getEmptySlotIndicator()) {
            final AttunedMagic item = new AttunedMagic();
            
            item.setIdSpace(attunedMagicDefinition.getIdSpace());
            item.setId(itemId);
            
            return item;
            
        } else {
            return null;
        }
        
    }
    
}
