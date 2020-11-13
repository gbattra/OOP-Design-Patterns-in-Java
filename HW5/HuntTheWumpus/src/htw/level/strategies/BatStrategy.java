package htw.level.strategies;

import java.util.Random;

import htw.level.nodes.INode;
import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.utils.Direction;

/**
 * Strategy used for a bat node. If enter() is called, there is a 50% chance that a different,
 * random node in the maze will be entered instead.
 */
public class BatStrategy extends StandardStrategy implements INodeStrategy {
  private final INodeStrategy parent;
  private final Random random;
  private final int rowCount;
  private final int columnCount;

  /**
   * Constructor for the bat strategy.
   *
   * @param rowCount the number of rows in the maze (used for choosing random coordinates)
   * @param columnCount the number of columns in the maze (used for choosing random coordinates)
   * @param random the random instance to use when choosing coordinates
   * @param parent the parent strategy to use when bats don't grab player
   */
  public BatStrategy(
          int rowCount,
          int columnCount,
          Random random,
          INodeStrategy parent) {
    if (rowCount < 0 || columnCount < 0) {
      throw new IllegalArgumentException("Row count and column count cannot be zero.");
    }
    if (random == null || parent == null) {
      throw new IllegalArgumentException("Random and parent cannot be null.");
    }
    this.parent = parent;
    this.random = random;
    this.rowCount = rowCount;
    this.columnCount = columnCount;
  }

  @Override
  public INode enter(Direction from, INode curr) {
    if (this.random.nextDouble() <= 0.5) {
      int row = this.random.nextInt(this.rowCount);
      int column = this.random.nextInt(this.columnCount);
      ICoordinates coordinates = new Coordinates(column, row);
      return ((INode) curr.get(coordinates)).enter(from);
    }

    return this.parent.enter(from, curr);
  }

  @Override
  public boolean shoot(Direction direction, int count, INode curr) {
    if (count == 0) {
      return this.parent.shoot(direction, count, curr);
    }
    return ((INode) curr.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public String toString() {
    return "Bat(" + this.parent.toString() + ")";
  }
}