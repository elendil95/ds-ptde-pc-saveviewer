package illgirni.ds.ptde.pc.saveviewer.ui.layout.progress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Boss;
import illgirni.ds.ptde.pc.saveviewer.ui.i18n.Messages;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.AbstractDetailsPanel;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * A details panel which shows the boss defeats. For layout reasons they are split in four groups,
 * which are aligned as a grid with two columns. Each list entry consists of label-value-pairs. The
 * order of the bosses corresponds to the discovery order in a "usual playthrough".
 * 
 * @author illgirni
 *
 */
public class BossesPanel extends AbstractDetailsPanel {

  /**
   * The text for each boss showing the defeat information for the boss.
   */
  private Map<Boss, Text> bosses;

  /** {@inheritDoc} */
  @Override
  protected String getTitle() {
    return "Bosses";
  }

  /**
   * Adds the four boss blocks to the container.
   */
  @Override
  protected void fillContentGrid(GridPane contentContainer) {
    bosses = new HashMap<>();

    int nextRow = createBlock1(contentContainer, 0, 0);
    int nextRow2 = createBlock2(contentContainer, 2, 0);

    nextRow = Integer.max(nextRow, nextRow2);
    contentContainer.add(new Text(""), 0, nextRow++);

    createBlock3(contentContainer, 0, nextRow);
    createBlock4(contentContainer, 2, nextRow);

  }

  /**
   * Adds the bosses block 1 to the container.
   * 
   * @param bossesContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock1(final GridPane bossesContainer, final int column, int row) {
    List<Boss> bossBlock = Arrays.asList(Boss.ASYLUM_DEMON, Boss.TAURUS, Boss.BELL_GARGOYLES,
        Boss.CAPRA_DEMON, Boss.GAPING_DRAGON, Boss.QUELAAG);

    createBlock(bossesContainer, column, row, bossBlock);

    return row + bossBlock.size();

  }

  /**
   * Adds the bosses block 2 to the container.
   * 
   * @param bossesContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock2(GridPane bossesContainer, int column, int row) {
    List<Boss> bossBlock = Arrays.asList(Boss.CEASELESS_DISCHARGE, Boss.STRAY_DEMON,
        Boss.MOONLIGHT_BUTTERFLY, Boss.IRON_GOLEM, Boss.ORNSTEIN_AND_SMOUGH, Boss.GWYNDLOLIN);

    createBlock(bossesContainer, column, row, bossBlock);

    return row + bossBlock.size();

  }

  /**
   * Adds the bosses block 3 to the container.
   * 
   * @param bossesContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock3(GridPane bossesContainer, int column, int row) {
    List<Boss> bossBlock = Arrays.asList(Boss.PRISCILLA, Boss.SEATH, Boss.PINWHEEL, Boss.NITO,
        Boss.DEMON_FIRESAGE, Boss.CENTIPEDE_DEMON, Boss.BED_OF_CHAOS);

    createBlock(bossesContainer, column, row, bossBlock);

    return row + bossBlock.size();

  }

  /**
   * Adds the bosses block 4 to the container.
   * 
   * @param bossesContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock4(GridPane bossesContainer, int column, int row) {
    List<Boss> bossBlock = Arrays.asList(Boss.SIF, Boss.FOUR_KINGS, Boss.SANCTUARY_GUARDIAN,
        Boss.ARTORIAS, Boss.KALAMEET, Boss.MANUS, Boss.GWYN);

    createBlock(bossesContainer, column, row, bossBlock);

    return row + bossBlock.size();

  }

  /**
   * Adds the bosses block to the container.
   * 
   * @param bossesContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @param bossBlock The bosses of the block.
   */
  private void createBlock(GridPane bosssContainer, int column, int row, List<Boss> bossBlock) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    for (int bossIndex = 0; bossIndex < bossBlock.size(); bossIndex++) {
      final Boss boss = bossBlock.get(bossIndex);
      final VPos position;

      if (bossIndex == 0) {
        position = VPos.TOP;
      } else if (bossIndex == bossBlock.size() - 1) {
        position = VPos.BOTTOM;
      } else {
        position = VPos.CENTER;
      }

      addLabelGridCell(bosssContainer, getBossName(boss), position, labelColumn, row);
      bosses.put(boss, addValueCell(bosssContainer, "", position, valueColumn, row++));

    }

  }

  /**
   * The displayed name for a boss.
   */
  private String getBossName(final Boss boss) {
    return Messages.getMessage(boss);
  }

  /**
   * Updates the panel title to show number of defeated bosses.
   */
  public void setBossesProgress(final int defeated) {
    setTitle("Bosses (" + defeated + " / " + Boss.values().length + ")");
  }

  /**
   * Sets the text for the boss' defeat state.
   * 
   * @param boss The boss.
   * @param defeated If the boss has been defeated.
   */
  public void setBoss(final Boss boss, final boolean defeated) {
    bosses.get(boss).setText(defeated ? "defeated" : "alive");
  }

}
