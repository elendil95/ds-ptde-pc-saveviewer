package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlots;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 * The dialog to choose the exported slot group in which to put a newly exported slot and the custom
 * name for the exported slot.
 * <p/>
 * The result of the dialog is a pair consisting of name of the exported slot and the group that
 * shall contain the slot.
 * 
 * @author illgirni
 *
 */
public class ExportSlotDialog
    extends AbstractParentGroupSelectionDialog<Pair<String, ExportedSlotGroup>> {

  /**
   * The save button type.
   */
  private static final ButtonType SAVE_BUTTON_TYPE = new ButtonType("Save", ButtonData.OK_DONE);

  /**
   * The name of the exported slot.
   */
  private SimpleStringProperty name = new SimpleStringProperty();

  /**
   * @param characterName The name of the character in the slot to be exported.
   * @param parentGroups The available parent groups.
   */
  public ExportSlotDialog(final String characterName, final ExportedSlots parentGroups) {
    super();

    setTitle("Export Character");
    setHeaderText("Select name and group for exported character '" + characterName + "'.");

    // initStyle(StageStyle.UTILITY);
    getDialogPane().getButtonTypes().addAll(SAVE_BUTTON_TYPE, ButtonType.CANCEL);

    final GridPane dialogContent = new GridPane();
    dialogContent.setHgap(10);
    dialogContent.setVgap(10);
    dialogContent.setPadding(new Insets(10, 10, 10, 10));
    getDialogPane().setContent(dialogContent);

    final Text nameLabel = new Text("Name *");
    final TextField nameField = new TextField();
    nameField.setPromptText("name");
    nameField.setMinWidth(300);
    nameField.setMaxWidth(Double.MAX_VALUE);
    name.bind(nameField.textProperty());
    nameField.setText(characterName);

    GridPane.setHgrow(nameField, Priority.ALWAYS);
    Platform.runLater(() -> nameField.requestFocus());

    dialogContent.add(nameLabel, 0, 0);
    dialogContent.add(nameField, 1, 0);

    final Text parentLabel = new Text("Group *");
    GridPane.setValignment(parentLabel, VPos.TOP);
    final ScrollPane parentTree =
        createScrollableTree(new ExportedSlotsWrapper(parentGroups, true));

    final Node okButton = getDialogPane().lookupButton(SAVE_BUTTON_TYPE);

    dialogContent.add(parentLabel, 0, 1);
    dialogContent.add(parentTree, 1, 1);

    defineOkButtonState(okButton, characterName, getSelectedParent().get());
    name.addListener((observable, oldValue, newValue) -> defineOkButtonState(okButton, newValue,
        getSelectedParent().get()));
    getSelectedParent().addListener(
        (observable, oldValue, newValue) -> defineOkButtonState(okButton, name.get(), newValue));

    setResultConverter(pressedButton -> SAVE_BUTTON_TYPE.equals(pressedButton)
        ? new Pair<>(name.get(), getSelectedParent().get())
        : null);

  }

  private void defineOkButtonState(final Node okButton, final String name,
      final ExportedSlotGroup group) {
    okButton.setDisable(name == null || name.trim().isEmpty() || group == null);
  }
}
