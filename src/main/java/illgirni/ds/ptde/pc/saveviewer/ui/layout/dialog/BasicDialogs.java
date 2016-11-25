package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import illgirni.ds.ptde.pc.saveviewer.ui.UIStarter;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageKey;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageRegistry;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.LayoutUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Provides basic dialogs. All dialogs created through this will have the custom application
 * style applied.
 * 
 * @author illgirni
 *
 */
public final class BasicDialogs {
    
    private BasicDialogs() {
    }
    
    /**
     * A dialog requesting confirmation of an action.
     * 
     * @param title The dialog title.
     * @param text The dialog text.
     * @return {@code true} when confirmed; {@code false} otherwise.
     */
    public static boolean showConfirmDialog(final String title, final String text) {
        checkUIStarted();
        
        final Alert errorDialog =  createSimpleDialog(title, text, AlertType.CONFIRMATION);
        
        
        final Optional<ButtonType> confirmed = errorDialog.showAndWait();
        
        return confirmed.isPresent() && confirmed.get() == ButtonType.OK;
    }
    
    /**
     * Shows the save file selection dialog.
     * 
     * @param saveFilePath The preselected save file.
     * @param saveFileName The name that the save file must have.
     * @return The newly selected save file as full path to the file.
     */
    //TODO make this dialog an own class
    public static String showSaveFilePathDialog(String saveFilePath, String saveFileName) {
        checkUIStarted();
        
        final Dialog<String> dialog = createDialog("Select DS1-PTDE Save File", "Select Dark Souls - Prepare to Die - Save File");
        dialog.initStyle(StageStyle.UTILITY);
        
        // Dialog buttons
        final ButtonType okButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        
        final Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(saveFilePath == null || saveFilePath.trim().length() == 0);
        
        //Dialog Content
        final Label saveFileLabel = new Label("Save File *");
        
        final TextField saveFileField = new TextField();
        saveFileField.setPromptText("Save File Location");
        saveFileField.setText(saveFilePath);
        saveFileField.setMinWidth(300);
        saveFileField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(saveFileField, Priority.ALWAYS);
        
        final FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.getExtensionFilters().add(new ExtensionFilter("DS1 PTDE Save File", saveFileName));
        
        final Button selectSaveFileButton = new Button("Select...");
        selectSaveFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final File fileSelection = saveFileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
                
                if (fileSelection != null) {
                    saveFileField.setText(fileSelection.getAbsolutePath());
                }
            }
        });
        
        //Assemble dialog content
        final GridPane dialogContent = new GridPane();
        dialogContent.setHgap(10);
        dialogContent.setVgap(10);
        dialogContent.setPadding(new Insets(10, 10, 10, 10));
        
        dialogContent.add(saveFileLabel, 0, 0);
        dialogContent.add(saveFileField, 1, 0);
        dialogContent.add(selectSaveFileButton, 2, 0);
        
        dialog.getDialogPane().setContent(dialogContent);
        
        // Request focus on the save file field by default.
        Platform.runLater(() -> saveFileField.requestFocus());
        
        //OK button is only enabled when value entered in save file field.
        saveFileField.textProperty().addListener((observable, oldValue, newValue) -> okButton.setDisable(newValue.trim().isEmpty()));
        
        dialog.setResultConverter((button) -> okButtonType.equals(button) ? saveFileField.getText() : null);
        
        final Optional<String> selectedSaveFilePath = dialog.showAndWait();
        
        return selectedSaveFilePath.orElse(null);
        
    }
    
    /**
     * Shows an error dialog.
     * 
     * @param errorMessage The error message in the dialog.
     */
    public static void showErrorDialog(final String errorMessage) {
        showErrorDialog(errorMessage, null);
    }
    
    /**
     * Shows an error dialog.
     * 
     * @param errorMessage The error message.
     * @param errorDetails Details about the error.
     */
    public static void showErrorDialog(final String errorMessage, final String errorDetails) {
        showErrorDialog(errorMessage, errorDetails, null);
    }
    
    /**
     * Shows an error dialog. When an exception is provided also shows the stack trace of the exception.
     * 
     * @param errorMessage The error message.
     * @param errorDetails Details about the error.
     * @param exception The exception because of which the dialog is shown.
     */
    public static void showErrorDialog(final String errorMessage, final String errorDetails, final Throwable exception) {
        checkUIStarted();
        
        final Alert errorDialog = createSimpleDialog("Uuups...", errorMessage, AlertType.ERROR);
        errorDialog.setTitle("Uuups...");
        errorDialog.setHeaderText(errorMessage);
        errorDialog.setContentText(errorDetails);
        
        final GridPane detailsContent = new GridPane();
        detailsContent.setMaxWidth(Double.MAX_VALUE);
        
        if (exception != null) {
            final Label exceptionLabel = new Label("This information would be useful for debugging:");
            
            final StringWriter stackTrace = new StringWriter();
            final PrintWriter stackTracePrinter = new PrintWriter(stackTrace);
            exception.printStackTrace(stackTracePrinter);
            final String exceptionText = stackTrace.toString();
            
            final TextArea exceptionOutput = new TextArea(exceptionText);
            exceptionOutput.setEditable(false);
            exceptionOutput.setWrapText(true);
            
            exceptionOutput.setMaxWidth(Double.MAX_VALUE);
            exceptionOutput.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(exceptionOutput, Priority.ALWAYS);
            GridPane.setHgrow(exceptionOutput, Priority.ALWAYS);
            
            detailsContent.add(exceptionLabel, 0, 0);
            detailsContent.add(exceptionOutput, 0, 1);
            
        }
        
        errorDialog.getDialogPane().setExpandableContent(detailsContent);
        errorDialog.getDialogPane().setExpanded(true);
        errorDialog.showAndWait();
        
    }
    
    /**
     * Shows a warn dialog.
     * 
     * @param warnMessage The warn message.
     * @param warnDetails Warning details.
     */
    public static void showWarnDialog(final String warnMessage, final String warnDetails) {
        checkUIStarted();
        
        final Alert warnDialog = createSimpleDialog("Uuups...", warnMessage, AlertType.WARNING);
        warnDialog.setTitle("Uuups...");
        warnDialog.setHeaderText(warnMessage);
        //warnDialog.setContentText(warnDetails);
        
        final GridPane detailsContent = new GridPane();
        detailsContent.setMaxWidth(Double.MAX_VALUE);
        final Label warnText = new Label(warnDetails);
        detailsContent.add(warnText, 0, 0);
        
        warnDialog.getDialogPane().setExpandableContent(detailsContent);
        warnDialog.getDialogPane().setExpanded(true);
        
        warnDialog.showAndWait();
        
    }
    
    /**
     * Creates a new generic dialog with the custom styles.
     * 
     * @param title The dialog title.
     * @param headerText The header text.
     * @return The dialog.
     */
    public static <T> Dialog<T> createDialog(final String title, final String headerText) {
        final Dialog<T> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        applyDialogIcon(dialog);
        applySaveViewerStylesheet(dialog);
        
        final DialogPane dialogContent = dialog.getDialogPane();
        dialogContent.setStyle("-fx-background-color: #fff");
        
        return dialog;
    }
    
    /**
     * A simple alert dialog without buttons.
     * 
     * @param title Dialog title.
     * @param headerText Dialog text. 
     * @return The alert dialog.
     */
    public static Alert createSimpleDialog(final String title, final String headerText) {
        return createSimpleDialog(title, headerText, null, false);
    }
    
    /**
     * A simple alert dialog without buttons.
     * 
     * @param title Dialog title.
     * @param headerText Dialog text.
     * @param type The alert type. 
     * @return The alert dialog.
     */
    public static Alert createSimpleDialog(final String title, final String headerText, final AlertType type) {
        return createSimpleDialog(title, headerText, type, false);
    }
    
    /**
     * A simple alert dialog.
     * 
     * @param title Dialog title.
     * @param headerText Dialog text.
     * @param showOkButton if the dialog should have an "OK" button.
     * @return The alert dialog.
     */
    public static Alert createSimpleDialog(final String title, final String headerText, final boolean showOkButton) {
        return createSimpleDialog(title, headerText, null, showOkButton);
    }
    
    /**
     * A simple alert dialog.
     * 
     * @param title Dialog title.
     * @param headerText Dialog text.
     * @param type The alert type. 
     * @param showOkButton if the dialog should have an "OK" button.
     * @return The alert dialog.
     */
    public static Alert createSimpleDialog(final String title, final String headerText, final AlertType type, final boolean showOkButton) {
        final Alert dialog;
        
        if (type == null) {
            dialog = new Alert(AlertType.NONE);
        } else {
            dialog = new Alert(type);
        }
        
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        applyDialogIcon(dialog);
        applySaveViewerStylesheet(dialog);
        
        final DialogPane dialogContent = dialog.getDialogPane();
        dialogContent.setStyle("-fx-background-color: #fff");
        
        if (type == null) {
            dialogContent.getButtonTypes().add(ButtonType.OK);
            
            if (!showOkButton) {
                dialogContent.lookupButton(ButtonType.OK).setVisible(false);
            }
        }
        
        return dialog;
    }
    
    /**
     * Applies the custom styles to the dialog.
     * 
     * @param dialog The dialog.
     */
    public static <T> void applySaveViewerStyle(final Dialog<T> dialog) {
        applySaveViewerStylesheet(dialog);
        applyDialogIcon(dialog);
    }
    
    /**
     * Applies the custom style sheets to the dialog.
     * 
     * @param dialog The dialog.
     */
    private static <T> void applySaveViewerStylesheet(final Dialog<T> dialog) {
        dialog.getDialogPane().getScene().getStylesheets().addAll(LayoutUtils.getStylesheets());
    }
    
    /**
     * Applies the application icon to the dialog.
     * 
     * @param dialog The dialog.
     */
    private static <T> void applyDialogIcon(final Dialog<T> dialog) {
        final Window window = dialog.getDialogPane().getScene().getWindow();
        
        if (window instanceof Stage) {
            final Stage stage = (Stage) window;
            stage.getIcons().add(ImageRegistry.getImage(ImageKey.APPLICATION));
        } else {
            System.err.println("Cannot set dialog icon. Not a stage: " + window); //FIXME should be logging.
        }
    }
    
    /**
     * Enforces the UI resources to be set-up.
     */
    private static void checkUIStarted() {
        if (!UIStarter.isStarted()) {
            throw new IllegalStateException("UI has not been started.");
        }
    }

}
