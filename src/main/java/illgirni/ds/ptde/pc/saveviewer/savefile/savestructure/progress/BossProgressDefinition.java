package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;

/**
 * Definition for a single boss defeat ("yes/no") in a "boss group defeat byte".
 * 
 * @author illgirni
 *
 */
public class BossProgressDefinition extends AbstractBitDefinition<Boss> {

  /**
   * @param boss The boss for which to determine if defeated or not.
   * @param defeatBitOffset The defeat bit offset in the group byte.
   */
  public BossProgressDefinition(final Boss boss, final int defeatBitOffset) {
    super(boss, defeatBitOffset);
  }

}
