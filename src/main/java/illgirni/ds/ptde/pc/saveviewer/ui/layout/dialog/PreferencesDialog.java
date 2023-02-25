package illgirni.ds.ptde.pc.saveviewer.ui.layout.dialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceSetting;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceValue;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * The menu dialog, which allows to change the user preferences. The result of the dialog is the
 * list of new preference values.
 * 
 * @author illgirni
 *
 */
public class PreferencesDialog extends Dialog<List<PreferenceValue>> {

  /**
   * The save button type.
   */
  private static final ButtonType SAVE_BUTTON_TYPE = new ButtonType("Save", ButtonData.OK_DONE);

  /**
   * The new preference values.
   */
  private final List<PreferenceValue> preferences = new ArrayList<>();

  /**
   * Fills the dialog. Essentially adds a row per available preference value to the dialog.
   */
  public PreferencesDialog() {
    BasicDialogs.applySaveViewerStyle(this);
    setTitle("Preferences");
    setHeaderText("Define some GUI related settings.");

    // Input fields
    final GridPane dialogContent = new GridPane();
    dialogContent.setHgap(10);
    // dialogContent.setVgap(10);
    dialogContent.setPadding(new Insets(10, 10, 10, 10));
    getDialogPane().setContent(dialogContent);

    int row = 0;
    int columns = 1;

    final Text restartLabel =
        new Text("Restart the application after saving to make changes take effect everywhere!");
    GridPane.setHalignment(restartLabel, HPos.CENTER);
    dialogContent.addRow(row++, restartLabel);

    final Separator separator1 = new Separator();
    separator1.setPadding(new Insets(10, 0, 10, 0));
    GridPane.setHgrow(separator1, Priority.ALWAYS);
    GridPane.setColumnSpan(separator1, 5);
    GridPane.setValignment(separator1, VPos.CENTER);
    dialogContent.addRow(row++, separator1);

    final PreferenceValue characterDetailsValue =
        new PreferenceValue(PreferenceSetting.CHARACTER_DETAILS);
    preferences.add(characterDetailsValue);
    List<Node> rowNodes = createCharacterDetailsPreferenceRow(characterDetailsValue);
    dialogContent.addRow(row++, rowNodes.toArray(new Node[0]));
    columns = Integer.max(columns, rowNodes.size());

    final Separator separator2 = new Separator();
    separator2.setPadding(new Insets(10, 0, 10, 0));
    GridPane.setHgrow(separator2, Priority.ALWAYS);
    GridPane.setColumnSpan(separator2, 5);
    GridPane.setValignment(separator2, VPos.CENTER);
    dialogContent.addRow(row++, separator2);

    final PreferenceValue windowMaximizedValue =
        new PreferenceValue(PreferenceSetting.WINDOW_MAXIMIZED);
    preferences.add(windowMaximizedValue);
    rowNodes = createFullscreenPreferenceRow(windowMaximizedValue);
    dialogContent.addRow(row++, rowNodes.toArray(new Node[0]));
    columns = Integer.max(columns, rowNodes.size());

    final PreferenceValue windowWidthValue = new PreferenceValue(PreferenceSetting.WINDOW_WIDTH);
    preferences.add(windowWidthValue);
    rowNodes = createPreferenceValueRow(windowWidthValue, "Window Width");
    dialogContent.addRow(row++, rowNodes.toArray(new Node[0]));
    columns = Integer.max(columns, rowNodes.size());

    final PreferenceValue windowHeightValue = new PreferenceValue(PreferenceSetting.WINDOW_HEIGHT);
    preferences.add(windowHeightValue);
    rowNodes = createPreferenceValueRow(windowHeightValue, "Window Height");
    dialogContent.addRow(row++, rowNodes.toArray(new Node[0]));
    columns = Integer.max(columns, rowNodes.size());

    final PreferenceValue windowDividerValue =
        new PreferenceValue(PreferenceSetting.WINDOW_DIVIDER);
    preferences.add(windowDividerValue);
    rowNodes = createPreferenceValueRow(windowDividerValue, "Window Divider");
    dialogContent.addRow(row++, rowNodes.toArray(new Node[0]));
    columns = Integer.max(columns, rowNodes.size());

    final PreferenceValue fontSizeValue = new PreferenceValue(PreferenceSetting.FONT_SIZE);
    preferences.add(fontSizeValue);
    rowNodes = createPreferenceValueRow(fontSizeValue, "Font Size");
    dialogContent.addRow(row++, rowNodes.toArray(new Node[0]));
    columns = Integer.max(columns, rowNodes.size());

    GridPane.setColumnSpan(restartLabel, columns);

    // Buttons & result
    getDialogPane().getButtonTypes().addAll(SAVE_BUTTON_TYPE, ButtonType.CANCEL);
    setResultConverter(new ResultConverter());

  }

