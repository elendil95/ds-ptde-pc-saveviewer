package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;

/**
 * Defines the bit in a tail cut byte, which indicates, if the tail is cut for the tail owner.
 * 
 * @author illgirni
 *
 */
public class TailcutStateDefinition extends AbstractBitDefinition<TailOwner> {
    
    /**
     * @param tailOwner The tail owner.
     * @param cutBitOffset The offset of the bit indicating, if the tail has been cut or not.
     */
    public TailcutStateDefinition(final TailOwner tailOwner, final int cutBitOffset) {
        super(tailOwner, cutBitOffset);
    }
    
}
