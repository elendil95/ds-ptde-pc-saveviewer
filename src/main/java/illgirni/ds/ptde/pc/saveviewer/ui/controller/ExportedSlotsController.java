package illgirni.ds.ptde.pc.saveviewer.ui.controller;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlots;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.ExportedSlotsPanel;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;

import javafx.scene.Node;

/**
 * Controller for the panel that shows the exported slots.
 * 
 * @author illgirni
 *
 */
public class ExportedSlotsController {

  /**
   * The panel showing the exported slots.
   */
  private final ExportedSlotsPanel exportedSlotsPanel = new ExportedSlotsPanel();

  /**
   * The tree shown in the panel.
   */
  private final ExportedSlotsWrapper slotTree;

  /**
   * @param slotsStructure The slots to show in the panel managed by this controller.
   */
  public ExportedSlotsController(final ExportedSlots slotsStructure) {
    slotTree = new ExportedSlotsWrapper(slotsStructure);
    exportedSlotsPanel.setSlotTree(slotTree);
  }

  /**
   * Clears the selection in the panel of this controller.
   */
  public void clearSelection() {
    exportedSlotsPanel.clearSelection();
  }

  /**
   * The panel managed by this controller.
   */
  public Node getPanel() {
    return exportedSlotsPanel;
  }

  /**
   * Refreshes the tree in the panel.
   */
  public void refreshSlotTree() {
    slotTree.refresh();
  }

}
