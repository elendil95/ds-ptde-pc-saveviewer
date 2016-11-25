package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import illgirni.ds.ptde.pc.saveviewer.ui.UIStarter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * The information dialogs that can be opened via the application's main menu.
 * 
 * @author illgirni
 *
 */
public final class MenuDialogs {
    
    private MenuDialogs() {
    }
    
    /**
     * Shows the "About" dialog with the version info.
     */
    public static void showAboutDialog() {
        Alert aboutDialog = BasicDialogs.createSimpleDialog("About", "DARK SOULS - PREPARE TO DIE - PC Save Viewer");
        
        final DialogPane dialogContent = aboutDialog.getDialogPane();
        
        int row = 0;
        final GridPane aboutGrid = new GridPane();
        aboutGrid.setHgap(0);
        aboutGrid.setVgap(1);
        aboutGrid.setPadding(new Insets(10));
        
        dialogContent.setContent(aboutGrid);
        
        final Text versionText = new Text("- version 1.0.0 / 2016-11-26 -");
        versionText.setStyle("-fx-font-weight: bold;");
        GridPane.setHalignment(versionText, HPos.CENTER);
        GridPane.setFillWidth(versionText, true);
        aboutGrid.add(versionText, 0, row++);
        
        final Text authorText = new Text("- by illgirni -");
        GridPane.setHalignment(authorText, HPos.CENTER);
        aboutGrid.add(authorText, 0, row++);
        
        final Separator separator1 = new Separator(Orientation.HORIZONTAL);
        separator1.setPadding(new Insets(10, 0, 10, 0));
        GridPane.setFillWidth(separator1, true);
        GridPane.setHgrow(separator1, Priority.ALWAYS);
        
        aboutGrid.add(separator1, 0, row++);
        
        final Text downloadText = new Text("- Download Current Version -");
        GridPane.setHalignment(downloadText, HPos.CENTER);
        GridPane.setFillWidth(downloadText, true);
        aboutGrid.add(downloadText, 0, row++);
        
        final String downloadUrl = "http://www.nexusmods.com/darksouls/mods/1181";
        final Hyperlink downloadLink = new Hyperlink(downloadUrl);
        downloadLink.setOnAction(event -> UIStarter.openUrl(downloadUrl));
        GridPane.setHalignment(downloadLink, HPos.CENTER);
        aboutGrid.add(downloadLink, 0, row++);
        
        aboutGrid.add(new Text(""), 0, row++);
        
        final Text sourceCodeText = new Text("- Source Code -");
        GridPane.setHalignment(sourceCodeText, HPos.CENTER);
        GridPane.setFillWidth(sourceCodeText, true);
        aboutGrid.add(sourceCodeText, 0, row++);
        
        final String sourceCodeUrl = "http://www.github.com/illgirni/ds-ptde-pc-saveviewer";
        final Hyperlink sourceCodeLink = new Hyperlink(sourceCodeUrl);
        sourceCodeLink.setOnAction(event -> UIStarter.openUrl(sourceCodeUrl));
        GridPane.setHalignment(sourceCodeLink, HPos.CENTER);
        aboutGrid.add(sourceCodeLink, 0, row++);
        
        final Separator separator2 = new Separator(Orientation.HORIZONTAL);
        separator2.setPadding(new Insets(10, 0, 10, 0));
        GridPane.setFillWidth(separator2, true);
        GridPane.setHgrow(separator2, Priority.ALWAYS);
        
        aboutGrid.add(separator2, 0, row++);
        
        final Text foundBugText = new Text("- Found a bug? Report here! -");
        GridPane.setHalignment(foundBugText, HPos.CENTER);
        GridPane.setFillWidth(foundBugText, true);
        aboutGrid.add(foundBugText, 0, row++);
        
        final String foundBugUrl = "http://www.github.com/illgirni/ds-ptde-pc-saveviewer/issues";
        final Hyperlink foundBugLink = new Hyperlink(foundBugUrl);
        foundBugLink.setOnAction(event -> UIStarter.openUrl(foundBugUrl));
        GridPane.setHalignment(foundBugLink, HPos.CENTER);
        aboutGrid.add(foundBugLink, 0, row++);
        
        aboutDialog.showAndWait();
        
    }
    
