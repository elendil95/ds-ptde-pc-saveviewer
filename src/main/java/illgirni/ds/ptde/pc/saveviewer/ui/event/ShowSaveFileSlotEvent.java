package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;

import javafx.event.EventType;

/**
 * Indicates that the details of a slot in the save file have to be displayed. Payload is the slot.
 * 
 * @author illgirni
 *
 */
public class ShowSaveFileSlotEvent extends AbstractEvent<SaveSlot> {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Show save file slot event type.
   */
  public static final EventType<ShowSaveFileSlotEvent> SHOW_SAVE_FILE_SLOT =
      new EventType<>(SAVE_VIEWER_EVENT, "SHOW_SAVE_FILE_SLOT");

  /**
   * @param slotToShow The slot to display.
   */
  public ShowSaveFileSlotEvent(final SaveSlot slotToShow) {
    super(SHOW_SAVE_FILE_SLOT, slotToShow);
  }

}
