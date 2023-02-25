package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;

/**
 * The defeat information for a particular boss.
 * 
 * @author illgirni
 *
 */
public class BossProgress {

  /**
   * The boss.
   */
  private Boss boss;

  /**
   * If the boss has been defeated.
   */
  private boolean defeated;

  /**
   * The boss.
   */
  public Boss getBoss() {
    return boss;
  }

  /**
   * @see #getBoss()
   */
  public void setBoss(Boss boss) {
    this.boss = boss;
  }

  /**
   * If the boss has been defeated.
   */
  public boolean isDefeated() {
    return defeated;
  }

  /**
   * @see #isDefeated()
   */
  public void setDefeated(boolean defeated) {
    this.defeated = defeated;
  }

}
