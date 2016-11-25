package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import java.text.DecimalFormat;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CharacterStatistics;
import illgirni.ds.ptde.pc.saveviewer.ui.i18n.Messages;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Panel for a single save slot in the save file. Shows the basic slot information
 * (like i fit is empty, character name, playtime, location, and ng state). 
 * 
 * @author illgirni
 *
 */
public class SaveFileSlotPanel extends VBox {
    
    /**
     * If the panel is currently selected.
     */
    private boolean selected = false;
    
    /**
     * The "unselected" style of the panel.
     */
    private String originalStyle;
    
    /**
     * Builds the panel, so that it shows the basic slot information. The panel will only have a 
     * "selection style" when the slot is not empty and not erroneous. In other cases it appears 
     * unselectable.
     * 
     * @param slot The slot shown in the panel.
     */
    public SaveFileSlotPanel(final SaveSlot slot) {
        setFillWidth(true);
        originalStyle = "-fx-border-width: 0 0 1 0; -fx-border-color: #999;";
        setStyle(originalStyle);
        
        setFocusTraversable(true);
        focusedProperty().addListener((observable, oldFocused, newFocused) -> applySelectionStyle(newFocused));
        
        final String displayedIndex = new DecimalFormat("00").format(slot.getIndex() + 1);
        
        final GridPane firstRow = new GridPane();
        firstRow.setPadding(new Insets(3, 5, 3, 5));
        firstRow.setStyle("-fx-background-color: rgba(200, 200, 200, 0.5);");
        getChildren().add(firstRow);
        
        final GridPane secondRow = new GridPane();
        secondRow.setPadding(new Insets(3, 5, 3, 5));
        getChildren().add(secondRow);
        
        Text slotLabel = new Text();
        slotLabel.setStyle("-fx-font-style: italic; -fx-font-weight: bold");
        firstRow.add(slotLabel, 0, 0);
        
        if (slot.isErroneous()) {
            slotLabel.setText(displayedIndex + ". DEFECT");
            secondRow.add(new Label(" "), 0, 0);
            
        } else if (slot.isEmpty()) {
            slotLabel.setText(displayedIndex + ". EMPTY");
            secondRow.add(new Label(" "), 0, 0);
            
        } else {
            setCursor(Cursor.HAND);
            setOnMouseEntered(event -> applyMouseEnteredStyle());
            setOnMouseExited(event -> applyMouseExitedStyle());
            
            final CharacterStatistics characterStats = slot.getContent().getCharacterStatistics();
            
            slotLabel.setText(displayedIndex + ". " + characterStats.getName());
            
            final Text locationLabel = new Text(Messages.getMessage(characterStats.getLocation()));
            firstRow.add(locationLabel, 1, 0);
            
            final Text ngLabel = new Text("NG+" + characterStats.getClearCount());
            secondRow.add(ngLabel, 0, 0);
            
            final Text levelLabel = new Text("Level: " + characterStats.getLevel());
            secondRow.add(levelLabel, 1, 0);
            
            final Text playTimeLabel = new Text("Playtime: " + characterStats.getPlayTime());
            secondRow.add(playTimeLabel, 2, 0);
            
            ColumnConstraints columnConstraints = new ColumnConstraints(200);
            columnConstraints.setHalignment(HPos.RIGHT);
            firstRow.getColumnConstraints().addAll(new ColumnConstraints(150), columnConstraints);
            columnConstraints = new ColumnConstraints(200);
            columnConstraints.setHalignment(HPos.RIGHT);
            secondRow.getColumnConstraints().addAll(new ColumnConstraints(60), new ColumnConstraints(90), columnConstraints);
        }
        
    }
    
    /**
     * Sets the selected state of the panel. This will also apply the corresponding selection style.
     * 
     * @param selected If the panel is selected.
     */
    public void setSelected(final boolean selected) {
        this.selected = selected;
        applySelectionStyle();
    }
    
    /**
     * Style on mouse over.
     */
    private void applyMouseEnteredStyle() {
        setStyle(originalStyle + "-fx-background-color: #ccc");
    }
    
    /**
     * Style on mouse leave.
     */
    private void applyMouseExitedStyle() {
        applySelectionStyle();
    }
    
    /**
     * Applies the selection style. 
     * 
     * @see #applySelectionStyle(boolean)
     */
    private void applySelectionStyle() {
        applySelectionStyle(isFocused());
    }
    
    /**
     * Changes the style of the panel according to the current selection style and if
     * the panel is currently focused.
     * 
     * @param focused If the panel currently has the focus.
     */
    private void applySelectionStyle(final boolean focused) {
        if (selected) {
            if (focused) {
                setStyle(originalStyle + "-fx-background-color: -fx-selection-bar;"); //#ffc //#0096c9
            } else {
                setStyle(originalStyle + "-fx-background-color: -fx-selection-bar-non-focused;"); //#ffc
            }
        } else {
            setStyle(originalStyle);
        }
    }
    
}
