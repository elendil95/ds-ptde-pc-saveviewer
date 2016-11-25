package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.BonfireStrength;

/**
 * The kindle state for a particular bonfire.
 * 
 * @author illgirni
 *
 */
public class BonfireState {
    
    /**
     * The bonfire.
     */
    private Bonfire bonfire;
    
    /**
     * The kindle state.
     */
    private BonfireStrength strength;
    
    /**
     * The bonfire.
     */
    public Bonfire getBonfire() {
        return bonfire;
    }
    
    /**
     * @see #getBonfire()
     */
    public void setBonfire(Bonfire bonfire) {
        this.bonfire = bonfire;
    }
    
    /**
     * The kindle state.
     */
    public BonfireStrength getStrength() {
        return strength;
    }
    
    /**
     * @see #getStrength()
     */
    public void setStrength(BonfireStrength strength) {
        this.strength = strength;
    }
    
}
