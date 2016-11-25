package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;

import javafx.event.EventType;

/**
 * Event to indicate that a group has to be renamed. Payload is the group to 
 * be renamed.
 * 
 * @author illgirni
 *
 */
public class RenameGroupEvent extends AbstractEvent<ExportedSlotGroup> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Rename group event type.
     */
    public static final EventType<RenameGroupEvent> RENAME_GROUP = new EventType<>(SAVE_VIEWER_EVENT, "RENAME_GROUP");
    
    /**
     * @param slotGroup The group to rename.
     */
    public RenameGroupEvent(final ExportedSlotGroup slotGroup) {
        super(RENAME_GROUP, slotGroup);
    }

}
