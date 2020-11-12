package htw.nodes;

import htw.strategies.INodeStrategy;
import maze.components.ICoordinates;

public class Cave extends AbstractCave implements INode {
  public Cave(ICoordinates coordinates, INodeStrategy strategy) throws IllegalArgumentException {
    super(coordinates, strategy);
  }
}
