package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossGroupBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossProgress;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.BossProgressDefinition;

/**
 * Parses the defeat information for a single boss from a "defeat bit block" containing information
 * for possibly multiple bosses.
 * 
 * @author illgirni
 *
 */
@Bean
public class BossProgressParser extends AbstractSaveElementParser {
    
    /**
     * Parses the defeat information for the boss definition from the bit block.
     * 
     * @param bossProgressDefinition The boss definition.
     * @param bossGroupBitBlock The "defeats bit block".
     * @return The defeat information for the boss.
     */
    public BossProgress parse(BossProgressDefinition bossProgressDefinition, BossGroupBitBlock bossGroupBitBlock) {
        final BossProgress bossProgress = new BossProgress();
        
        bossProgress.setBoss(bossProgressDefinition.getRepresentor());
        bossProgress.setDefeated(bossGroupBitBlock.getBlockData()[bossProgressDefinition.getBitOffset()]);
        
        return bossProgress;
    }

}
