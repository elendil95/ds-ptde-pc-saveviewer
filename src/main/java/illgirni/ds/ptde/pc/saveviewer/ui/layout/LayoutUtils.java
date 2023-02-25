package illgirni.ds.ptde.pc.saveviewer.ui.layout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import illgirni.ds.ptde.pc.saveviewer.ui.UIStarter;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageKey;
import illgirni.ds.ptde.pc.saveviewer.ui.images.ImageRegistry;
import illgirni.ds.ptde.pc.saveviewer.ui.preferences.PreferenceSetting;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Utility to access the user preferences, open new windows, and get the application's style sheets.
 * 
 * @author illgirni
 *
 */
public class LayoutUtils {

  /**
   * The user preferences-
   */
  private static Properties preferences;

  /**
   * Expression to look for the font size in the font size CSS file.
   */
  private static final String FONT_SIZE_SEARCH_EXPRESSION = "-fx-font-size:.*?(\\d+)[^\\d]*";

  /**
   * The content of the font size CSS file. The actual font size can be set in this with
   * MessageFormat.
   */
  public static final String FONT_SIZE_FILE_CONTENT_FORMAT =
      ".root'{'-fx-font-size:{0,number,integer};'}'";

  private LayoutUtils() {}

  /**
   * The application's style sheets. Also includes the user preference font size style sheet.
   */
  public static List<String> getStylesheets() {
    final List<String> stylesheets = new ArrayList<>();

    final File settingsDirectory = getSettingsDirectory();

    if (settingsDirectory != null) {
      final File fontSheet = getFontSizeFile();

      if (fontSheet.isFile()) {
        stylesheets.add(fontSheet.toURI().toString());
      }
    }

    stylesheets.add(LayoutUtils.class.getResource("saveviewer.css").toExternalForm());

    return stylesheets;
  }

  /**
   * Creates the root for a new window.
   */
  public static Stage getNewWindowRoot() {
    if (!UIStarter.isStarted()) {
      throw new RuntimeException("UI has not been started.");
    }

    final Stage windowRoot = new Stage(StageStyle.DECORATED);
    windowRoot.getIcons().add(ImageRegistry.getImage(ImageKey.APPLICATION));

    return windowRoot;
  }

  /**
   * The user preferences as properties. Changes in the returned properties Object do not reflect on
   * the current settings.
   */
  public static Properties getPreferences() {
    loadPreferences();

    return new Properties(preferences);
  }

  /**
   * Triggers reload of the user preferences.
   */
  public static void reloadPreferences() {
    preferences = null;
  }

  /**
   * Gets the value for a user preference.
   * 
   * @param setting The preference.
   * @return The value to use.
   */
  public static int getPreferenceValue(PreferenceSetting setting) {
    loadPreferences();

    final String preferenceValue = preferences.getProperty(setting.getPreferencesKey());

    if (preferenceValue != null) {
      return Integer.parseInt(preferenceValue);
    } else {
      return setting.getDefaultValue();
    }
  }

  /**
   * The file with the user (window) preferences.
   */
  public static File getPreferencesFile() {
    final File settingsDirectory = getSettingsDirectory();

    if (settingsDirectory != null) {
      return new File(settingsDirectory, "preferences.properties");
    } else {
      return null;
    }
  }

  /**
   * The user preference font size CSS file.
   */
  public static File getFontSizeFile() {
    final File settingsDirectory = getSettingsDirectory();

    if (settingsDirectory != null) {
      return new File(settingsDirectory, "font-size.css");
    } else {
      return null;
    }

  }

  /**
   * Loads the user defined preferences. Uses the main preference file
   * ({@link #getPreferencesFile()}) and font size file ({@link #getFontSizeFile()}) as sources.
   */
  private static void loadPreferences() {
    if (preferences == null) {
      preferences = new Properties(); // load only once

      final File preferencesFile = getPreferencesFile();

      if (preferencesFile != null && preferencesFile.isFile()) {
        FileInputStream preferencesStream = null;

        try {
          preferencesStream = new FileInputStream(preferencesFile);
          preferences.load(preferencesStream);
          // font size is special. never allow it in preference file
          preferences.remove(PreferenceSetting.FONT_SIZE.getPreferencesKey());

        } catch (IOException e) {
          System.err.println("Could not load file: " + preferencesFile);

        } finally {
          if (preferencesStream != null) {
            try {
              preferencesStream.close();
            } catch (IOException e) {
              // close quietly
            }
          }
        }
      }

      // font size is not stored in main preferences file.
      final File fontSizeFile = getFontSizeFile();

      if (fontSizeFile != null && fontSizeFile.isFile()) {
        try {
          final String fontSizeFileContent = new String(Files.readAllBytes(fontSizeFile.toPath()));
          final Pattern fontSizeExpression = Pattern.compile(FONT_SIZE_SEARCH_EXPRESSION);
          final Matcher fontSizeMatcher = fontSizeExpression.matcher(fontSizeFileContent);

          if (fontSizeMatcher.find()) {
            preferences.put(PreferenceSetting.FONT_SIZE.getPreferencesKey(),
                fontSizeMatcher.group(1));
          }

        } catch (IOException e) {
          System.err.println("Could not read font size file: " + fontSizeFile);
        }

      }

    }

  }

  /**
   * The directory containing the preference setting files.
   */
  private static File getSettingsDirectory() {
    if (UIStarter.isStarted()) {
      return UIStarter.getApplicationSettingsDirectory();
    } else {
      return null;
    }
  }

}
