package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;

import javafx.event.EventType;

/**
 * Event to indicate that an exported slot should be imported into the save file. Payload
 * is the slot to import.
 * 
 * @author illgirni
 *
 */
public class ImportSlotEvent extends AbstractEvent<SaveSlot> {
    
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Import slot event type.
     */
    public static final EventType<ImportSlotEvent> IMPORT_SLOT = new EventType<>(SAVE_VIEWER_EVENT, "IMPORT_SLOT");
    
    /**
     * @param slot The slot to import.
     */
    public ImportSlotEvent(final SaveSlot slot) {
        super(IMPORT_SLOT, slot);
    }

}
