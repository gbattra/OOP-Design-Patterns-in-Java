package htw;

import maze.components.nodes.DeadEndNode;
import maze.utils.Direction;

public class DeadEnd extends DeadEndNode implements MazeNode {
  @Override
  public MazeNode enter(Direction from) throws IllegalStateException {
    throw new IllegalStateException("Cannot enter a dead end node.");
  }

  @Override
  public MazeNode promote() throws IllegalStateException {
    throw new IllegalStateException("Cannot promote a dead end node.");
  }
}
