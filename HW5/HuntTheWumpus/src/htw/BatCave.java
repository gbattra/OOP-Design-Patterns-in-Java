package htw;

import java.util.Random;

import maze.components.Coordinates;
import maze.utils.Direction;

public class BatCave extends Cave implements MazeNode {
  private final MazeNode parent;
  private final Random random;

  public BatCave(Coordinates coordinates, MazeNode parent) {
    super(coordinates);
    this.parent = parent;
    this.random = new Random();
  }

  @Override
  public MazeNode enter(Direction from) {
    if (this.random.nextDouble() <= 0.5) {
      // find random node
    }

    return this.parent.enter(from);
  }
}