    /**
     * Shows the dialog with the infos of the files managed by the application.
     * 
     * @param saveFilePath The path to the save file.
     * @param workspacePath The path to the workspace directory.
     */
    public static void showFileInfoDialog(final String saveFilePath, final String workspacePath) {
        Alert fileInfoDialog = BasicDialogs.createSimpleDialog("File Info", "This is were you find the files of this application.");
        
        final DialogPane dialogContent = fileInfoDialog.getDialogPane();
        
        int row = 0;
        final GridPane fileInfoGrid = new GridPane();
        fileInfoGrid.setHgap(0);
        fileInfoGrid.setVgap(1);
        fileInfoGrid.setPadding(new Insets(10));
        dialogContent.setContent(fileInfoGrid);
        
        final Text saveFileLabel = new Text("- Loaded Save File -");
        final TextField saveFileField = createAutosizedField();
        saveFileField.setText(saveFilePath);
        saveFileField.setEditable(false);
        
        GridPane.setHgrow(saveFileLabel, Priority.NEVER);
        GridPane.setValignment(saveFileLabel, VPos.TOP);
        GridPane.setHalignment(saveFileLabel, HPos.CENTER);
        GridPane.setHgrow(saveFileField, Priority.ALWAYS);
        GridPane.setValignment(saveFileField, VPos.TOP);
        GridPane.setHalignment(saveFileField, HPos.CENTER);
        
        fileInfoGrid.add(saveFileLabel, 0, row++);
        fileInfoGrid.add(saveFileField, 0, row++);
        
        fileInfoGrid.add(new Text(""), 0, row++);
        
        final Text workspacePathLabel = new Text("- Exported Characters and Settings -");
        final TextField workspacePathField = createAutosizedField();
        workspacePathField.setText(workspacePath);
        workspacePathField.setEditable(false);
        
        GridPane.setHgrow(workspacePathLabel, Priority.NEVER);
        GridPane.setValignment(workspacePathLabel, VPos.TOP);
        GridPane.setHalignment(workspacePathLabel, HPos.CENTER);
        GridPane.setValignment(workspacePathField, VPos.TOP);
        GridPane.setHalignment(workspacePathField, HPos.CENTER);
        
        fileInfoGrid.add(workspacePathLabel, 0, row++);
        fileInfoGrid.add(workspacePathField, 0, row++);
        
        fileInfoDialog.showAndWait();
        
    }
    
