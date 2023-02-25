package illgirni.ds.ptde.pc.saveviewer.ui.event;

import javafx.event.EventType;

/**
 * Event to indicate that the save file has to be reloaded.
 * 
 * @author illgirni
 *
 */
public class ReloadSaveEvent extends AbstractEvent<Void> {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Reload save file event type.
   */
  public static final EventType<ReloadSaveEvent> RELOAD_SAVE =
      new EventType<>(SAVE_VIEWER_EVENT, "RELOAD_SAVE");

  /**
   * No payload.
   */
  public ReloadSaveEvent() {
    super(RELOAD_SAVE);
  }

}
