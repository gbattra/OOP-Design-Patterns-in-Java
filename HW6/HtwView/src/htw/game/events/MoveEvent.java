package htw.game.events;

import maze.components.ICoordinates;
import visitors.IGameEventVisitor;

public class MoveEvent implements IGameEvent {
  private final ICoordinates coordinates;

  public MoveEvent(ICoordinates coordinates) throws IllegalArgumentException {
    if (coordinates == null) {
      throw new IllegalArgumentException("Unable to instantiate MoveEvent. Coordinates null.");
    }

    this.coordinates = coordinates;
  }

  @Override
  public void receive(IGameEventVisitor visitor) {
    visitor.move(this.coordinates);
  }
}
