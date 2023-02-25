package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;

import javafx.event.EventType;

/**
 * Event to indicate that an exported slot should be deleted. Payload is the slot to delete.
 * 
 * @author illgirni
 *
 */
public class DeleteSlotEvent extends AbstractEvent<ExportedSlot> {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Delete slot event type.
   */
  public static final EventType<DeleteSlotEvent> DELETE_SLOT =
      new EventType<>(SAVE_VIEWER_EVENT, "DELETE_SLOT");

  /**
   * @param slot The slot to delete.
   */
  public DeleteSlotEvent(final ExportedSlot slot) {
    super(DELETE_SLOT, slot);
  }

}
