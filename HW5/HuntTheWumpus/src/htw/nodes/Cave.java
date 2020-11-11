package htw.nodes;

import htw.strategies.Strategy;
import maze.components.Coordinates;
import maze.utils.Direction;

public class Cave extends AbstractCaveNode implements HTWNode {
  public Cave(Coordinates coordinates, Strategy strategy) throws IllegalArgumentException {
    super(coordinates, strategy);
  }
}
