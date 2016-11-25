package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;

import javafx.event.EventType;

/**
 * Indicates that the details of an exported slot have to be displayed. Payload
 * is the exported slot.
 * 
 * @author illgirni
 *
 */
public class ShowExportedSlotEvent extends AbstractEvent<ExportedSlot> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Show exported slot event type.
     */
    public static final EventType<ShowExportedSlotEvent> SHOW_EXPORTED_SLOT = new EventType<>(SAVE_VIEWER_EVENT, "SHOW_EXPORTED_SLOT");
    
    /**
     * @param slotToShow The slot for which to show the details.
     */
    public ShowExportedSlotEvent(final ExportedSlot slotToShow) {
        super(SHOW_EXPORTED_SLOT, slotToShow);
    }

}
