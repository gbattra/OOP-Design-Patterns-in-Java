package htw;

import maze.components.Coordinates;
import maze.utils.Direction;

public class WumpusCave extends Cave implements HTWNode {
  public WumpusCave(Coordinates coordinates) {
    super(coordinates);
    this.north = new DeadEnd();
    this.south = new DeadEnd();
    this.east = new DeadEnd();
    this.west = new DeadEnd();
  }

  @Override
  public boolean shoot(Direction direction, int count) throws IllegalArgumentException {
    if (count == 0) {
      return true;
    }

    return super.shoot(direction, count - 1);
  }
}
