package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;

/**
 * A "collection" of all the warp points and which of them are unlocked. Also contains information
 * if warping is unlocked at all.
 * 
 * @author illgirni
 *
 */
public class WarpState {

  /**
   * All bonfires that act as warp points.
   */
  public static final List<Bonfire> ALL_WARP_POINTS = Arrays.asList(Bonfire.PAINTED_WORLD,
      Bonfire.TOMB_OF_GIANTS_1, Bonfire.DUKES_ARCHIVE, Bonfire.CRYSTAL_CAVES, Bonfire.DARKMOON_TOMB,

      Bonfire.SANCTUARY_GARDEN, Bonfire.OOLACILE_SANCTUARY, Bonfire.OOLACILE_TOWNSHIP,
      Bonfire.CHASM_OF_THE_ABYSS, Bonfire.OOLACILE_DUNGEON,

      Bonfire.DEPTHS, Bonfire.UNDEAD_PARISH, Bonfire.FIRELINK_SHRINE, Bonfire.STONE_DRAGON,
      Bonfire.DAUGHTER_OF_CHAOS,

      Bonfire.ANOR_LONDO, Bonfire.ABYSS, Bonfire.SUNLIGHT_ALTAR, Bonfire.GRAVELORD_ALTAR,
      Bonfire.CHAMBER_OF_THE_PRINCESS);

  /**
   * The actual warp point collection.
   */
  private final List<WarpPointState> warpPointStates = new ArrayList<>();

  /**
   * If warping is unlocked in the playthrough.
   */
  private boolean warpingUnlocked;

  /**
   * Adds a warp point unlocked information to this "collection".
   */
  public void addWarpPoint(final WarpPointState warpPointState) {
    if (warpPointState != null) {
      warpPointStates.add(warpPointState);
    }
  }

  /**
   * Determines for a warp point if it is unlocked.
   * 
   * @param bonfire The warp point.
   * @return {@code true} if it is unlocked.
   */
  public boolean isUnlocked(final Bonfire bonfire) {
    for (final WarpPointState warpPointState : warpPointStates) {
      if (warpPointState.getBonfire() == bonfire) {
        return warpPointState.isUnlocked();
      }
    }

    return false;
  }

  /**
   * If warping is unlocked in the playthrough.
   */
  public boolean isWarpingUnlocked() {
    return warpingUnlocked;
  }

  /**
   * @see #isWarpingUnlocked()
   */
  public void setWarpingUnlocked(boolean warpingUnlocked) {
    this.warpingUnlocked = warpingUnlocked;
  }
}
