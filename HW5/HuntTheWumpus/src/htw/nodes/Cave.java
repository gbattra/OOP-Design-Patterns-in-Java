package htw.nodes;

import htw.strategies.HtwNodeStrategy;
import maze.components.Coordinates;

public class Cave extends AbstractCaveNode implements HtwNode {
  public Cave(Coordinates coordinates, HtwNodeStrategy strategy) throws IllegalArgumentException {
    super(coordinates, strategy);
  }
}