  /**
   * Creates the nodes for the character details layout preference. This are a label, a "tabs" radio
   * button, a "split" radio button, and a "list radio button.
   * 
   * @param preferenceValue The layout preference value.
   * @return The nodes.
   */
  private List<Node> createCharacterDetailsPreferenceRow(final PreferenceValue preferenceValue) {
    final ToggleGroup defaultCustomToggles = new ToggleGroup();

    final Text preferenceLabel = new Text("Character Details");
    GridPane.setHgrow(preferenceLabel, Priority.NEVER);
    GridPane.setValignment(preferenceLabel, VPos.CENTER);

    final ToggleButton tabsToggle = new RadioButton("Default (Tabs)");
    tabsToggle.setSelected(
        preferenceValue.getUseDefault().get() || preferenceValue.getValue().get() == 1);
    tabsToggle.selectedProperty().addListener((toggle, oldSel, newSel) -> {
      if (newSel) {
        preferenceValue.getValue().set(1);
      }
    });
    preferenceValue.getUseDefault().bind(tabsToggle.selectedProperty());

    final ToggleButton splitToggle = new RadioButton("Split");
    splitToggle.setSelected(
        !preferenceValue.getUseDefault().get() && preferenceValue.getValue().get() == 2);
    splitToggle.selectedProperty().addListener((toggle, oldSel, newSel) -> {
      if (newSel) {
        preferenceValue.getValue().set(2);
      }
    });

    final ToggleButton listToggle = new RadioButton("List");
    listToggle.setSelected(
        !preferenceValue.getUseDefault().get() && preferenceValue.getValue().get() == 3);
    listToggle.selectedProperty().addListener((toggle, oldSel, newSel) -> {
      if (newSel) {
        preferenceValue.getValue().set(3);
      }
    });

    final HBox toggleBox = new HBox(tabsToggle, splitToggle, listToggle);
    toggleBox.setSpacing(20);
    GridPane.setHgrow(toggleBox, Priority.NEVER);
    GridPane.setValignment(toggleBox, VPos.CENTER);
    GridPane.setColumnSpan(toggleBox, 4);

    defaultCustomToggles.getToggles().addAll(tabsToggle, splitToggle, listToggle);

    return Arrays.asList(preferenceLabel, toggleBox);
  }

  /**
   * Create the nodes for the full screen preference value. This are a label, a "yes" radio button
   * and a "no" radio button.
   * 
   * @param preferenceValue The full screen preference value.
   * @return The nodes.
   */
  private List<Node> createFullscreenPreferenceRow(final PreferenceValue preferenceValue) {
    final ToggleGroup defaultCustomToggles = new ToggleGroup();

    final Text preferenceLabel = new Text("Maximized");
    GridPane.setHgrow(preferenceLabel, Priority.NEVER);
    GridPane.setValignment(preferenceLabel, VPos.CENTER);

    final ToggleButton noToggle = new RadioButton("Default (No)");
    noToggle.setSelected(
        preferenceValue.getUseDefault().get() || preferenceValue.getValue().get() != 1);
    noToggle.selectedProperty().addListener((toggle, oldSel, newSel) -> {
      if (newSel) {
        preferenceValue.getValue().set(0);
      }
    });
    preferenceValue.getUseDefault().bind(noToggle.selectedProperty());

    final ToggleButton yesToggle = new RadioButton("Yes");
    yesToggle.setSelected(
        !preferenceValue.getUseDefault().get() && preferenceValue.getValue().get() == 1);
    yesToggle.selectedProperty().addListener((toggle, oldSel, newSel) -> {
      if (newSel) {
        preferenceValue.getValue().set(1);
      }
    });

    defaultCustomToggles.getToggles().addAll(noToggle, yesToggle);

    return Arrays.asList(preferenceLabel, noToggle, yesToggle);
  }

