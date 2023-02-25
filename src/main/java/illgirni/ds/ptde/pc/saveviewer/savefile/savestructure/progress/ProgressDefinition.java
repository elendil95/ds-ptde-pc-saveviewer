package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * This is the overall definition of the current playthrough's progress in the slot content of a
 * save slot. The progress definition does not include everything the game has to offer, but the
 * some important progress indicators. These are:
 * <ul>
 * <li>Bonfire kindle states</li>
 * <li>Unlocked warp points</li>
 * <li>Whether or not warping is unlocked</li>
 * <li>Which bosses are defeated</li>
 * <li>Which tails were cut</li>
 * </ul>
 * 
 * @author illgirni
 *
 */
@Bean
public class ProgressDefinition {

  /**
   * The section with the bonfire kindle information is one of a list of sections with dynamically
   * growing size. This means that its offset in the slot content shifts around. This value here is
   * the offset of the first section in the list, which does not move around. For hints regarding
   * the calculation of the bonfire kindle state section offset and length see
   * {@link #getBonfiresDefintion(int, int)}.
   */
  public ByteBlockSectionDefinition<Integer> getBonfiresOffsetLengthCalculationDefinition() {
    return new ByteBlockSectionDefinition<>(220710, 4, JavaTypeToDataType.INT_32);
  }

  /**
   * Definition of the bonfire kindle state section length. Essentially the first four bytes in that
   * section. Well, the length definition is not part of the actual section.
   * 
   * @param bonfiresOffset The offset of the bonfire kindle states section in the slot content.
   */
  public ByteBlockSectionDefinition<Integer> getBonfiresLengthDefinition(int bonfiresOffset) {
    return new ByteBlockSectionDefinition<>(bonfiresOffset, 4, JavaTypeToDataType.INT_32);
  }

