package illgirni.ds.ptde.pc.saveviewer.ui.event;

import javafx.event.EventType;

/**
 * Event to indicate that a menu entry has been clicked. Payload is the 
 * menu element type for the menu entry that has been clicked.
 * 
 * @author illgirni
 *
 */
public class MenuEvent extends AbstractEvent<MenuEvent.MenuElement> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Menu event type.
     */
    public static final EventType<MenuEvent> MENU = new EventType<>(SAVE_VIEWER_EVENT, "MENU");
    
    /**
     * The available menu items.
     */
    public static enum MenuElement {
        FILE_INFO,
        
        SETTINGS,
        
        CREDITS,
        
        ABOUT
    }
    
    /**
     * @param menuEntry The clicked menu element.
     */
    public MenuEvent(MenuElement menuEntry) {
        super(MENU, menuEntry);
    }
    
}
