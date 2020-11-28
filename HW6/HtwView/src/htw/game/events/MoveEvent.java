package htw.game.events;

import htw.view.IView;
import maze.components.ICoordinates;

public class MoveEvent implements IGameEvent {
  private final ICoordinates coordinates;

  public MoveEvent(ICoordinates coordinates) throws IllegalArgumentException {
    if (coordinates == null) {
      throw new IllegalArgumentException("Unable to instantiate MoveEvent. Coordinates null.");
    }

    this.coordinates = coordinates;
  }

  @Override
  public void receive(IView visitor) {
    visitor.renderMove(this.coordinates);
  }
}
