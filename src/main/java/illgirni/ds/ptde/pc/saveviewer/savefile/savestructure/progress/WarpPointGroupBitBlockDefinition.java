package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import java.util.Arrays;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;

/**
 * The definition of a bit group for enabled warp points. The bits in the respective byte indicate
 * if the respective warp point in the group has been defeated.
 * <p/>
 * This is essentially the definition of one byte in the slot content, with a fixed "target mapping
 * type", and which warp points (bonfires) are associated to that byte.
 * 
 * @author illgirni
 *
 */
public class WarpPointGroupBitBlockDefinition extends ByteBlockSectionDefinition<boolean[]> {
    
    /**
     * The warp points for which this groups defines, if they are enabled.
     */
    private final List<Bonfire> bonfires;
    
    /**
     * @param offset The offset of the byte in the slot content.
     * @param bonfires The warp points for which this groups defines, if they are enabled.
     */
    public WarpPointGroupBitBlockDefinition(int offset, Bonfire... bonfires) {
        super(offset, 1, JavaTypeToDataType.BITS_OF_BYTE);
        
        this.bonfires = Arrays.asList(bonfires);
    }
    
    /**
     * The warp points for which this groups defines, if they are enabled.
     */
    public List<Bonfire> getBonfires() {
        return bonfires;
    }

}
