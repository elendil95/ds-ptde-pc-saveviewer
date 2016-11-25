package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * Dialog to choose a new name for an element. The result of the dialog is simply the new name.
 * 
 * @author illgirni
 *
 */
public class RenameExportedElementDialog extends Dialog<String> {
    
    /**
     * The save button type.
     */
    private static final ButtonType SAVE_BUTTON_TYPE = new ButtonType("Save", ButtonData.OK_DONE);
    
    /**
     * The new name of the element to rename.
     */
    private final SimpleStringProperty elementName = new SimpleStringProperty();
    
    /**
     * @param currentElementName The current name of the element to rename.
     */
    public RenameExportedElementDialog(final String currentElementName) {
        BasicDialogs.applySaveViewerStyle(this);
        setTitle("Rename Element");
        setHeaderText("Define a new name for the element.");
        //initStyle(StageStyle.UTILITY);
        
        //Buttons
        getDialogPane().getButtonTypes().addAll(SAVE_BUTTON_TYPE, ButtonType.CANCEL);
        
        final Node okButton = getDialogPane().lookupButton(SAVE_BUTTON_TYPE);
        okButton.setDisable(currentElementName == null || currentElementName.trim().isEmpty());
        
        //Input fields
        final GridPane dialogContent = new GridPane();
        dialogContent.setHgap(10);
        dialogContent.setVgap(10);
        dialogContent.setPadding(new Insets(10, 10, 10, 10));
        getDialogPane().setContent(dialogContent);
        
        final Text nameLabel = new Text("New Name *");
        final TextField nameField = new TextField(currentElementName);
        nameField.setPromptText("name");
        nameField.setMinWidth(300);
        nameField.setMaxWidth(Double.MAX_VALUE);
        
        GridPane.setHgrow(nameField, Priority.ALWAYS);
        Platform.runLater(() -> nameField.requestFocus());
        
        dialogContent.add(nameLabel, 0, 0);
        dialogContent.add(nameField, 1, 0);
        
        this.elementName.bind(nameField.textProperty());
        this.elementName.addListener((observable, oldValue, newValue) -> okButton.setDisable(newValue == null || newValue.trim().isEmpty()));
        
        setResultConverter(pressedButton -> SAVE_BUTTON_TYPE.equals(pressedButton) ? this.elementName.get() : null);
        
    }
    
}
