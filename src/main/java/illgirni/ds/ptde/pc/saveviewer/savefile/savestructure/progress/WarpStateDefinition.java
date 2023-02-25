package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;

/**
 * Provides the definitions for all the "warp point enabled" within their respective groups.
 * 
 * @see WarpPointGroupBitBlockDefinition
 * @see WarpPointStateDefinition
 * 
 * @author illgirni
 *
 */
@Bean
public class WarpStateDefinition {

  // public List<WarpUnlockedDefinition> getWarpUnlockedDefinitions() {
  // final List<WarpUnlockedDefinition> unlockedDefinitions = new ArrayList<>();
  //
  // unlockedDefinitions.add(new WarpUnlockedDefinition(2)); //bit not needed
  // unlockedDefinitions.add(new WarpUnlockedDefinition(6));
  //
  // return unlockedDefinitions;
  // }

  /**
   * For each warp point the "enabled yes/no" bit definition in the respective warp point group
   * byte.
   */
  public List<WarpPointStateDefinition> getWarpPointStateDefinitions() {
    List<WarpPointStateDefinition> warpPointDefinitions = new ArrayList<>();

    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.PAINTED_WORLD, 0));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.TOMB_OF_GIANTS_1, 1));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.DUKES_ARCHIVE, 2));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.CRYSTAL_CAVES, 3));

    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.DARKMOON_TOMB, 0));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.SANCTUARY_GARDEN, 1));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.OOLACILE_SANCTUARY, 2));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.OOLACILE_TOWNSHIP, 3));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.CHASM_OF_THE_ABYSS, 4));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.OOLACILE_DUNGEON, 5));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.DEPTHS, 6));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.UNDEAD_PARISH, 7));

    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.FIRELINK_SHRINE, 0));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.STONE_DRAGON, 1));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.DAUGHTER_OF_CHAOS, 2));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.ANOR_LONDO, 3));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.ABYSS, 4));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.SUNLIGHT_ALTAR, 5));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.GRAVELORD_ALTAR, 6));
    warpPointDefinitions.add(new WarpPointStateDefinition(Bonfire.CHAMBER_OF_THE_PRINCESS, 7));

    return warpPointDefinitions;
  }

}
