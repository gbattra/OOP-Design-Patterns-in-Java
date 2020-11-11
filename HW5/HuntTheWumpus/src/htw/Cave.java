package htw;

import maze.components.Coordinates;
import maze.utils.Direction;

public class Cave extends AbstractCaveNode implements HTWNode {
  public Cave(Coordinates coordinates) throws IllegalArgumentException {
    super(coordinates);
  }
}
