package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import java.text.DecimalFormat;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * The dialog in which to choose the position at which to import an exported slot into the save file.
 * <p/>
 * The result of the dialog is the index that the slot should have in the save file.
 * 
 * @author illgirni
 *
 */
public class ImportSlotDialog extends Dialog<Integer> {
    
    /**
     * The save button type.
     */
    private static final ButtonType SAVE_BUTTON_TYPE = new ButtonType("Save", ButtonData.OK_DONE);
    
    /**
     * The index at which to import the slot.
     */
    private final SimpleObjectProperty<Integer> selectedSlot = new SimpleObjectProperty<>();
    
    /**
     * @param characterName The name of the character to be imported into the save file.
     * @param replacableSlots The current slots in the save file.
     */
    public ImportSlotDialog(final String characterName, final List<SaveSlot> replacableSlots) {
        BasicDialogs.applySaveViewerStyle(this);
        setTitle("Import Character");
        setHeaderText("Import character '" + characterName + "' into save file. Select replaced character.");
        //initStyle(StageStyle.UTILITY);
        
        //Buttons
        getDialogPane().getButtonTypes().addAll(SAVE_BUTTON_TYPE, ButtonType.CANCEL);
        
        //Input fields
        final GridPane dialogContent = new GridPane();
        dialogContent.setHgap(10);
        dialogContent.setVgap(10);
        dialogContent.setPadding(new Insets(10, 10, 10, 10));
        getDialogPane().setContent(dialogContent);
        
        final Text replacedSlotLabel = new Text("Replaced Character *");
        
        final ComboBox<SaveSlot> replacedSlotField = new ComboBox<>();
        replacedSlotField.getItems().addAll(replacableSlots);
        replacedSlotField.setCellFactory(listView -> new SaveSlotComboBoxCell());
        //this is important! otherwise the text of the selected value won't be displayed correctly.
        replacedSlotField.setButtonCell(replacedSlotField.getCellFactory().call(null)); 
        //replacedSlotField.setMinWidth(300);
        replacedSlotField.setMaxWidth(Double.MAX_VALUE);
        
        GridPane.setHgrow(replacedSlotField, Priority.ALWAYS);
        Platform.runLater(() -> replacedSlotField.requestFocus());
        
        dialogContent.add(replacedSlotLabel, 0, 0);
        dialogContent.add(replacedSlotField, 1, 0);
        
        replacedSlotField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { 
            if (newValue == null) { 
                selectedSlot.set(null); 
            } else { 
                selectedSlot.set(replacableSlots.indexOf(newValue));
            }
        });
        
        final Node okButton = getDialogPane().lookupButton(SAVE_BUTTON_TYPE);
        okButton.setDisable(true);
        selectedSlot.addListener((observable, oldValue, newValue) -> okButton.setDisable(newValue == null));
        
        setResultConverter(pressedButton -> SAVE_BUTTON_TYPE.equals(pressedButton) ? selectedSlot.get() : null);
        
    }
    
    /**
     * Custom combo box cell defining the cell text based on the save value's content.
     */
    private class SaveSlotComboBoxCell extends ListCell<SaveSlot> {
        @Override
        protected void updateItem(SaveSlot item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            
            if (empty) {
                setGraphic(null);
            } else if (item == null) {
                setGraphic(new Text("Please choose..."));
            } else {
                final Text label = new Text();
                final String displayedIndex = new DecimalFormat("00").format(item.getIndex() + 1);
                
                if (item.isEmpty()) {
                    label.setText(displayedIndex + ". EMPTY");
                } else if (item.isErroneous()) {
                    label.setText(displayedIndex + ". DEFECT");
                } else {
                    label.setText(displayedIndex + ". " + item.getContent().getCharacterStatistics().getName());
                }
                
                setGraphic(label);
            }
            
        }
    }
    
}