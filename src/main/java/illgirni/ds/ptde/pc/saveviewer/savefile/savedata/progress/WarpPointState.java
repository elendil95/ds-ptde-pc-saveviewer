package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;

/**
 * For a particular warp point if it is unlocked in the current playthrough.
 * 
 * @author illgirni
 *
 */
public class WarpPointState {

  /**
   * The warp point.
   */
  private Bonfire bonfire;

  /**
   * If it is unlocked.
   */
  private boolean unlocked;

  /**
   * The warp point.
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
   * If it is unlocked.
   */
  public boolean isUnlocked() {
    return unlocked;
  }

  /**
   * @see #isUnlocked()
   */
  public void setUnlocked(boolean unlocked) {
    this.unlocked = unlocked;
  }

}
