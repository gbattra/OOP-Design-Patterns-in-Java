package htw;

import maze.components.Coordinates;
import maze.utils.Direction;

public class Cave extends AbstractCaveNode implements HTWNode {
  public Cave(Coordinates coordinates) throws IllegalArgumentException {
    super(coordinates);
  }

  @Override
  public HTWNode enter(Direction from) {
    return this;
  }

  @Override
  public HTWNode promote() {
    return this;
  }

  @Override
  public boolean shoot(Direction direction, int count) throws IllegalArgumentException {
    if (count < 0) {
      throw new IllegalArgumentException("Count cannot be negative.");
    }
    if (count == 0) {
      return false;
    }
    return ((HTWNode) this.getNode(direction)).shoot(direction, count - 1);
  }
}
