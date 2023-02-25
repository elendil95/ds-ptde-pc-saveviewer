package illgirni.ds.ptde.pc.saveviewer.ui.event;

import illgirni.ds.ptde.pc.saveviewer.savemanager.workspace.ExportedSlotGroup;

import javafx.event.EventType;

/**
 * Event to indicate that a group has to be moved to a new parent group. Payload is the group to
 * move.
 * 
 * @author illgirni
 *
 */
public class MoveGroupEvent extends AbstractEvent<ExportedSlotGroup> {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Move group event type.
   */
  public static final EventType<MoveGroupEvent> MOVE_GROUP =
      new EventType<>(SAVE_VIEWER_EVENT, "MOVE_GROUP");

  /**
   * @param slotGroup The group to move.
   */
  public MoveGroupEvent(final ExportedSlotGroup slotGroup) {
    super(MOVE_GROUP, slotGroup);
  }

}
