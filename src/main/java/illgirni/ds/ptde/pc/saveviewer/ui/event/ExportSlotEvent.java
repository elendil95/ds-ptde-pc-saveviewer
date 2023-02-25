package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;

import javafx.event.EventType;

/**
 * Event to indicate that a slot should be exported from the save file. Payload is the slot to
 * export.
 * 
 * @author illgirni
 *
 */
public class ExportSlotEvent extends AbstractEvent<SaveSlot> {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Export slot event type.
   */
  public static final EventType<ExportSlotEvent> EXPORT_SLOT =
      new EventType<>(SAVE_VIEWER_EVENT, "EXPORT_SLOT");

  /**
   * @param slot The slot to export.
   */
  public ExportSlotEvent(final SaveSlot slot) {
    super(EXPORT_SLOT, slot);
  }

}
