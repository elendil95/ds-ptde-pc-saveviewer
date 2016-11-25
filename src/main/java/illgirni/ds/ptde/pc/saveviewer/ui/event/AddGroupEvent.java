package illgirni.ds.ptde.pc.saveviewer.ui.event;

import javafx.event.EventType;

/**
 * Event to indicate that a new group for exported slots should be created.
 * 
 * @author illgirni
 *
 */
public class AddGroupEvent extends AbstractEvent<Void> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Add group event type.
     */
    public static final EventType<AddGroupEvent> ADD_GROUP = new EventType<>(SAVE_VIEWER_EVENT, "ADD_GROUP");
    
    /**
     * No payload.
     */
    public AddGroupEvent() {
        super(ADD_GROUP);
    }

}
