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
 * A dialog to define the required values for the creation of a new exported slot group.
 * <p/>
 * The result is a pair consisting of the name of the new group and the parent of the new group. When the
 * parent in the result is {@code null}, this indicates that the new group is a root group.
 * 
 * 
 * @author illgirni
 *
 */
public class AddExportedSlotGroupDialog extends AbstractParentGroupSelectionDialog<Pair<String, ExportedSlotGroup>> {
    
    /**
     * The save button type.
     */
    private static final ButtonType SAVE_BUTTON_TYPE = new ButtonType("Save", ButtonData.OK_DONE);
    
    /**
     * The name of the new group.
     */
    private final SimpleStringProperty groupName = new SimpleStringProperty();
    
    /**
     * @param parentGroups The possible parents of the new group.
     */
    public AddExportedSlotGroupDialog(final ExportedSlots parentGroups) {
        setTitle("Add Character Group");
        setHeaderText("Define name and parent of new character group.");
        //initStyle(StageStyle.UTILITY);
        
        //Buttons
        getDialogPane().getButtonTypes().addAll(SAVE_BUTTON_TYPE, ButtonType.CANCEL);
        
        final Node okButton = getDialogPane().lookupButton(SAVE_BUTTON_TYPE);
        okButton.setDisable(true);
        
        //Input fields
        final GridPane dialogContent = new GridPane();
        dialogContent.setHgap(10);
        dialogContent.setVgap(10);
        dialogContent.setPadding(new Insets(10, 10, 10, 10));
        getDialogPane().setContent(dialogContent);
        
        final Text nameLabel = new Text("Group Name *");
        final TextField nameField = new TextField();
        nameField.setPromptText("group name");
        nameField.setMinWidth(300);
        nameField.setMaxWidth(Double.MAX_VALUE);
        
        GridPane.setHgrow(nameField, Priority.ALWAYS);
        Platform.runLater(() -> nameField.requestFocus());
        
        dialogContent.add(nameLabel, 0, 0);
        dialogContent.add(nameField, 1, 0);
        
        final Text parentLabel = new Text("Parent Group");
        GridPane.setValignment(parentLabel, VPos.TOP);
        final ScrollPane parentTree = createScrollableTree(new ExportedSlotsWrapper(parentGroups, true));
        
        dialogContent.add(parentLabel, 0, 1);
        dialogContent.add(parentTree, 1, 1);
        
        groupName.bind(nameField.textProperty());
        groupName.addListener((observable, oldValue, newValue) -> okButton.setDisable(newValue == null || newValue.trim().isEmpty()));
        
        setResultConverter(pressedButton -> SAVE_BUTTON_TYPE.equals(pressedButton) ? new Pair<>(groupName.get(), getSelectedParent().get()) : null);
        
    }
    
}