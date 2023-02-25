package illgirni.ds.ptde.pc.saveviewer.savefile.parser.inventory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.ItemIdSpaceParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.ItemIdSpace;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.inventory.InventoryItem;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.inventory.InventoryItemDefinition;

/**
 * Parses an inventory item from a byte block representing only that one inventory item.
 * 
 * @author illgirni
 *
 */
@Bean
public class InventoryItemParser extends AbstractSaveElementParser {

  /**
   * The definition of the inventory item values in the byte block.
   */
  @Inject
  private InventoryItemDefinition itemDefinition;

  /**
   * The item main type parser.
   */
  @Inject
  private ItemIdSpaceParser idSpaceParser;

  /**
   * The bundle containing the item names ("maps" item type + id to item name).
   */
  private ResourceBundle itemNameBundle =
      ResourceBundle.getBundle(InventoryItemParser.class.getPackage().getName() + ".items");

  /**
   * Parses the {@link InventoryItem} from the byte block.
   * 
   * @param itemIndex The index/offset of the item in the inventory list.
   * @param itemBlock The byte block with the item data.
   * @return The {@link InventoryItem}; {@code null} when the inventory slot is empty.
   * 
   * @throws ParserException
   */
  public InventoryItem parse(final int itemIndex, final ByteBlock itemBlock)
      throws ParserException {
    final ByteBlock idSpaceBlock =
        blockSectionParser.parse(itemDefinition.getIdSpaceDefinition(), itemBlock);
    final ItemIdSpace idSpace = idSpaceParser.parse(idSpaceBlock);

    if (idSpace != null) {
      final InventoryItem item = new InventoryItem();

      item.setAmount(blockSectionParser.parse(itemDefinition.getAmountDefinition(), itemBlock));
      item.setDurability(
          blockSectionParser.parse(itemDefinition.getDurabilityDefinition(), itemBlock));
      item.setDurabilityLoss(
          blockSectionParser.parse(itemDefinition.getDurabilityLossDefinition(), itemBlock));
      item.setEnabled(blockSectionParser.parse(itemDefinition.getEnabledDefinition(), itemBlock));
      item.setId(blockSectionParser.parse(itemDefinition.getIdDefinition(), itemBlock));

      item.setIdSpace(idSpace);
      item.setIndex(itemIndex);
      item.setSorting(blockSectionParser.parse(itemDefinition.getSortingDefinition(), itemBlock));

      try {
        item.setName(itemNameBundle.getString(item.getIdSpace() + "." + item.getId()));
      } catch (MissingResourceException e) {
        // TODO log missing item name.
      }

      return item;

    } else {
      return null;
    }
  }

}
