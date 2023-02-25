package illgirni.ds.ptde.pc.saveviewer.ui.layout.progress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;
import illgirni.ds.ptde.pc.saveviewer.ui.i18n.Messages;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.AbstractDetailsPanel;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * A details panel which shows the tail cuts. For layout reasons they are split in two groups, which
 * are aligned as a grid with two columns. Each list entry consists of label-value-pairs. The order
 * of the tail cuts corresponds to the discovery order in a "usual playthrough".
 * 
 * @author illgirni
 *
 */
public class TailcutsPanel extends AbstractDetailsPanel {

  /**
   * The text for each tail cut showing the "is cut" information.
   */
  private Map<TailOwner, Text> tailcuts;

  /** {@inheritDoc} */
  @Override
  protected String getTitle() {
    return "Tailcuts";
  }

  /**
   * Adds the two tail cut blocks to the container.
   */
  @Override
  protected void fillContentGrid(GridPane contentContainer) {
    tailcuts = new HashMap<>();

    createBlock1(contentContainer, 0, 0);
    createBlock2(contentContainer, 2, 0);

  }

  /**
   * Adds the tail cuts block 1 to the container.
   * 
   * @param tailcutContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock1(final GridPane tailcutContainer, final int column, int row) {
    List<TailOwner> tailOwnerBlock = Arrays.asList(TailOwner.HELLKITE, TailOwner.BELL_GARGOYLES,
        TailOwner.GAPING_DRAGON, TailOwner.STONE_DRAGON);

    createBlock(tailcutContainer, column, row, tailOwnerBlock);

    return row + tailOwnerBlock.size();

  }

  /**
   * Adds the tail cuts block 2 to the container.
   * 
   * @param tailcutContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @return The index of the next free row in the container.
   */
  private int createBlock2(GridPane tailcutContainer, int column, int row) {
    List<TailOwner> tailOwnerBlock = Arrays.asList(TailOwner.PRISCILLA, TailOwner.SEATH,
        TailOwner.SANCTUARY_GUARDIAN, TailOwner.KALAMEET);

    createBlock(tailcutContainer, column, row, tailOwnerBlock);

    return row + tailOwnerBlock.size();

  }

  /**
   * Adds the tail cuts block to the container.
   * 
   * @param tailcutsContainer The container
   * @param column The column for the cells.
   * @param row The row of the first cell.
   * @param tailOwnersBlock The tail owners of the block.
   */
  private void createBlock(GridPane tailcutsContainer, int column, int row,
      List<TailOwner> tailOwnersBlock) {
    final int labelColumn = column;
    final int valueColumn = labelColumn + 1;

    for (int tailOwnerIndex = 0; tailOwnerIndex < tailOwnersBlock.size(); tailOwnerIndex++) {
      final TailOwner tailOwner = tailOwnersBlock.get(tailOwnerIndex);
      final VPos position;

      if (tailOwnerIndex == 0) {
        position = VPos.TOP;
      } else if (tailOwnerIndex == tailOwnersBlock.size() - 1) {
        position = VPos.BOTTOM;
      } else {
        position = VPos.CENTER;
      }

      addLabelGridCell(tailcutsContainer, getTailOwnerName(tailOwner), position, labelColumn, row);
      tailcuts.put(tailOwner, addValueCell(tailcutsContainer, "", position, valueColumn, row++));

    }

  }

  /**
   * The displayed name for a tail owner.
   */
  private String getTailOwnerName(final TailOwner tailOwner) {
    return Messages.getMessage(tailOwner);
  }

  /**
   * Updates the panel title to show number of cut tails.
   */
  public void setTailcutsProgress(final int cut) {
    setTitle("Tailcuts (" + cut + " / " + TailOwner.values().length + ")");
  }

  /**
   * Sets the text for the tail cut of a tail owner.
   * 
   * @param tailOwner The tail owner.
   * @param cut If the tail has been cut.
   */
  public void setTailcut(final TailOwner tailOwner, final boolean cut) {
    tailcuts.get(tailOwner).setText(cut ? "stumpy" : "wiggly");
  }

}
