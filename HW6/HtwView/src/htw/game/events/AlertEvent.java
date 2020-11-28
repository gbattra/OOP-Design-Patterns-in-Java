package htw.game.events;

import visitors.IGameEventVisitor;

public class AlertEvent implements IGameEvent {
  private final String message;

  public AlertEvent(String message) throws IllegalArgumentException {
    if (message == null || message.isEmpty()) {
      throw new IllegalArgumentException(
              "Unable to instantiate AlertEvent. Message empty or null.");
    }

    this.message = message;
  }
  @Override
  public void receive(IGameEventVisitor visitor) {
    visitor.alert(this.message);
  }
}
