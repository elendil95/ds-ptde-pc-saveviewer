package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;

/**
 * A bit block containing the information for a group of bosses if they are defeated.
 * 
 * @author illgirni
 *
 */
public class BossGroupBitBlock extends AbstractProgressGroupBitBlock<Boss> {
    
    /**
     * @param blockData The bits.
     * @param bosses The bosses represented by these bits.
     */
    public BossGroupBitBlock(boolean[] blockData, final List<Boss> bosses) {
        super(blockData, bosses);
    }
    
}
