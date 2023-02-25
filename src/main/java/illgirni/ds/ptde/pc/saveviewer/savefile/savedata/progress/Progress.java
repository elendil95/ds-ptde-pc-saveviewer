package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

/**
 * All the "interesting" progress in a character's playthrough.
 * 
 * @author illgirni
 *
 */
public class Progress {

  /**
   * Which bosses are defeated.
   */
  private BossesProgress bossesProgress;

  /**
   * How far the bonfires are kindled.
   */
  private BonfiresState bonfiresState;

  /**
   * Which warp points are enabled and if warping is unlocked at all.
   */
  private WarpState warpState;

  /**
   * Which tails have been cut.
   */
  private TailcutsState tailcutsState;

  /**
   * Which bosses are defeated.
   */
  public BossesProgress getBossesProgress() {
    return bossesProgress;
  }

  /**
   * @see #getBossesProgress()
   */
  public void setBossesProgress(BossesProgress bossesProgress) {
    this.bossesProgress = bossesProgress;
  }

  /**
   * How far the bonfires are kindled.
   */
  public BonfiresState getBonfiresState() {
    return bonfiresState;
  }

  /**
   * @see #getBonfiresState()
   */
  public void setBonfiresState(BonfiresState bonfiresState) {
    this.bonfiresState = bonfiresState;
  }

  /**
   * Which warp points are enabled and if warping is unlocked at all.
   */
  public WarpState getWarpState() {
    return warpState;
  }

  /**
   * @see #getWarpState()
   */
  public void setWarpState(WarpState warpState) {
    this.warpState = warpState;
  }

  /**
   * Which tails have been cut.
   */
  public TailcutsState getTailcutsState() {
    return tailcutsState;
  }

  /**
   * @see #getTailcutsState()
   */
  public void setTailcutsState(TailcutsState tailcutsState) {
    this.tailcutsState = tailcutsState;
  }
}
