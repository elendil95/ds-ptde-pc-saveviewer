package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;

import javafx.event.EventType;

/**
 * Event to indicate that a group for exported slots should be deleted. Payload
 * is the group to delete.
 * 
 * @author illgirni
 *
 */
public class DeleteGroupEvent extends AbstractEvent<ExportedSlotGroup> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Delete group event type.
     */
    public static final EventType<DeleteGroupEvent> DELETE_GROUP = new EventType<>(SAVE_VIEWER_EVENT, "DELETE_GROUP");
    
    /**
     * @param slotGroup The group to delete.
     */
    public DeleteGroupEvent(final ExportedSlotGroup slotGroup) {
        super(DELETE_GROUP, slotGroup);
    }

}
