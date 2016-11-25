package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;

/**
 * Definition of a byte as a block of bits for a single tail cut.
 * 
 * @author illgirni
 *
 */
public class TailcutBitBlockDefinition extends ByteBlockSectionDefinition<boolean[]> {
    
    /**
     * The tail owner.
     */
    private final TailOwner tailOwner;
    
    /**
     * @param offset The offset of the byte in the slot content.
     * @param tailOwner The tail owner
     */
    public TailcutBitBlockDefinition(int offset, TailOwner tailOwner) {
        super(offset, 1, JavaTypeToDataType.BITS_OF_BYTE);
        
        this.tailOwner = tailOwner;
    }
    
    /**
     * The tail owner.
     */
    public TailOwner getTailOwner() {
        return tailOwner;
    }

}
