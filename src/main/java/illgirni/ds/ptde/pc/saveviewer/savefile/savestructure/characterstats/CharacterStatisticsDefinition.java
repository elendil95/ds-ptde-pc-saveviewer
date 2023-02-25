package illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition.JavaTypeToDataType;

/**
 * The character stats we are interested in are distributed over three blocks within the save slot
 * content:
 * <ol>
 * <li>The first about 350 bytes of the slot content with the main chunk of stats.</li>
 * <li>An eight byte block at 124,178 with the game clear counter and last chosen ending.</li>
 * <li>The total death count of the character at 127,252.</li>
 * </ol>
 * 
 * @author illgirni
 *
 */
@Bean
public class CharacterStatisticsDefinition {

  /**
   * The section with the main chunk of stats.
   */
  public ByteBlockSectionDefinition<ByteBlock> getCharacterStatisticsDefinition() {
    return new ByteBlockSectionDefinition<>(0, 348, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The section with the clear counter and last ending.
   */
  public ByteBlockSectionDefinition<ByteBlock> getClearCounterDefinition() {
    return new ByteBlockSectionDefinition<>(124178, 8, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The section with the death count.
   */
  public ByteBlockSectionDefinition<ByteBlock> getDeathCountDefinition() {
    return new ByteBlockSectionDefinition<>(127252, 4, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The section in the main chunk with the current world/location.
   */
  public ByteBlockSectionDefinition<ByteBlock> getLocationDefinition() {
    return new ByteBlockSectionDefinition<>(6, 2, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The section in the main chunk with the play time.
   */
  public ByteBlockSectionDefinition<ByteBlock> getPlayTimeDefinition() {
    return new ByteBlockSectionDefinition<>(8, 4, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The character's current HP (may be reduced by damage).
   */
  public ByteBlockSectionDefinition<Long> getHpCurrentDefinition() {
    return new ByteBlockSectionDefinition<>(88, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's total/max HP (including buffs like the FAP ring).
   */
  public ByteBlockSectionDefinition<Long> getHpTotalModifiedDefinition() {
    return new ByteBlockSectionDefinition<>(92, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's total/max HP (without any buffs).
   */
  public ByteBlockSectionDefinition<Long> getHpTotalUnmodifiedDefinition() {
    return new ByteBlockSectionDefinition<>(96, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's current stamina (may be reduced due to usage).
   */
  public ByteBlockSectionDefinition<Long> getStaminaCurrentDefinition() {
    return new ByteBlockSectionDefinition<>(116, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's total/max stamina (including buffs like the FAP ring).
   */
  public ByteBlockSectionDefinition<Long> getStaminaTotalModifiedDefinition() {
    return new ByteBlockSectionDefinition<>(120, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's total/max stamina (without any buffs).
   */
  public ByteBlockSectionDefinition<Long> getStaminaTotalUnmodifiedDefinition() {
    return new ByteBlockSectionDefinition<>(124, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's vitality.
   */
  public ByteBlockSectionDefinition<Long> getVitalityDefinition() {
    return new ByteBlockSectionDefinition<>(132, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's attunement.
   */
  public ByteBlockSectionDefinition<Long> getAttunementDefinition() {
    return new ByteBlockSectionDefinition<>(140, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's endurance.
   */
  public ByteBlockSectionDefinition<Long> getEnduranceDefinition() {
    return new ByteBlockSectionDefinition<>(148, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's strength.
   */
  public ByteBlockSectionDefinition<Long> getStrengthDefinition() {
    return new ByteBlockSectionDefinition<>(156, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's dexterity.
   */
  public ByteBlockSectionDefinition<Long> getDexterityDefinition() {
    return new ByteBlockSectionDefinition<>(164, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's intelligence.
   */
  public ByteBlockSectionDefinition<Long> getIntelligenceDefinition() {
    return new ByteBlockSectionDefinition<>(172, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's faith.
   */
  public ByteBlockSectionDefinition<Long> getFaithDefinition() {
    return new ByteBlockSectionDefinition<>(180, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's current soft humanity.
   */
  public ByteBlockSectionDefinition<Long> getHumanityDefinition() {
    return new ByteBlockSectionDefinition<>(200, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's resistance.
   */
  public ByteBlockSectionDefinition<Long> getResistanceDefinition() {
    return new ByteBlockSectionDefinition<>(204, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's level.
   */
  public ByteBlockSectionDefinition<Long> getLevelDefinition() {
    return new ByteBlockSectionDefinition<>(212, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's current number of souls.
   */
  public ByteBlockSectionDefinition<Long> getSoulsDefinition() {
    return new ByteBlockSectionDefinition<>(216, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The total number of souls that the character earned.
   */
  public ByteBlockSectionDefinition<Long> getSoulsTotalDefinition() {
    return new ByteBlockSectionDefinition<>(220, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The name of the character. The name has at most thirteen chars, which are encoded as UTF-16LE
   * (so two byte per char). It always ends with a to {@code 0 } bytes. So we read as much String as
   * we can.
   */
  public ByteBlockSectionDefinition<String> getNameDefinition() {
    return new ByteBlockSectionDefinition<>(236, -1, JavaTypeToDataType.STRING_UTF16);
  }

  /**
   * The character's gender. This is a simple number indicator consisting of one byte.
   */
  public ByteBlockSectionDefinition<ByteBlock> getGenderDefinition() {
    return new ByteBlockSectionDefinition<>(270, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The character's starting class. This is a simple number indicator consisting of one byte.
   */
  public ByteBlockSectionDefinition<ByteBlock> getStartingClassDefinition() {
    return new ByteBlockSectionDefinition<>(274, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The character's physique. This is a simple number indicator consisting of one byte.
   */
  public ByteBlockSectionDefinition<ByteBlock> getPhysiqueDefinition() {
    return new ByteBlockSectionDefinition<>(275, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The character's gift. This is a simple number indicator consisting of one byte.
   */
  public ByteBlockSectionDefinition<ByteBlock> getGiftDefinition() {
    return new ByteBlockSectionDefinition<>(276, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The number of multiplayer interactions of the character.
   */
  public ByteBlockSectionDefinition<Long> getMultiplayerCountDefinition() {
    return new ByteBlockSectionDefinition<>(280, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The number of boss victories as a white phantom.
   */
  public ByteBlockSectionDefinition<Long> getCoopVictoriesDefinition() {
    return new ByteBlockSectionDefinition<>(284, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The section with the character's covenant level/progress in each "levelable" covenant.
   */
  public ByteBlockSectionDefinition<ByteBlock> getCovenantLevelsDefinition() {
    return new ByteBlockSectionDefinition<>(305, 8, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The character's current number of indictments.
   */
  public ByteBlockSectionDefinition<Long> getIndictmentsDefinition() {
    return new ByteBlockSectionDefinition<>(313, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The character's current covenant. This is a simple number indicator consisting of one byte.
   */
  public ByteBlockSectionDefinition<ByteBlock> getCovenantDefinition() {
    return new ByteBlockSectionDefinition<>(343, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

  /**
   * The character's current cursed level. This is a simple number indicator consisting of one byte.
   */
  public ByteBlockSectionDefinition<ByteBlock> getCursedDefinition() {
    return new ByteBlockSectionDefinition<>(347, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

  // secondary blocks

  /**
   * The number of deaths the character suffered.
   */
  public ByteBlockSectionDefinition<Long> getDeathsDefinition() {
    return new ByteBlockSectionDefinition<>(0, 4, JavaTypeToDataType.UINT_32);
  }

  /**
   * The new game level ({@code 0} being NG, {@code 1} being NG+, etc.; maxes at {@code 7}).
   */
  public ByteBlockSectionDefinition<Long> getClearCountDefinition() {
    return new ByteBlockSectionDefinition<>(0, 1, JavaTypeToDataType.UINT_8);
  }

  /**
   * The ending of the character's last playthrough.
   */
  public ByteBlockSectionDefinition<ByteBlock> getLastEndingDefinition() {
    return new ByteBlockSectionDefinition<>(4, 1, JavaTypeToDataType.BYTE_BLOCK);
  }

}
