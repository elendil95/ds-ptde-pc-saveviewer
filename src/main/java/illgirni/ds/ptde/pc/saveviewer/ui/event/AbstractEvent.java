package illgirni.ds.ptde.pc.saveviewer.ui.event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Base for events that we use to achieve loose coupling between the UI components. Allows to pass
 * payload along with the event.
 * 
 * @author illgirni
 *
 * @param <T>
 */
public class AbstractEvent<T> extends Event {

  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The base event type.
   */
  public static final EventType<AbstractEvent<?>> SAVE_VIEWER_EVENT =
      new EventType<>(Event.ANY, "SAVE_VIEWER_EVENT");

  /**
   * The payload of the event.
   */
  private T payload;

  /**
   * @param eventType The event type.
   */
  public AbstractEvent(EventType<? extends Event> eventType) {
    super(eventType);
  }

  /**
   * @param eventType The event type.
   * @param payload The event payload.
   */
  public AbstractEvent(EventType<? extends Event> eventType, final T payload) {
    super(eventType);
    this.payload = payload;
  }

  /**
   * The payload of the event.
   */
  public T getPayload() {
    return payload;
  }
}
