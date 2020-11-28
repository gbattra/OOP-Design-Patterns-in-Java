package htw.game.events;

import htw.view.IView;

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
  public void receive(IView visitor) {
    visitor.handleAlert(this.message);
  }
}