    /**
     * Shows the credits dialog.
     */
    public static void showCreditsDialog() {
        Alert creditsDialog = BasicDialogs.createSimpleDialog("Credits", 
                                                              "Shout-outs to these people! Creating this tool would not have "
                                                              + "been possible without their research.");
        
        final DialogPane dialogContent = creditsDialog.getDialogPane();
        dialogContent.setMaxWidth(600);
        
        final int wordWrapWidth = 500;
        
        final GridPane shoutOutsBox = new GridPane();
        shoutOutsBox.setVgap(10);
        shoutOutsBox.setPrefWidth(600);
        shoutOutsBox.setMaxWidth(600);
        dialogContent.setContent(shoutOutsBox);
        
        int row = 0;
        final GridPane shoutOutGrid = new GridPane();
        shoutOutGrid.setHgap(3);
        shoutOutGrid.setVgap(1);
        shoutOutsBox.add(shoutOutGrid, 0, 1);
        
        //http://www.360haven.com/forums/threads/16271-Remake-PC-Xbox360-Dark-Souls-Save-editor-V3-0-7-0
        final Text jappi88Label = new Text("Jappi88:");
        jappi88Label.setStyle("-fx-font-style: italic");
        final Text jappi88Text = new Text("His save editor really helped to understand the save file structure!");
        jappi88Text.setWrappingWidth(wordWrapWidth);
        GridPane.setHgrow(jappi88Label, Priority.NEVER);
        GridPane.setValignment(jappi88Label, VPos.TOP);
        GridPane.setHgrow(jappi88Text, Priority.ALWAYS);
        GridPane.setValignment(jappi88Text, VPos.TOP);
        shoutOutGrid.add(jappi88Label, 0, row);
        shoutOutGrid.add(jappi88Text, 1, row++);
        
        Separator separator = new Separator(Orientation.HORIZONTAL);
        GridPane.setHgrow(separator, Priority.ALWAYS);
        GridPane.setColumnSpan(separator, 2);
        shoutOutGrid.add(separator, 0, row++);
        
        final Text wulf2kLabel = new Text("Wulf2k:");
        wulf2kLabel.setStyle("-fx-font-style: italic");
        
        final Text wulf2kText = new Text("The DSCM source code helped to find the boss information and some hidden stats in the saves!");
        wulf2kText.setWrappingWidth(wordWrapWidth);
        
        final Hyperlink wulf2kLink1 = new Hyperlink("/u/wulf2k");
        wulf2kLink1.setPadding(new Insets(0));
        wulf2kLink1.setOnAction(event -> UIStarter.openUrl("https://www.reddit.com/user/Wulf2k"));
        
        final Hyperlink wulf2kLink2 = new Hyperlink("http://wulf2k.ca");
        wulf2kLink2.setPadding(new Insets(0));
        wulf2kLink2.setOnAction(event -> UIStarter.openUrl("http://wulf2k.ca"));
        
        final Hyperlink wulf2kLink3 = new Hyperlink("https://github.com/Wulf2k");
        wulf2kLink3.setPadding(new Insets(0));
        wulf2kLink3.setOnAction(event -> UIStarter.openUrl("https://github.com/Wulf2k"));
        
        GridPane.setHgrow(wulf2kLabel, Priority.NEVER);
        GridPane.setValignment(wulf2kLabel, VPos.TOP);
        GridPane.setHgrow(wulf2kText, Priority.ALWAYS);
        GridPane.setValignment(wulf2kText, VPos.TOP);
        GridPane.setHgrow(wulf2kLink1, Priority.NEVER);
        GridPane.setValignment(wulf2kLink1, VPos.TOP);
        GridPane.setHgrow(wulf2kLink2, Priority.NEVER);
        GridPane.setValignment(wulf2kLink2, VPos.TOP);
        GridPane.setHgrow(wulf2kLink3, Priority.NEVER);
        GridPane.setValignment(wulf2kLink3, VPos.TOP);
        
        shoutOutGrid.add(wulf2kLabel, 0, row);
        shoutOutGrid.add(wulf2kText, 1, row++);
        shoutOutGrid.add(wulf2kLink1, 1, row++);
        shoutOutGrid.add(wulf2kLink2, 1, row++);
        shoutOutGrid.add(wulf2kLink3, 1, row++);
        
        separator = new Separator(Orientation.HORIZONTAL);
        GridPane.setHgrow(separator, Priority.ALWAYS);
        GridPane.setColumnSpan(separator, 2);
        shoutOutGrid.add(separator, 0, row++);
        
        final Text illusoryLabel = new Text("illusorywall &\neur0pa");
        illusoryLabel.setStyle("-fx-font-style: italic");
        
        final Text illusoryText = new Text("For their item lists and and other findings - like the hidden death counter.");
        illusoryText.setWrappingWidth(wordWrapWidth);
        
        final Hyperlink illusoryLink1 = new Hyperlink("http://illusorywall.tumblr.com");
        illusoryLink1.setPadding(new Insets(0));
        illusoryLink1.setOnAction(event -> UIStarter.openUrl("http://illusorywall.tumblr.com"));
        
        final Hyperlink illusoryLink2 = new Hyperlink("/u/eur0pa");
        illusoryLink2.setPadding(new Insets(0));
        illusoryLink2.setOnAction(event -> UIStarter.openUrl("https://www.reddit.com/user/eur0pa"));
        
        GridPane.setHgrow(illusoryLabel, Priority.NEVER);
        GridPane.setValignment(illusoryLabel, VPos.TOP);
        GridPane.setRowSpan(illusoryLabel, 2);
        GridPane.setHgrow(illusoryText, Priority.ALWAYS);
        GridPane.setValignment(illusoryText, VPos.TOP);
        GridPane.setHgrow(illusoryLink1, Priority.NEVER);
        GridPane.setValignment(illusoryLink1, VPos.TOP);
        GridPane.setHgrow(illusoryLink2, Priority.NEVER);
        GridPane.setValignment(illusoryLink2, VPos.TOP);
        
        shoutOutGrid.add(illusoryLabel, 0, row);
        shoutOutGrid.add(illusoryText, 1, row++);
        shoutOutGrid.add(illusoryLink1, 1, row++);
        shoutOutGrid.add(illusoryLink2, 1, row++);
        
        separator = new Separator(Orientation.HORIZONTAL);
        GridPane.setHgrow(separator, Priority.ALWAYS);
        GridPane.setColumnSpan(separator, 2);
        shoutOutGrid.add(separator, 0, row++);
        
        final Text cheatEngineLabel = new Text("technojacker &\nco.");
        cheatEngineLabel.setStyle("-fx-font-style: italic");
        
        final Text cheatEngineText = new Text("All the dudes and dudettes creating the cheat engine tables. "
                                                + "These tables really helped finding additional properties.");
        cheatEngineText.setWrappingWidth(wordWrapWidth);
        GridPane.setHgrow(cheatEngineLabel, Priority.NEVER);
        GridPane.setValignment(cheatEngineLabel, VPos.TOP);
        GridPane.setHgrow(cheatEngineText, Priority.ALWAYS);
        GridPane.setValignment(cheatEngineText, VPos.TOP);
        shoutOutGrid.add(cheatEngineLabel, 0, row);
        shoutOutGrid.add(cheatEngineText, 1, row++);
        
        //Burton Radons:
        //github: https://github.com/Burton-Radons
        //spread sheet: https://docs.google.com/spreadsheets/d/1KukblWL61We64-gNIyaAShga9h8RTXYmyFs98eQhY4E/edit#gid=1621557847
        
        creditsDialog.showAndWait();
    }
    
    /**
     * Creates a text field that adjusts in size according to the text in the field.
     */
    private static TextField createAutosizedField() {
        final TextField autosizedField = new TextField();
        autosizedField.setMinWidth(TextField.USE_PREF_SIZE);
        autosizedField.setMaxWidth(TextField.USE_PREF_SIZE);
        autosizedField.textProperty().addListener((ov, prevText, currText) -> {
            Text text = new Text(currText);
            text.setFont(autosizedField.getFont()); // Set the same font, so the size is the same
            double width = text.getLayoutBounds().getWidth() // This big is the Text in the TextField
                                                    + autosizedField.getPadding().getLeft() 
                                                    + autosizedField.getPadding().getRight() // Add the padding of the TextField
                                                    + 20d; // Add some spacing
            autosizedField.setPrefWidth(width); // Set the width
            autosizedField.positionCaret(autosizedField.getCaretPosition()); // If you remove this line, it flashes a little bit
            
        });
        
        return autosizedField;
        
    }
    
}
