package illgirni.ds.ptde.pc.saveviewer.ui.preferences;

import illgirni.ds.ptde.pc.saveviewer.ui.layout.LayoutUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The value of a {@link PreferenceSetting} as defined by the user.
 * 
 * @author illgirni
 *
 */
public class PreferenceValue {
    
    /**
     * The setting.
     */
    private final PreferenceSetting preference;
    
    /**
     * If the default value is to be used.
     */
    private final SimpleBooleanProperty useDefault = new SimpleBooleanProperty(); 
    
    /**
     * The custom value.
     */
    private final SimpleIntegerProperty value = new SimpleIntegerProperty();
    
    /**
     * @param preference The setting.
     */
    public PreferenceValue(final PreferenceSetting preference) {
        this.preference = preference;
        this.value.set(LayoutUtils.getPreferenceValue(preference));
        this.useDefault.set(preference.getDefaultValue() == value.get());
    }
    
    /**
     * The setting.
     */
    public PreferenceSetting getPreference() {
        return preference;
    }
    
    /**
     * If the default value is to be used.
     */
    public SimpleBooleanProperty getUseDefault() {
        return useDefault;
    }
    
    /**
     * The custom value.
     */
    public SimpleIntegerProperty getValue() {
        return value;
    }
    
}