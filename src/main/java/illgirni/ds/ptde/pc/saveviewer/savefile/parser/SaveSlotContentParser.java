package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats.CharacterStatisticsParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.equipment.EquipmentParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.CheckSumConflictException;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.inventory.InventoryParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress.ProgressParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlotContent;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CharacterStatistics;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.AttunedMagic;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.Equipment;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.EquippedItem;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory.Inventory;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.SaveSlotContentDefinition;
import illgirni.ds.ptde.pc.saveviewer.utils.ByteUtils;

/**
 * Parses the character information from a block of bytes representing <i>only</i> the content of a
 * save slot (i.e. excluding the save slots checksum and slot content length definition). Also
 * validates the slot content against the checksum in the byte block.
 * <p/>
 * Mostly delegates to other parsers for the actual parsing task.
 * 
 * @author illgirni
 *
 */
@Bean
public class SaveSlotContentParser extends AbstractSaveElementParser {

  /**
   * The slot content definition.
   */
  @Inject
  private SaveSlotContentDefinition slotContentDefinition;

  /**
   * The character stats parser.
   */
  @Inject
  private CharacterStatisticsParser characterStatParser;

  /**
   * The inventory parser.
   */
  @Inject
  private InventoryParser inventoryParser;

  /**
   * The equipment parser.
   */
  @Inject
  private EquipmentParser equipmentParser;

  /**
   * The playthrough progress parser.
   */
  @Inject
  private ProgressParser progressParser;

  /**
   * Checksum validator.
   */
  @Inject
  private CheckSumValidator checkSumValidator;

  /**
   * Parses the block of bytes to save slot content. Does not put anything into the slot content, if
   * there is no character name and no character level in the character statistics.
   */
  public SaveSlotContent parse(final ByteBlock slotContentData) throws ParserException {
    final SaveSlotContent slotContent = new SaveSlotContent();
    final CharacterStatistics characterStats = characterStatParser.parse(slotContentData);

    if (characterStats.getName() != null && !characterStats.getName().isEmpty()
        && characterStats.getLevel() > 0) {
      slotContent.setCheckSum(parseCheckSum(slotContentData));

      slotContent.setCharacterStatistics(characterStats);
      slotContent.setInventory(inventoryParser.parse(slotContentData));
      slotContent.setEquipment(equipmentParser.parse(slotContentData));
      slotContent.setProgress(progressParser.parse(slotContentData));

      connectEquipmentToInventory(slotContent.getEquipment(), slotContent.getInventory());

    } else {
      // empty slot
    }


    return slotContent;
  }

  /**
   * Parses the checksum from the byte block and validates the content bytes against the checksum.
   * 
   * @param slotContentData The slot content bytes.
   * @return The parsed checksum
   * 
   * @throws CheckSumConflictException
   */
  protected byte[] parseCheckSum(final ByteBlock slotContentData) throws CheckSumConflictException {
    final ByteBlockSectionDefinition<byte[]> checkSumDefinition =
        slotContentDefinition.getCheckSumDefinition(slotContentData.getLength());
    final byte[] checkSum = blockSectionParser.parse(checkSumDefinition, slotContentData);

    final ByteBlock checkSummedData = ByteUtils.readBlock(slotContentData.getBlockData(), 0,
        slotContentData.getLength() - checkSum.length);
    checkSumValidator.validateCheckSum(checkSum, checkSummedData);

    return checkSum;
  }

  /**
   * Connects the items in the equipment to their corresponding inventory items.
   */
  protected void connectEquipmentToInventory(final Equipment equipment, final Inventory inventory) {
    for (final EquippedItem equippedItem : equipment.getEquippedItems()) {
      equippedItem.setInventoryItem(inventory.findItemByInventoryIndex(equippedItem.getIdSpace(),
          equippedItem.getInventoryIndex()));
    }

    for (final AttunedMagic attunedMagic : equipment.getAttunedMagics()) {
      attunedMagic.setInventoryItem(
          inventory.findItemById(attunedMagic.getIdSpace(), attunedMagic.getId()));
    }

  }

}
