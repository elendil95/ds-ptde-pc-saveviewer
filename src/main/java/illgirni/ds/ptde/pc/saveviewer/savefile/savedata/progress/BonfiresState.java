package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.BonfireStrength;

/**
 * A "collection" of the kindle state of the game's bonfires.
 * 
 * @author illgirni
 *
 */
public class BonfiresState {
    
    /**
     * The actual bonfire kindle states.
     */
    private final List<BonfireState> bonfireStates = new ArrayList<>();
    
    /**
     * Adds a kindle state for a bonfire to this "collection".
     */
    public void addBonfireState(final BonfireState bonfireState) {
        if (bonfireState != null) {
            bonfireStates.add(bonfireState);
        }
    }
    
    /**
     * Convenience method to get the kindle state of the bonfire from this "collection".
     * 
     * @param bonfire The bonfire.
     * @return The kindle state.
     */
    public BonfireStrength getStrength(final Bonfire bonfire) {
        for (final BonfireState bonfireState : bonfireStates) {
            if (bonfireState.getBonfire() == bonfire) {
                return bonfireState.getStrength();
            }
        }
        
        //Bonfire might not have yet been loaded in the game. Then we use the default for it.
        if (bonfire == Bonfire.FIRELINK_SHRINE || bonfire == Bonfire.ANOR_LONDO || bonfire == Bonfire.DAUGHTER_OF_CHAOS) {
            return BonfireStrength.KINDLED_1;
        } else {
            return BonfireStrength.OFF;
        }
    }
    
}
