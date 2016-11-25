package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;

/**
 * Provides the definitions for all tail cuts within their respective byte.
 * 
 * @see TailcutBitBlockDefinition
 * @see TailcutStateDefinition
 * 
 * @author illgirni
 *
 */
@Bean
public class TailcutsStateDefinition {
    
    /**
     * For each tail owner the "cut yes/no" definition in the respective byte.
     */
    public List<TailcutStateDefinition> getTailcutDefinitions() {
        List<TailcutStateDefinition> tailcutDefinitions = new ArrayList<>();
        
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.BELL_GARGOYLES, 2));       //x20|032
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.GAPING_DRAGON, 6));        //x02|002
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.PRISCILLA, 6));            //x02|002
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.STONE_DRAGON, 6));         //x02|002
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.SEATH, 0));                //x80|128
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.SANCTUARY_GUARDIAN, 6));   //x02|002
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.KALAMEET, 2));             //x20|032
        tailcutDefinitions.add(new TailcutStateDefinition(TailOwner.HELLKITE, 4));             //x08|008
        
        if (tailcutDefinitions.size() != TailOwner.values().length) {
            throw new RuntimeException("Not all tailcuts defined.");
        }
        
        return tailcutDefinitions;
    }
    
    
}
