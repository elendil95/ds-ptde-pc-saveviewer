package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;

/**
 * A "collection" of the defeat information for all main bosses in the game.
 * 
 * @author illgirni
 *
 */
public class BossesProgress {

  /**
   * The actual boss defeats.
   */
  private final List<BossProgress> bossProgresses = new ArrayList<>();

  /**
   * Adds a boss defeat information to this "collection".
   */
  public void addBoss(final BossProgress bossProgress) {
    if (bossProgress != null) {
      bossProgresses.add(bossProgress);
    }
  }

  /**
   * Convenience method to determine if a boss has been defeated.
   * 
   * @param boss The boss.
   * @return {@code true} when the boss has been defeated.
   */
  public boolean isDefeated(final Boss boss) {
    for (final BossProgress bossProgress : bossProgresses) {
      if (bossProgress.getBoss() == boss) {
        return bossProgress.isDefeated();
      }
    }

    return false;
  }

}
