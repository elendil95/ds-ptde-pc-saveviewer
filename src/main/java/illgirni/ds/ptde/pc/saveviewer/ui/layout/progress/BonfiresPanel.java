package illgirni.ds.ptde.pc.saveviewer.ui.layout.progress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.BonfireStrength;
import illgirni.ds.ptde.pc.saveviewer.ui.i18n.Messages;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.AbstractDetailsPanel;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * A details panel which shows the bonfire kindle states. For layout reasons they are split in four
 * groups, which are aligned as a grid with two columns. Each list entry consists of
 * label-value-pairs. The order of the bonfires corresponds to the discovery order in a "usual
 * playthrough".
 * 
 * @author illgirni
 *
 */
public class BonfiresPanel extends AbstractDetailsPanel {

  /**
   * The text for each bonfire showing the kindle state of the bonfire.
   */
  private Map<Bonfire, Text> bonfires;

  /** {@inheritDoc} */
  @Override
  protected String getTitle() {
    return "Bonfires";
  }

  /**
   * Adds the four bonfire blocks to the container.
   */
  @Override
  protected void fillContentGrid(GridPane contentContainer) {
    bonfires = new HashMap<>();

    int nextRow = createBlock1(contentContainer, 0, 0);
    int nextRow2 = createBlock2(contentContainer, 2, 0);

    nextRow = Integer.max(nextRow, nextRow2);
    contentContainer.add(new Text(""), 0, nextRow++);

    createBlock3(contentContainer, 0, nextRow);
    createBlock4(contentContainer, 2, nextRow);

  }

  /**
   * Adds the bonfires block 1 to the container.
   * 
   * @param bonfiresContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock1(final GridPane bonfiresContainer, final int column, int row) {
    List<Bonfire> bonfireBlock =
        Arrays.asList(Bonfire.UNDEAD_ASYLUM_COURTYARD, Bonfire.UNDEAD_ASYLUM_CHAMBER,
            Bonfire.FIRELINK_SHRINE, Bonfire.UNDEAD_BURG, Bonfire.SUNLIGHT_ALTAR,
            Bonfire.UNDEAD_PARISH, Bonfire.DARKROOT_GARDEN, Bonfire.DARKROOT_BASIN, Bonfire.DEPTHS,
            Bonfire.BLIGHTTOWN_SHANTY, Bonfire.BLIGHTTOWN_SWAMP);

    createBlock(bonfiresContainer, column, row, bonfireBlock);

    return row + bonfireBlock.size();
  }

  /**
   * Adds the bonfires block 2 to the container.
   * 
   * @param bonfiresContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock2(GridPane bonfiresContainer, int column, int row) {
    List<Bonfire> bonfireBlock = Arrays.asList(Bonfire.GREAT_HOLLOW, Bonfire.ASH_LAKE,
        Bonfire.STONE_DRAGON, Bonfire.SENS_FORTRESS, Bonfire.ANOR_LONDO, Bonfire.ANOR_LONDO_KEEP,
        Bonfire.CHAMBER_OF_THE_PRINCESS, Bonfire.DARKMOON_TOMB, Bonfire.PAINTED_WORLD,
        Bonfire.DUKES_ARCHIVE_ENTRANCE, Bonfire.DUKES_ARCHIVE_PRISON);

    createBlock(bonfiresContainer, column, row, bonfireBlock);

    return row + bonfireBlock.size();
  }

  /**
   * Adds the bonfires block 3 to the container.
   * 
   * @param bonfiresContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock3(GridPane bonfiresContainer, int column, int row) {
    List<Bonfire> bonfireBlock = Arrays.asList(Bonfire.DUKES_ARCHIVE, Bonfire.CRYSTAL_CAVES,
        Bonfire.CATACOMBS_ENTRANCE, Bonfire.CATACOMBS, Bonfire.TOMB_OF_GIANTS_1,
        Bonfire.TOMB_OF_GIANTS_2, Bonfire.GRAVELORD_ALTAR, Bonfire.DAUGHTER_OF_CHAOS,
        Bonfire.DEMON_RUINS_1, Bonfire.DEMON_RUINS_2, Bonfire.DEMON_RUINS_3);

    createBlock(bonfiresContainer, column, row, bonfireBlock);

    return row + bonfireBlock.size();
  }

  /**
   * Adds the bonfires block 4 to the container.
   * 
   * @param bonfiresContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock4(GridPane bonfiresContainer, int column, int row) {
    List<Bonfire> bonfireBlock = Arrays.asList(Bonfire.LOST_IZALITH_ENTRANCE, Bonfire.LOST_IZALITH,
        Bonfire.BED_OF_CHAOS, Bonfire.ABYSS, Bonfire.SANCTUARY_GARDEN, Bonfire.OOLACILE_SANCTUARY,
        Bonfire.OOLACILE_TOWNSHIP, Bonfire.OOLACILE_DUNGEON, Bonfire.CHASM_OF_THE_ABYSS,
        Bonfire.FIRELINK_ALTAR);

    createBlock(bonfiresContainer, column, row, bonfireBlock);

    return row + bonfireBlock.size();
  }

  /**
   * Adds the bonfires block to the container.
   * 
   * @param bonfiresContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @param bonfireBlock The bonfires of the block.
   */
  private void createBlock(GridPane bonfiresContainer, int column, int row,
      List<Bonfire> bonfireBlock) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    for (int bonfireIndex = 0; bonfireIndex < bonfireBlock.size(); bonfireIndex++) {
      final Bonfire bonfire = bonfireBlock.get(bonfireIndex);
      final VPos position;

      if (bonfireIndex == 0) {
        position = VPos.TOP;
      } else if (bonfireIndex == bonfireBlock.size() - 1) {
        position = VPos.BOTTOM;
      } else {
        position = VPos.CENTER;
      }

      addLabelGridCell(bonfiresContainer, getBonfireName(bonfire), position, labelColumn, row);
      bonfires.put(bonfire, addValueCell(bonfiresContainer, "", position, valueColumn, row++));

    }

  }

  /**
   * The displayed name for a bonfire.
   */
  private String getBonfireName(final Bonfire bonfire) {
    return Messages.getMessage(bonfire);
  }

  /**
   * Sets the text for the bonfire's kindle state.
   * 
   * @param bonfire The bonfire.
   * @param strength The kindle state.
   */
  public void setBonfireStrength(final Bonfire bonfire, final BonfireStrength strength) {
    bonfires.get(bonfire).setText(Messages.getMessage(strength));
  }

  /**
   * Updates the title of the panel to show the number of lit bonfires.
   */
  public void setBonfiresProgress(int bonfiresLitCount) {
    setTitle("Bonfires (" + bonfiresLitCount + " / " + Bonfire.values().length + ")");
  }

}
