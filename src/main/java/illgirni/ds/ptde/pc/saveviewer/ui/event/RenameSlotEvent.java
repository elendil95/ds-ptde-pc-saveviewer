package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlot;

import javafx.event.EventType;

/**
 * Event to indicate that an exported slot has to be renamed. Payload is the slot to be renamed.
 * 
 * @author illgirni
 *
 */
public class RenameSlotEvent extends AbstractEvent<ExportedSlot> {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Rename slot event type.
   */
  public static final EventType<RenameSlotEvent> RENAME_SLOT =
      new EventType<>(SAVE_VIEWER_EVENT, "RENAME_SLOT");

  /**
   * @param slot The slot to rename.
   */
  public RenameSlotEvent(final ExportedSlot slot) {
    super(RENAME_SLOT, slot);
  }

}
