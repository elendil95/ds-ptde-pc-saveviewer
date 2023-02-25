package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ReloadSaveEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.event.ShowSaveFileSlotEvent;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageButton;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageKey;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A panel listing the slots of the save file. Non-empty, non-erroneous slots are made selectable.
 * In that case proper events are fired when the slot is selected.
 * 
 * @author illgirni
 *
 */
public class SaveFileSlotsPanel extends BorderPane {

  /**
   * The container for the slot panels.
   */
  private VBox slotList;

  /**
   * The currently shown slot panels.
   */
  private List<SaveFileSlotPanel> slots = new ArrayList<>();

  /**
   * Sets up the base layout of the panel (title controls, basic node containers).
   */
  public SaveFileSlotsPanel() {

    final GridPane titleBar = new GridPane();
    titleBar.setPadding(new Insets(5));
    titleBar
        .setStyle("-fx-background-color: #fff; -fx-border-width: 0 0 1 0; -fx-border-color: #999;");
    setTop(titleBar);

    final Text title = new Text("Save File");
    title.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em");
    GridPane.setHgrow(title, Priority.ALWAYS);
    GridPane.setValignment(title, VPos.CENTER);
    titleBar.add(title, 0, 0);

    final Button reloadSaveButton = new ImageButton(ImageKey.RELOAD);
    reloadSaveButton.setTooltip(new Tooltip("Reload save file"));
    reloadSaveButton.setOnAction(event -> reloadSaveButton.fireEvent(new ReloadSaveEvent()));
    GridPane.setHgrow(reloadSaveButton, Priority.NEVER);
    GridPane.setValignment(reloadSaveButton, VPos.CENTER);
    titleBar.add(reloadSaveButton, 1, 0);

    final ScrollPane slotListScroller = new ScrollPane();
    slotList = new VBox();
    slotListScroller.setContent(slotList);
    slotListScroller.setFitToWidth(true);
    slotListScroller.setFitToHeight(true);

    setCenter(slotListScroller);

  }

  /**
   * Sets the slots to be displayed in the panel.
   * 
   * @param slots The slots to be displayed.
   * 
   * @see SaveFileSlotPanel
   */
  public void setSlots(final List<SaveSlot> slots) {
    this.slotList.getChildren().clear();
    clearSelection();

    for (final SaveSlot slot : slots) {
      final SaveFileSlotPanel slotPanel = new SaveFileSlotPanel(slot);

      if (!slot.isEmpty() && !slot.isErroneous()) {
        slotPanel.setOnMouseClicked(event -> {
          clearSelection();
          slotPanel.fireEvent(new ShowSaveFileSlotEvent(slot));
          slotPanel.requestFocus();
          slotPanel.setSelected(true);
        });
      }

      this.slots.add(slotPanel);
      slotList.getChildren().add(slotPanel);
    }
  }

  public void clearSelection() {
    slots.forEach(slot -> slot.setSelected(false));
  }

}
