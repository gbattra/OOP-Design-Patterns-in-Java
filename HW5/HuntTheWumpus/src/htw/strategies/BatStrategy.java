package htw.strategies;

import java.util.Random;

import htw.nodes.INode;
import maze.components.ICoordinates;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

public class BatStrategy extends StandardStrategy implements INodeStrategy {
  private final INodeStrategy parent;
  private final Random random;
  private final int rowCount;
  private final int columnCount;

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
      ICoordinates coordinates = new MazeCoordinates(column, row);
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
}
