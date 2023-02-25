package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;
import illgirni.ds.ptde.pc.saveviewer.ui.slottree.ExportedSlotsWrapper;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 * Base implementation for dialogs that allow to select a new container for an element.
 * <p/>
 * The result of the dialog is a pair consisting of a boolean (telling, if a parent has been
 * selected) and the actually selected parent. The actually selected parent can be {@code null} and
 * the boolean {@code true} when the element is supposed to become a root element.
 * 
 * @author illgirni
 *
 */
public abstract class AbstractMoveExportedElementDialog
    extends AbstractParentGroupSelectionDialog<Pair<Boolean, ExportedSlotGroup>> {

  /**
   * Button type for the save button.
   */
  private static final ButtonType SAVE_BUTTON_TYPE = new ButtonType("Save", ButtonData.OK_DONE);

  /**
   * Fills the dialog.
   * 
   * @param canBeRoot If the element to be moved can bee a root element.
   * @param parentGroups The root node containing the possible moved element parents/containers.
   */
  public AbstractMoveExportedElementDialog(final boolean canBeRoot,
      final ExportedSlotsWrapper parentGroups) {
    // initStyle(StageStyle.UTILITY);
    getDialogPane().getButtonTypes().addAll(SAVE_BUTTON_TYPE, ButtonType.CANCEL);

    final GridPane dialogContent = new GridPane();
    dialogContent.setHgap(10);
    dialogContent.setVgap(10);
    dialogContent.setPadding(new Insets(10, 10, 10, 10));
    getDialogPane().setContent(dialogContent);

    final Text parentLabel = new Text("Parent Group " + (canBeRoot ? "*" : ""));
    GridPane.setValignment(parentLabel, VPos.TOP);
    final ScrollPane parentTree = createScrollableTree(parentGroups);

    int contentRow = 0;
    final Node okButton = getDialogPane().lookupButton(SAVE_BUTTON_TYPE);
    getSelectedParent()
        .addListener((observable, oldValue, newValue) -> okButton.setDisable(newValue == null));

    if (canBeRoot) {
      okButton.setDisable(true);

    } else {
      okButton.setDisable(false);

      final ToggleGroup rootToggle = new ToggleGroup();

      final RadioButton rootRadioButton = new RadioButton("Top");
      rootRadioButton.setToggleGroup(rootToggle);
      rootRadioButton.setSelected(true);
      GridPane.setValignment(rootRadioButton, VPos.TOP);

      rootRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
          ((TreeView<?>) parentTree.getContent()).getSelectionModel().clearSelection();
          okButton.setDisable(false);
        } else {
          okButton.setDisable(true);
        }
      });

      final RadioButton parentRadioButton = new RadioButton("Group");
      parentRadioButton.setToggleGroup(rootToggle);
      parentRadioButton.setSelected(false);
      GridPane.setValignment(parentRadioButton, VPos.TOP);

      parentTree.setDisable(true);
      parentTree.disableProperty().bind(parentRadioButton.selectedProperty().not());

      final Text rootLabel = new Text("Move to *");
      GridPane.setValignment(rootLabel, VPos.TOP);

      dialogContent.add(rootLabel, 0, contentRow);
      dialogContent.add(rootRadioButton, 1, contentRow);

      contentRow++;

      dialogContent.add(parentRadioButton, 1, contentRow);

      contentRow++;
    }

    dialogContent.add(parentLabel, 0, contentRow);
    dialogContent.add(parentTree, 1, contentRow);

    setResultConverter(pressedButton -> SAVE_BUTTON_TYPE.equals(pressedButton)
        ? new Pair<>(getSelectedParent().get() == null, getSelectedParent().get())
        : null);

  }

}
