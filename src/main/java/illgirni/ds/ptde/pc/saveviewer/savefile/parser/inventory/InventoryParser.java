package illgirni.ds.ptde.pc.saveviewer.savefile.parser.inventory;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory.Inventory;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.ByteBlockSectionDefinition;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.inventory.InventoryDefinition;

/**
 * Parses the inventory from the byte block representing a save slot's content.
 * 
 * @author illgirni
 *
 */
@Bean
public class InventoryParser extends AbstractSaveElementParser {

  /**
   * The inventory definition.
   */
  @Inject
  private InventoryDefinition inventoryDefinition;

  /**
   * The parser for a single inventory item.
   */
  @Inject
  private InventoryItemParser itemParser;

  /**
   * Parses the inventory from the slot content byte block.
   * 
   * @param slotContentData The slot content byte block.
   * @return The inventory.
   * 
   * @throws ParserException
   */
  public Inventory parse(final ByteBlock slotContentData) throws ParserException {
    final ByteBlock inventoryBlock =
        blockSectionParser.parse(inventoryDefinition.getInventoryDefinition(), slotContentData);
    final List<ByteBlockSectionDefinition<ByteBlock>> itemBlockDefinitions =
        inventoryDefinition.getInventoryItemDefinitions();

    final Inventory inventory = new Inventory();

    for (int itemIndex = 0; itemIndex < itemBlockDefinitions.size(); itemIndex++) {
      final ByteBlock itemBlock =
          blockSectionParser.parse(itemBlockDefinitions.get(itemIndex), inventoryBlock);
      inventory.addItem(itemParser.parse(itemIndex, itemBlock));
    }

    return inventory;
  }


}