  /**
   * Creates the nodes required to change the values of the preference. This are:
   * <ul>
   * <li>A label</li>
   * <li>A radio button indicating to use the default value.</li>
   * <li>A radio button indicating to use a custom value.</li>
   * <li>A slider with which to define the custom value.</li>
   * <li>A read-only text field showing the slider value.</li>
   * </ul>
   * 
   * @param preferenceValue The preference value.
   * @param labelText The text for the label.
   * @return The nodes for the preference value.
   */
  private List<Node> createPreferenceValueRow(final PreferenceValue preferenceValue,
      final String labelText) {
    final ToggleGroup defaultCustomToggles = new ToggleGroup();

    final Text preferenceLabel = new Text(labelText);
    GridPane.setHgrow(preferenceLabel, Priority.NEVER);
    GridPane.setValignment(preferenceLabel, VPos.CENTER);

    final ToggleButton defaultToggle =
        new RadioButton("Default (" + preferenceValue.getPreference().getDefaultValue() + ")");
    defaultToggle.setSelected(preferenceValue.getUseDefault().get());
    preferenceValue.getUseDefault().bind(defaultToggle.selectedProperty());
    GridPane.setHgrow(defaultToggle, Priority.NEVER);
    GridPane.setValignment(defaultToggle, VPos.CENTER);

    final ToggleButton customToggle = new RadioButton();
    customToggle.setSelected(!preferenceValue.getUseDefault().get());
    GridPane.setHgrow(customToggle, Priority.NEVER);
    GridPane.setValignment(customToggle, VPos.CENTER);

    defaultCustomToggles.getToggles().addAll(defaultToggle, customToggle);

    final Slider customValueSlider = new Slider();
    customValueSlider.setMin(preferenceValue.getPreference().getMinValue());
    customValueSlider.setMax(preferenceValue.getPreference().getMaxValue());
    customValueSlider.setValue(preferenceValue.getValue().get());
    customValueSlider.setShowTickLabels(true);
    customValueSlider.setShowTickMarks(true);
    customValueSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      preferenceValue.getValue().set(newVal.intValue());
      customValueSlider.setValue(preferenceValue.getValue().get());
    });
    customValueSlider.setPrefWidth(300);
    customValueSlider.disableProperty().bind(customToggle.selectedProperty().not());
    GridPane.setHgrow(customValueSlider, Priority.ALWAYS);
    GridPane.setValignment(customValueSlider, VPos.CENTER);

    final DecimalFormat valueFormat = new DecimalFormat("#,##0");
    final TextField customValueOutput =
        new TextField(valueFormat.format(preferenceValue.getValue().get()));
    customValueOutput.setDisable(true);
    customValueOutput.setAlignment(Pos.CENTER_RIGHT);
    preferenceValue.getValue().addListener((observable, oldValue, newValue) -> customValueOutput
        .setText(valueFormat.format(newValue)));
    GridPane.setMargin(customValueOutput, new Insets(10));
    GridPane.setHgrow(customValueOutput, Priority.NEVER);
    GridPane.setValignment(customValueOutput, VPos.CENTER);

    if (preferenceValue.getPreference() == PreferenceSetting.FONT_SIZE) {
      Platform.runLater(() -> {
        customValueSlider.setValue(preferenceLabel.getFont().getSize());
      });

      defaultToggle.setText("Default");

      customValueSlider.setMajorTickUnit(2);
      customValueSlider.setMinorTickCount(1);
      customValueSlider.setBlockIncrement(1);

    } else {
      customValueSlider
          .setMajorTickUnit((customValueSlider.getMax() - customValueSlider.getMin()) / 10);
      customValueSlider.setMinorTickCount(3);
      customValueSlider.setBlockIncrement(10);

    }

    return Arrays.asList(preferenceLabel, defaultToggle, customToggle, customValueSlider,
        customValueOutput);
  }

  /**
   * Creates a copy of the preference values shown i the dialog at the timethe save button is
   * clicked.
   */
  // dialog content not modifyable from outside
  private class ResultConverter implements Callback<ButtonType, List<PreferenceValue>> {

    @Override
    public List<PreferenceValue> call(ButtonType button) {
      if (SAVE_BUTTON_TYPE.equals(button)) {
        List<PreferenceValue> resultList = new ArrayList<>();

        for (final PreferenceValue sourceValue : preferences) {
          final PreferenceValue targetValue = new PreferenceValue(sourceValue.getPreference());
          targetValue.getUseDefault().set(sourceValue.getUseDefault().get());
          targetValue.getValue().set(sourceValue.getValue().get());

          resultList.add(targetValue);
        }

        return resultList;

      } else {
        return null;
      }
    }
  }
}
