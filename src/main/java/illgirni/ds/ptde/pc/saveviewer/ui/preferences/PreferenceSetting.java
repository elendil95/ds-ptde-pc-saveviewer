package illgirni.ds.ptde.pc.saveviewer.ui.preferences;

/**
 * User preference values supported by the application.
 * 
 * @author illgirni
 *
 */
public enum PreferenceSetting {
    
    /**
     * If window should be maximized on application start.
     */
    WINDOW_MAXIMIZED("window.maximized", 0, 0, 1),
    
    /**
     * Window width at application start.
     */
    WINDOW_WIDTH("window.width", 1260, 800, 1900),
    
    /**
     * Window height at application start.
     */
    WINDOW_HEIGHT("window.height", 700, 600, 1000),
    
    /**
     * The position of the divider between slot lists and slot details panels
     * at application start.
     */
    WINDOW_DIVIDER("window.divider", 430, 400, 1000),
    
    /**
     * The base font size.
     */
    FONT_SIZE("font.size", -1, 10, 20),
    
    /**
     * The layout of the character details.
     */
    CHARACTER_DETAILS("character.details", 1, 1, 3);
    
    /**
     * Setting default value.
     */
    private final int defaultValue;
    
    /**
     * Setting minimum value.
     */
    private final int minValue;
    
    /**
     * Setting maximum value.
     */
    private final int maxValue;
    
    /**
     * Key in the settings file.
     */
    private final String preferencesKey;
    
    /**
     * @param preferencesKey Key in the settings file.
     * @param defaultValue Setting default value.
     * @param minValue Setting minimum value.
     * @param maxValue Setting maximum value.
     */
    private PreferenceSetting(final String preferencesKey, final int defaultValue, final int minValue, final int maxValue) {
        this.preferencesKey = preferencesKey;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    /**
     * Key in the settings file.
     */
    public String getPreferencesKey() {
        return preferencesKey;
    }
    
    /**
     * Setting default value.
     */
    public int getDefaultValue() {
        return defaultValue;
    }
    
    /**
     * Setting minimum value.
     */
    public int getMinValue() {
        return minValue;
    }
    
    /**
     * Setting maximum value.
     */
    public int getMaxValue() {
        return maxValue;
    }
    
}