  /**
   * Defines the section in the slot content, which contains the bonfire kindle states. This section
   * is one of a number of sections in a list of section that grow as the game progresses. It itself
   * also grows: The size depends on the locations that have been loaded at least once in the game.
   * <p/>
   * To calculate the offset and length of this section proceed as follows:
   * <ol>
   * <li>Starts at the offset defined by {@link #getBonfiresOffsetLengthCalculationDefinition()}.
   * This is the offset of the first block in the list, which grows, but is always at the same
   * position in the slot content. At that offset is the length of that first section. Read the
   * length and skip that many bytes. That's the new offset</li>
   * <li>At the previously calculated offset there is again a dynamically growing section. Repeat
   * the previous step and get the next offset.</li>
   * <li>The next section is of a fixed size of 88 bytes. It is easy to calculate the offset of the
   * following section.</li>
   * <li>Now follow six more sections. They are dynamically growing and differ a bit from the
   * previous growing sections in that they have a 4 byte footer, which is not part of their length
   * definition. So, iterate six times, each time reading the length, skipping that number of bytes,
   * and skipping the footer. Just for information: Some of these section contain online play
   * information.</li>
   * <li>With the last calculated offset we have the offset of the bonfire kindle state section in
   * the slot content. The bonfire section starts right after this length definition. So, read the
   * length and then we have the offset and length of the bonfire section.</li>
   * </ol>
   * Visually this looks something like that: <code><pre>
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b      |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b      |
   * ----------------------------------------------
   * | 88b                                        |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b | 4b |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b | 4b |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b | 4b |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b | 4b |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b | 4b |
   * ----------------------------------------------
   * |4b | # bytes as defined in previous 4b | 4b |
   * ----------------------------------------------
   * |4b | # bytes (BONFIRES)                | 4b |
   * ----------------------------------------------
   * </pre></code>
   * 
   * @param offset The offset of the bonfire section in the slot content (not including the length
   *        definition of the section).
   * @param length The lenght of the section.
   * @return
   */
  public ByteBlockSectionDefinition<ByteBlock> getBonfiresDefintion(int offset, int length) {
    return new ByteBlockSectionDefinition<>(offset, length, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The section defining if the character can warp.
   */
  public ByteBlockSectionDefinition<ByteBlock> getWarpUnlockedBlockDefinition() {
    return new ByteBlockSectionDefinition<>(151597, 1, JavaTypeToDataType.BYTE_BLOCK); // use 127360
                                                                                       // to enable
                                                                                       // warping in
                                                                                       // game
                                                                                       // (000000x0)
  }

  /**
   * The sections in the slot content, which contain information about which warp points are
   * unlocked.
   */
  public List<WarpPointGroupBitBlockDefinition> getWarpPointGroupDefinitions() {
    List<WarpPointGroupBitBlockDefinition> warpPointGroups = new ArrayList<>();

    warpPointGroups.add(new WarpPointGroupBitBlockDefinition(127293, Bonfire.PAINTED_WORLD,
        Bonfire.TOMB_OF_GIANTS_1, Bonfire.DUKES_ARCHIVE, Bonfire.CRYSTAL_CAVES));

    warpPointGroups.add(new WarpPointGroupBitBlockDefinition(127294, Bonfire.DARKMOON_TOMB,
        Bonfire.SANCTUARY_GARDEN, Bonfire.OOLACILE_SANCTUARY, Bonfire.OOLACILE_TOWNSHIP,
        Bonfire.CHASM_OF_THE_ABYSS, Bonfire.OOLACILE_DUNGEON, Bonfire.DEPTHS,
        Bonfire.UNDEAD_PARISH));

    warpPointGroups.add(new WarpPointGroupBitBlockDefinition(127295, Bonfire.FIRELINK_SHRINE,
        Bonfire.STONE_DRAGON, Bonfire.DAUGHTER_OF_CHAOS, Bonfire.ANOR_LONDO, Bonfire.ABYSS,
        Bonfire.SUNLIGHT_ALTAR, Bonfire.GRAVELORD_ALTAR, Bonfire.CHAMBER_OF_THE_PRINCESS));

    return warpPointGroups;
  }

  /**
   * The sections in the slot content, which contain information about which bosses have been
   * defeated.
   */
  public List<BossGroupBitBlockDefinition> getBossGroupDefinitions() {
    List<BossGroupBitBlockDefinition> bossGroups = new ArrayList<>();

    bossGroups.add(new BossGroupBitBlockDefinition(127270, Boss.ASYLUM_DEMON, Boss.MANUS));

    bossGroups.add(new BossGroupBitBlockDefinition(127271, Boss.QUELAAG, Boss.BED_OF_CHAOS,
        Boss.IRON_GOLEM, Boss.ORNSTEIN_AND_SMOUGH, Boss.FOUR_KINGS, Boss.SEATH, Boss.GWYN));

    bossGroups.add(new BossGroupBitBlockDefinition(127272, Boss.GAPING_DRAGON, Boss.BELL_GARGOYLES,
        Boss.PRISCILLA, Boss.SIF, Boss.PINWHEEL, Boss.NITO));

    bossGroups.add(new BossGroupBitBlockDefinition(131224, Boss.TAURUS, Boss.CAPRA_DEMON));

    bossGroups.add(new BossGroupBitBlockDefinition(135064, Boss.MOONLIGHT_BUTTERFLY));

    bossGroups.add(new BossGroupBitBlockDefinition(136232, Boss.SANCTUARY_GUARDIAN, Boss.ARTORIAS,
        Boss.KALAMEET));

    bossGroups.add(new BossGroupBitBlockDefinition(142677, Boss.DEMON_FIRESAGE));

    bossGroups.add(
        new BossGroupBitBlockDefinition(142744, Boss.CEASELESS_DISCHARGE, Boss.CENTIPEDE_DEMON));

    // options:
    // -- 145227 (0000x000)
    // -- 145268 (00x00000)
    // -- 145324 (0000x010)?
    // -- 151809 (x0000000)
    bossGroups.add(new BossGroupBitBlockDefinition(145207, Boss.GWYNDLOLIN));

    bossGroups.add(new BossGroupBitBlockDefinition(150424, Boss.STRAY_DEMON));

    return bossGroups;
  }

  /**
   * The sections in the slot content, which contain information about which tails have been cut.
   */
  public List<TailcutBitBlockDefinition> getTailcutDefinitions() {
    final List<TailcutBitBlockDefinition> tailcutDefinitions = new ArrayList<>();

    tailcutDefinitions.add(new TailcutBitBlockDefinition(152989, TailOwner.GAPING_DRAGON));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(154271, TailOwner.BELL_GARGOYLES));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(154971, TailOwner.HELLKITE));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(156829, TailOwner.PRISCILLA));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(159386, TailOwner.SANCTUARY_GUARDIAN));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(159391, TailOwner.KALAMEET));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(163933, TailOwner.STONE_DRAGON));
    tailcutDefinitions.add(new TailcutBitBlockDefinition(170912, TailOwner.SEATH));

    return tailcutDefinitions;
  }
}
