package htw.nodes;

import htw.strategies.INodeStrategy;
import maze.components.ICoordinates;

/**
 * A cave node in a Hunt the Wumpus maze. Effectively the base node, which takes different
 * strategies to augment its functionality.
 */
public class Cave extends AbstractCave implements INode {
  /**
   * Main constructor for the cave node. Takes a coordinate pair and a strategy.
   *
   * @param coordinates the coordinates where the node resides
   * @param strategy the strategy used by the node
   * @throws IllegalArgumentException if strategy or coordinates are null
   */
  public Cave(ICoordinates coordinates, INodeStrategy strategy) throws IllegalArgumentException {
    super(coordinates, strategy);
  }
}
