package illgirni.ds.ptde.pc.saveviewer.ui.layout.stats;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.AttunedMagic;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.equipment.EquippedItem;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.AbstractDetailsPanel;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;

/**
 * A details panel which shows a character's equipped items and attuned magics. The shown elements
 * are grouped in thematic lists (e.g. armor, weapons, ammunition), which are aligned as a grid with
 * two columns. Each list entry consists of label-value-pairs.
 * 
 * @author illgirni
 *
 */
public class EquipmentPanel extends AbstractDetailsPanel {

  private Text head;

  private Text body;

  private Text hands;

  private Text legs;

  private Text rightWeapon1;

  private Text rightWeapon2;

  private Text leftWeapon1;

  private Text leftWeapon2;

  private Text arrows1;

  private Text arrows2;

  private Text bolts1;

  private Text bolts2;

  private Text ring1;

  private Text ring2;

  private Text consumable1;

  private Text consumable2;

  private Text consumable3;

  private Text consumable4;

  private Text consumable5;

  private int firstMagicRow;

  private List<Node> magicCells = new ArrayList<>();

  /** {@inheritDoc} */
  @Override
  protected String getTitle() {
    return "Equipment";
  }

  /**
   * Adds the "list blocks" to the container. The attuned magics block is not added yet, because the
   * number of attunable magics depends on the character. The nodes for the attuned magics are
   * created and removed by demand.
   */
  @Override
  protected void fillContentGrid(GridPane contentContainer) {
    int nextRow = createArmorBlock(contentContainer, 0, 0);
    int nextRow2 = createWeaponsBlock(contentContainer, 2, 0);
    nextRow = Integer.max(nextRow, nextRow2);

    contentContainer.add(new Text(""), 0, nextRow++);

    nextRow = createRingBlock(contentContainer, 0, nextRow);

    contentContainer.add(new Text(""), 0, nextRow++);

    nextRow = createAmmunitionBlock(contentContainer, 0, nextRow);

    contentContainer.add(new Text(""), 0, nextRow++);

    nextRow = createConsumableBlock(contentContainer, 0, nextRow);

    contentContainer.add(new Text(""), 0, nextRow++);

    // magic block is recreated on each update
    firstMagicRow = nextRow;
  }

