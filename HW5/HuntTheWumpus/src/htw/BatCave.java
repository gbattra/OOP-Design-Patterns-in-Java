package htw;

import java.util.Random;

import maze.components.Coordinates;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

public class BatCave extends Cave implements HTWNode {
  private final HTWNode parent;
  private final Random random;
  private final int rowCount;
  private final int columnCount;

  public BatCave(
          int rowCount,
          int columnCount,
          Coordinates coordinates,
          HTWNode parent) {
    super(coordinates);
    this.parent = parent;
    this.random = new Random();
    this.rowCount = rowCount;
    this.columnCount = columnCount;
  }

  @Override
  public HTWNode enter(Direction from) {
    if (this.random.nextDouble() <= 0.5) {
      int row = this.random.nextInt(this.rowCount);
      int column = this.random.nextInt(this.columnCount);
      Coordinates coordinates = new MazeCoordinates(column, row);
      return ((HTWNode) this.get(coordinates)).enter(from);
    }

    return this.parent.enter(from);
  }
}
