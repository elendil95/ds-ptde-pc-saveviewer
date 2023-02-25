package illgirni.ds.ptde.pc.saveviewer.ui.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Wrapper for the access to ResourceBundles.
 * 
 * @author illgirni
 *
 */
public final class Messages {

  /**
   * Bundle containing labels for enum values.
   */
  private static ResourceBundle enumBundle;

  private Messages() {}

  /**
   * Gets the corresponding value from the enum ResourceBudle.
   * 
   * @param enumValue The value for which to get a bundle entry.
   * @return The bundle entry; {@code ???$key???} when not in the bundle.
   */
  public static String getMessage(Enum<?> enumValue) {
    if (enumBundle == null) {
      enumBundle = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".Enum");
    }

    return getString(enumBundle, getEnumkey(enumValue));
  }

  /**
   * Creates the bundle key for the enum value.
   * 
   * @param enumValue The enum value.
   * @return The bundle key.
   */
  private static String getEnumkey(final Enum<?> enumValue) {
    if (enumValue == null) {
      return null;

    } else {
      return enumValue.getDeclaringClass().getSimpleName() + "." + enumValue;
    }
  }

  /**
   * Gets the value with the given key from the given bundle.
   * 
   * @param bundle The bundle.
   * @param key The key.
   * @return The bundle value; {@code ???$key???} when not in the bundle.
   */
  private static String getString(final ResourceBundle bundle, final String key) {
    if (key == null) {
      return "???null???";
    } else {
      try {
        return bundle.getString(key);
      } catch (MissingResourceException e) {
        return "???" + key + "???";
      }
    }
  }

}
