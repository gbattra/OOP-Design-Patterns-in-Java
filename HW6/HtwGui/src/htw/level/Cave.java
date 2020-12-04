package htw.level;

import maze.components.ICoordinates;

/**
 * A cave node in a Hunt the Wumpus maze. Effectively the base node, which takes different
 * strategies to augment its functionality.
 */
public class Cave extends AbstractCave implements IHtwNode {
  /**
   * Main constructor for the cave node. Takes a coordinate pair and a strategy.
   *
   * @param id the node's id
   * @param coordinates the coordinates where the node resides
   * @param strategy the strategy used by the node
   * @param logger the logger for event output
   * @throws IllegalArgumentException if strategy or coordinates are null
   */
  public Cave(
          Integer id,
          ICoordinates coordinates,
          IHtwNodeStrategy strategy,
          Appendable logger)
          throws IllegalArgumentException {
    super(id, coordinates, strategy, logger);
  }
}
