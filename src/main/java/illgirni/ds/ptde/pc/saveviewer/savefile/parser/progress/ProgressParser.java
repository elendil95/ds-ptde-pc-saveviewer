package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BonfiresState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossGroupBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BossesProgress;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.Progress;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.TailcutsState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpPointGroupBitBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.BossGroupBitBlockDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.ProgressDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.TailcutBitBlockDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.WarpPointGroupBitBlockDefinition;

/**
 * Parses the playthrough's progress from the byte block representing a save slot's content. Mostly
 * splits the byte block in smaller chunks and passes them to the parsers for the corresponding
 * "topic".
 * 
 * @author illgirni
 *
 */
@Bean
public class ProgressParser extends AbstractSaveElementParser {

  /**
   * The playthrough progress sections definition.
   */
  @Inject
  private ProgressDefinition progressDefinition;

  /**
   * Parser for boss defeats.
   */
  @Inject
  private BossesProgressParser bossesProgressParser;

  /**
   * Parser for bonfire kindle states.
   */
  @Inject
  private BonfiresStateParser bonfiresStateParser;

  /**
   * Parser for (un)locked warping and warp points.
   */
  @Inject
  private WarpStateParser warpStateParser;

  /**
   * Parser for tail cuts.
   */
  @Inject
  private TailcutsStateParser tailcutsStateParser;

  /**
   * Parses the playthrough progress information from the slot content byte block.
   * 
   * @param slotContentData The slot content byte block.
   * @return The playthrough progress.
   * 
   * @throws ParserException
   */
  public Progress parse(final ByteBlock slotContentData) throws ParserException {
    final Progress progress = new Progress();

    progress.setBossesProgress(parseBossesProgress(slotContentData));
    progress.setTailcutsState(parseTailcutsState(slotContentData));
    progress.setBonfiresState(parseBonfiresState(slotContentData));
    progress.setWarpState(parseWarpState(slotContentData));

    return progress;
  }

  /**
   * Extracts the boss defeats sections from the slot content byte block and hands over to the
   * corresponding parser to parse them.
   * 
   * @param slotContentData The slot content.
   * @return The boss defeats information.
   */
  private BossesProgress parseBossesProgress(final ByteBlock slotContentData) {
    final List<BossGroupBitBlock> bossGroups = new ArrayList<>();

    for (final BossGroupBitBlockDefinition bossGroupDefinition : progressDefinition
        .getBossGroupDefinitions()) {
      bossGroups
          .add(new BossGroupBitBlock(blockSectionParser.parse(bossGroupDefinition, slotContentData),
              bossGroupDefinition.getBosses()));
    }

    return bossesProgressParser.parse(bossGroups);
  }

  /**
   * Extracts the tail cuts sections from the slot content byte block and hands over to the
   * corresponding parser to parse them.
   * 
   * @param slotContentData The slot content.
   * @return The tail cuts information.
   */
  private TailcutsState parseTailcutsState(final ByteBlock slotContentData) {
    final List<TailcutBitBlock> tailcuts = new ArrayList<>();

    for (final TailcutBitBlockDefinition tailcutDefinition : progressDefinition
        .getTailcutDefinitions()) {
      tailcuts.add(new TailcutBitBlock(blockSectionParser.parse(tailcutDefinition, slotContentData),
          tailcutDefinition.getTailOwner()));
    }

    return tailcutsStateParser.parse(tailcuts);
  }

  /**
   * Extracts the warp points and warp (un)locked sections from the slot content byte block and
   * hands over to the corresponding parser to parse them.
   * 
   * @param slotContentData The slot content.
   * @return The warp information.
   */
  private WarpState parseWarpState(final ByteBlock slotContentData) throws ParserException {
    final List<WarpPointGroupBitBlock> warpPointGroups = new ArrayList<>();

    for (final WarpPointGroupBitBlockDefinition warpPointGroupDefinition : progressDefinition
        .getWarpPointGroupDefinitions()) {
      warpPointGroups.add(new WarpPointGroupBitBlock(
          blockSectionParser.parse(warpPointGroupDefinition, slotContentData),
          warpPointGroupDefinition.getBonfires()));
    }

    final ByteBlock warpUnlockedBlock = blockSectionParser
        .parse(progressDefinition.getWarpUnlockedBlockDefinition(), slotContentData);

    return warpStateParser.parse(warpPointGroups, warpUnlockedBlock);
  }

