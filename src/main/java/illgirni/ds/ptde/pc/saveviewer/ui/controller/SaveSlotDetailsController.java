package illgirni.ds.ptde.pc.saveviewer.ui.controller;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.SaveSlotDetailsPanel;

import javafx.scene.Node;

/**
 * Controller for the panel showing the details of a save slot.
 * 
 * @author illgirni
 *
 */
public class SaveSlotDetailsController {

  /**
   * The slot details panel.
   */
  private SaveSlotDetailsPanel detailsPanel = new SaveSlotDetailsPanel();

  /**
   * Which "kind" of slot is currently displayed: {@code true} when it is an exported slot:
   * {@code false} when it is a slot from the save file.
   */
  private Boolean standaloneSlot;

  /**
   * Updates the displayed details to the new slot. A "de-selection" can be expressed by passing in
   * {@code null} for the slot. A de-selection (clearing the panel content) will only take place
   * when the currently shown slot is an exported slot and the de-selection is requested for an
   * exported slot - and the same for the slots from the save file.
   * 
   * @param slot The slot for which to display the details.
   * @param standalone If the slot is an exported slot ({@code true}) or a slot from the save file
   *        ({@code false}).
   */
  public void updateSaveSlot(final SaveSlot slot, final boolean standalone) {

    if (standaloneSlot == null || Boolean.compare(standaloneSlot, standalone) == 0
        || slot != null) {
      this.standaloneSlot = standalone;

      detailsPanel.update(slot, standalone);

    }
  }

  /**
   * The details panel.
   */
  public Node getPanel() {
    return detailsPanel;
  }

}