  /**
   * Adds the armor cells to the container.
   * 
   * @param equipmentContainer The container
   * @param column The column for the armor cells.
   * @param row The row of the first armor cell.
   * @return The index of the next free row in the container.
   */
  private int createArmorBlock(final GridPane equipmentContainer, final int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(equipmentContainer, "Head", VPos.TOP, labelColumn, row);
    head = addValueCell(equipmentContainer, "", VPos.TOP, valueColumn, row++);

    addLabelGridCell(equipmentContainer, "Chest", VPos.CENTER, labelColumn, row);
    body = addValueCell(equipmentContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(equipmentContainer, "Hands", VPos.CENTER, labelColumn, row);
    hands = addValueCell(equipmentContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(equipmentContainer, "Legs", VPos.BOTTOM, labelColumn, row);
    legs = addValueCell(equipmentContainer, "", VPos.BOTTOM, valueColumn, row++);

    return row;
  }

  /**
   * Adds the weapon cells to the container. Uses up one "column" in the container.
   * 
   * @param equipmentContainer The container
   * @param column The column for the weapon cells.
   * @param row The row of the first weapon cell.
   * @return The index of the next free row in the container.
   */
  private int createWeaponsBlock(final GridPane equipmentContainer, final int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(equipmentContainer, "Right Weapon 1", VPos.TOP, labelColumn, row);
    rightWeapon1 = addValueCell(equipmentContainer, "", VPos.TOP, valueColumn, row++);

    addLabelGridCell(equipmentContainer, "Right Weapon 2", VPos.CENTER, labelColumn, row);
    rightWeapon2 = addValueCell(equipmentContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(equipmentContainer, "Left Weapon 1", VPos.CENTER, labelColumn, row);
    leftWeapon1 = addValueCell(equipmentContainer, "", VPos.CENTER, valueColumn, row++);

    addLabelGridCell(equipmentContainer, "Left Weapon 2", VPos.BOTTOM, labelColumn, row);
    leftWeapon2 = addValueCell(equipmentContainer, "", VPos.BOTTOM, valueColumn, row++);

    return row;
  }

  /**
   * Adds the ammunition cells to the container. Uses up one "column" in the container.
   * 
   * @param equipmentContainer The container
   * @param column The column for the ammunition cells.
   * @param row The row of the first ammunition cell.
   * @return The index of the next free row in the container.
   */
  private int createAmmunitionBlock(final GridPane equipmentContainer, final int column, int row) {
    final int labelColumnArrows = column;
    final int valueColumnArrows = labelColumnArrows + 1;

    final int labelColumnBolts = valueColumnArrows + 1;
    final int valueColumnBolts = labelColumnBolts + 1;

    addLabelGridCell(equipmentContainer, "Arrows 1", VPos.TOP, labelColumnArrows, row);
    arrows1 = addValueCell(equipmentContainer, "", VPos.TOP, valueColumnArrows, row);

    addLabelGridCell(equipmentContainer, "Bolts 1", VPos.TOP, labelColumnBolts, row);
    bolts1 = addValueCell(equipmentContainer, "", VPos.TOP, valueColumnBolts, row);

    row++;

    addLabelGridCell(equipmentContainer, "Arrows 2", VPos.BOTTOM, labelColumnArrows, row);
    arrows2 = addValueCell(equipmentContainer, "", VPos.BOTTOM, valueColumnArrows, row);

    addLabelGridCell(equipmentContainer, "Bolts 2", VPos.BOTTOM, labelColumnBolts, row);
    bolts2 = addValueCell(equipmentContainer, "", VPos.BOTTOM, valueColumnBolts, row);

    row++;

    return row;
  }

  /**
   * Adds the ring cells to the container. Uses up two "columns" in the container.
   * 
   * @param equipmentContainer The container
   * @param column The column for the ring cells.
   * @param row The row of the first ring cell.
   * @return The index of the next free row in the container.
   */
  private int createRingBlock(final GridPane equipmentContainer, final int column, int row) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    addLabelGridCell(equipmentContainer, "Ring 1", VPos.BASELINE, labelColumn, row);
    ring1 = addValueCell(equipmentContainer, "", VPos.BASELINE, valueColumn, row);

    addLabelGridCell(equipmentContainer, "Ring 2", VPos.BASELINE, labelColumn + 2, row);
    ring2 = addValueCell(equipmentContainer, "", VPos.BASELINE, valueColumn + 2, row);

    row++;

    return row;
  }

  /**
   * Adds the consumable cells to the container. Uses up two "columns" in the container.
   * 
   * @param equipmentContainer The container
   * @param column The column for the consumable cells.
   * @param row The row of the first consumable cell.
   * @return The index of the next free row in the container.
   */
  private int createConsumableBlock(final GridPane equipmentContainer, final int column, int row) {
    int labelColumn = column;
    int valueColumn = labelColumn + 1;

    addLabelGridCell(equipmentContainer, "Consumable 1", VPos.TOP, labelColumn, row);
    consumable1 = addValueCell(equipmentContainer, "", VPos.TOP, valueColumn, row);

    addLabelGridCell(equipmentContainer, "Consumable 4", VPos.TOP, labelColumn + 2, row);
    consumable4 = addValueCell(equipmentContainer, "", VPos.TOP, valueColumn + 2, row);

    row++;

    addLabelGridCell(equipmentContainer, "Consumable 2", VPos.CENTER, labelColumn, row);
    consumable2 = addValueCell(equipmentContainer, "", VPos.CENTER, valueColumn, row);

    addLabelGridCell(equipmentContainer, "Consumable 5", VPos.BOTTOM, labelColumn + 2, row);
    consumable5 = addValueCell(equipmentContainer, "", VPos.BOTTOM, valueColumn + 2, row);

    row++;

    addLabelGridCell(equipmentContainer, "Consumable 3", VPos.BOTTOM, labelColumn, row);
    consumable3 = addValueCell(equipmentContainer, "", VPos.BOTTOM, valueColumn, row);

    row++;

    return row;
  }

  public void setHead(final EquippedItem head) {
    this.head.setText(asString(head));
  }

  public void setBody(final EquippedItem body) {
    this.body.setText(asString(body));
  }

  public void setHands(final EquippedItem hands) {
    this.hands.setText(asString(hands));
  }

  public void setLegs(final EquippedItem legs) {
    this.legs.setText(asString(legs));
  }

  public void setRightWeapon1(final EquippedItem rightWeapon1) {
    this.rightWeapon1.setText(asString(rightWeapon1));
  }

  public void setRightWeapon2(final EquippedItem rightWeapon2) {
    this.rightWeapon2.setText(asString(rightWeapon2));
  }

  public void setLeftWeapon1(final EquippedItem leftWeapon1) {
    this.leftWeapon1.setText(asString(leftWeapon1));
  }

  public void setLeftWeapon2(final EquippedItem leftWeapon2) {
    this.leftWeapon2.setText(asString(leftWeapon2));
  }

  public void setArrows1(final EquippedItem arrows1) {
    this.arrows1.setText(asStringWithQuantity(arrows1));
  }

  public void setArrows2(final EquippedItem arrows2) {
    this.arrows2.setText(asStringWithQuantity(arrows2));
  }

  public void setBolts1(final EquippedItem bolts1) {
    this.bolts1.setText(asStringWithQuantity(bolts1));
  }

  public void setBolts2(final EquippedItem bolts2) {
    this.bolts2.setText(asStringWithQuantity(bolts2));
  }

  public void setRing1(final EquippedItem ring1) {
    this.ring1.setText(asString(ring1));
  }

  public void setRing2(final EquippedItem ring2) {
    this.ring2.setText(asString(ring2));
  }

  public void setConsumable1(final EquippedItem consumable1) {
    this.consumable1.setText(asStringWithQuantity(consumable1));
  }

  public void setConsumable2(final EquippedItem consumable2) {
    this.consumable2.setText(asStringWithQuantity(consumable2));
  }

  public void setConsumable3(final EquippedItem consumable3) {
    this.consumable3.setText(asStringWithQuantity(consumable3));
  }

  public void setConsumable4(final EquippedItem consumable4) {
    this.consumable4.setText(asStringWithQuantity(consumable4));
  }

  public void setConsumable5(final EquippedItem consumable5) {
    this.consumable5.setText(asStringWithQuantity(consumable5));
  }

  /**
   * Removes the current attuned magic nodes from the panel and created new ones for the given
   * attuned magics. This will split the given list into two groups which are then shown as two
   * "columns" in the panel.
   * 
   * @param attunedMagics
   * 
   * @see #fillContentGrid(GridPane)
   */
  public void setAttunedMagics(final List<AttunedMagic> attunedMagics) {
    magicCells.forEach(magicCell -> removeFromContent(magicCell));
    magicCells.clear();

    // half in first column; other half in second column
    final List<AttunedMagic> magicGroup1;

    if (attunedMagics.size() % 2 == 0) {
      magicGroup1 = new ArrayList<>(attunedMagics.subList(0, attunedMagics.size() / 2));

    } else {
      magicGroup1 = new ArrayList<>(attunedMagics.subList(0, (attunedMagics.size() / 2) + 1));

    }

    final List<AttunedMagic> magicGroup2 = new ArrayList<>(attunedMagics);
    magicGroup2.removeAll(magicGroup1);

    int labelColumn = 0;
    int valueColumn = labelColumn + 1;

    for (int magicIndex = 0; magicIndex < magicGroup1.size(); magicIndex++) {
      final VPos pos;

      if (magicGroup1.size() == 1) {
        pos = VPos.BASELINE;
      } else if (magicIndex == 0) {
        pos = VPos.TOP;
      } else if (magicIndex == magicGroup1.size() - 1) {
        pos = VPos.BOTTOM;
      } else {
        pos = VPos.CENTER;
      }

      final Pair<TextFlow, Text> attunedMagic =
          createValueCell(asString(magicGroup1.get(magicIndex)), pos);
      final Node attunedMagicLabel = createLabelGridCell("Magic " + (magicIndex + 1), pos);
      magicCells.add(attunedMagicLabel);
      magicCells.add(attunedMagic.getKey());

      addToContent(attunedMagicLabel, labelColumn, firstMagicRow + magicIndex);
      addToContent(attunedMagic.getKey(), valueColumn, firstMagicRow + magicIndex);
    }

    labelColumn = 2;
    valueColumn = labelColumn + 1;

    for (int magicIndex = 0; magicIndex < magicGroup2.size(); magicIndex++) {
      final VPos pos;

      if (magicGroup2.size() == 1) {
        pos = VPos.BASELINE;
      } else if (magicIndex == 0) {
        pos = VPos.TOP;
      } else if (magicIndex == magicGroup2.size() - 1) {
        pos = VPos.BOTTOM;
      } else {
        pos = VPos.CENTER;
      }

      final Pair<TextFlow, Text> attunedMagic =
          createValueCell(asString(magicGroup2.get(magicIndex)), pos);
      final Node attunedMagicLabel =
          createLabelGridCell("Magic " + (magicGroup1.size() + magicIndex + 1), pos);
      magicCells.add(attunedMagicLabel);
      magicCells.add(attunedMagic.getKey());

      addToContent(attunedMagicLabel, labelColumn, firstMagicRow + magicIndex);
      addToContent(attunedMagic.getKey(), valueColumn, firstMagicRow + magicIndex);
    }

  }

  /**
   * Gets the String representation for an equipped item which does <i>not</i> have a quantity.
   * 
   * @param equippedItem The equipped item.
   * @return The String representation.
   */
  private String asString(EquippedItem equippedItem) {
    StringBuilder itemString = new StringBuilder();

    if (equippedItem == null) {
      itemString.append('-');
    } else if (equippedItem.getInventoryItem() == null) {
      itemString.append("???" + equippedItem.getIdSpace() + "." + equippedItem.getId() + "???");
    } else {
      if (equippedItem.getInventoryItem().getName() == null) {
        itemString.append("???" + equippedItem.getIdSpace() + "." + equippedItem.getId() + "???");
      } else {
        itemString.append(equippedItem.getInventoryItem().getName());
      }
    }

    return itemString.toString();
  }

  /**
   * Gets the String representation for an equipped item which does have a quantity. Additionally
   * shows the quantity of the item.
   * 
   * @param equippedItem The equipped item.
   * @return The String representation.
   */
  private String asStringWithQuantity(EquippedItem equippedItem) {
    StringBuilder itemString = new StringBuilder();

    if (equippedItem == null) {
      itemString.append('-');
    } else if (equippedItem.getInventoryItem() == null) {
      itemString.append("???" + equippedItem.getIdSpace() + "." + equippedItem.getId() + "???");
    } else {
      if (equippedItem.getInventoryItem().getName() == null) {
        itemString.append("???" + equippedItem.getIdSpace() + "." + equippedItem.getId() + "???");
      } else {
        itemString.append(equippedItem.getInventoryItem().getName());
      }

      itemString.append(" (").append(equippedItem.getInventoryItem().getAmount()).append(')');
    }

    return itemString.toString();
  }

  /**
   * Gets the String representation for the attuned magic.
   * 
   * @param attunedMagic The attuned magic.
   * @return The string representation.
   */
  private String asString(AttunedMagic attunedMagic) {
    StringBuilder itemString = new StringBuilder();

    if (attunedMagic == null) {
      // nothing
    } else if (attunedMagic.getInventoryItem() == null) {
      itemString.append("???" + attunedMagic.getIdSpace() + "." + attunedMagic.getId() + "???");
    } else if (attunedMagic.getInventoryItem().getName() == null) {
      itemString.append("???" + attunedMagic.getIdSpace() + "." + attunedMagic.getId() + "???");
    } else {
      itemString.append(attunedMagic.getInventoryItem().getName());
    }

    return itemString.toString();
  }

}
