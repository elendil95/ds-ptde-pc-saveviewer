package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import java.util.Arrays;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;

/**
 * The definition of a bit group for "boss defeats". The bits in the respective byte indicate
 * if the respective boss in the group has been defeated.
 * <p/>
 * This is essentially the definition of one byte in the slot content, with a fixed "target mapping
 * type", and which bosses are associated to that byte.
 * 
 * @author illgirni
 *
 */
public class BossGroupBitBlockDefinition extends ByteBlockSectionDefinition<boolean[]> {
    
    /**
     * The bosses for which this group defines if they are defeated.
     */
    private final List<Boss> bosses;
    
    /**
     * @param offset The offset of the byte in the slot content.
     * @param bosses The bosses for which this group defines if they are defeated.
     */
    public BossGroupBitBlockDefinition(int offset, Boss... bosses) {
        super(offset, 1, JavaTypeToDataType.BITS_OF_BYTE);
        
        this.bosses = Arrays.asList(bosses);
    }
    
    /**
     * The bosses for which this group defines if they are defeated.
     */
    public List<Boss> getBosses() {
        return bosses;
    }

}
