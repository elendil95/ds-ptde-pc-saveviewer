package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;

/**
 * The information for one tail owner, if the tail has been cut.
 * 
 * @author illgirni
 *
 */
public class TailcutState {
    
    /**
     * The tail owner.
     */
    private TailOwner tailOwner;
    
    /**
     * If the tail has been cut.
     */
    private boolean cut;
    
    /**
     * The tail owner.
     */
    public TailOwner getTailOwner() {
        return tailOwner;
    }
    
    /**
     * @see #getTailOwner()
     */
    public void setTailOwner(TailOwner tailOwner) {
        this.tailOwner = tailOwner;
    }
    
    /**
     * If the tail has been cut.
     */
    public boolean isCut() {
        return cut;
    }
    
    /**
     * @see #isCut()
     */
    public void setCut(boolean cut) {
        this.cut = cut;
    }
    
}
