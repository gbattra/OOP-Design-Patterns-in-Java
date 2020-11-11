package htw.nodes;

import maze.components.nodes.DeadEndNode;
import maze.utils.Direction;

public class DeadEnd extends DeadEndNode implements HTWNode {
  @Override
  public HTWNode enter(Direction from) throws IllegalStateException {
    throw new IllegalStateException("Cannot enter a dead end node.");
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return false;
  }
}
