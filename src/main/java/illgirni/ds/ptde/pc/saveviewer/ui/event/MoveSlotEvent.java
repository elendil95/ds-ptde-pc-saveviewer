package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;

import javafx.event.EventType;

/**
 * Event to indicate that an exported slot has to be moved to a new group. Payload
 * is the slot to move.
 * 
 * @author illgirni
 *
 */
public class MoveSlotEvent extends AbstractEvent<ExportedSlot> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Move slot event type.
     */
    public static final EventType<MoveSlotEvent> MOVE_SLOT = new EventType<>(SAVE_VIEWER_EVENT, "MOVE_SLOT");
    
    /**
     * @param slot The slot to move.
     */
    public MoveSlotEvent(final ExportedSlot slot) {
        super(MOVE_SLOT, slot);
    }

}