  /**
   * Extracts the bonfire kindle states sections from the slot content byte block and hands over to
   * the corresponding parser to parse them.
   * 
   * @param slotContentData The slot content.
   * @return The bonfire kindle states information.
   * 
   * @see ProgressDefinition#getBonfiresDefintion(int, int)
   */
  private BonfiresState parseBonfiresState(final ByteBlock slotContentData) throws ParserException {
    final int bonfiresLengthOffset = calculateBonfiresLengthOffset(slotContentData);

    final int bonfiresBlockOffset = bonfiresLengthOffset + getInt32Length(); // + length of offset
    // int32 length is the footer not accounted in the length
    final int bonfiresBlockLength = blockSectionParser.parse(
        progressDefinition.getBonfiresLengthDefinition(bonfiresLengthOffset), slotContentData)
        + getInt32Length();

    final ByteBlock bonfiresBlock = blockSectionParser.parse(
        progressDefinition.getBonfiresDefintion(bonfiresBlockOffset, bonfiresBlockLength),
        slotContentData);

    return bonfiresStateParser.parse(bonfiresBlock);
  }

  /**
   * Calculates the offset of the bonfire kindle states section on the slot content.
   * 
   * @param slotContentData The slot content.
   * @return The offset.
   * 
   * @throws ParserException
   * 
   * @see ProgressDefinition#getBonfiresDefintion(int, int)
   */
  // bonfire block offset is a certain block in number of blocks with dynamic size.
  // the block list starts at a fixed position.
  private int calculateBonfiresLengthOffset(final ByteBlock slotContentData)
      throws ParserException {
    int bonfiresOffset =
        progressDefinition.getBonfiresOffsetLengthCalculationDefinition().getOffset();

    // 1. block (dynamic size)
    bonfiresOffset = calculateNextOffset(slotContentData, bonfiresOffset);
    // 2. block (dynamic size)
    bonfiresOffset = calculateNextOffset(slotContentData, bonfiresOffset);
    // 3. block (fixed size)
    bonfiresOffset += 88;

    // 4. to 9. block (each dynamic size)
    int footerLength = getInt32Length();

    for (int blockNumber = 0; blockNumber < 6; blockNumber++) {
      bonfiresOffset = calculateNextOffset(slotContentData, bonfiresOffset, footerLength);
    }

    return bonfiresOffset;
  }

  /**
   * Calculates the offset of the next section after the section starting at the given offset.
   * 
   * @param slotContentData The slot content.
   * @param offset The current offset.
   * @return The next section's offset.
   * 
   * @throws ParserException
   * 
   * @see ProgressDefinition#getBonfiresDefintion(int, int)
   */
  // offset + offset length + length definition at offset
  private int calculateNextOffset(final ByteBlock slotContentData, final int offset)
      throws ParserException {
    return calculateNextOffset(slotContentData, offset, 0);
  }

  /**
   * Calculates the offset of the next section after the section starting at the given offset. The
   * current section is assumed to have a footer of the given length.
   * 
   * @param slotContentData The slot content.
   * @param offset The current offset.
   * @param footerLength The current sections footer length.
   * @return The next section's offset.
   * 
   * @throws ParserException
   * 
   * @see ProgressDefinition#getBonfiresDefintion(int, int)
   */
  // offset + offset length + length + footer length
  private int calculateNextOffset(final ByteBlock slotContentData, final int offset,
      final int footerLength) throws ParserException {
    final int blockLength = blockSectionParser
        .parse(progressDefinition.getBonfiresLengthDefinition(offset), slotContentData);

    final int nextOffset = offset + blockLength + footerLength + getInt32Length();

    return (int) nextOffset;
  }

  /**
   * The number of bytes in an unsigned int 32 (which is four).
   */
  private int getInt32Length() {
    return progressDefinition.getBonfiresOffsetLengthCalculationDefinition().getLength();
  }

}
