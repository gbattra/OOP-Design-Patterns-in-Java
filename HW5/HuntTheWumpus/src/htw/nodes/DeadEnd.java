package htw.nodes;

import htw.strategies.HtwNodeStrategy;
import maze.components.nodes.DeadEndNode;
import maze.utils.Direction;

public class DeadEnd extends DeadEndNode implements HtwNode {
  @Override
  public HtwNode enter(Direction from) throws IllegalStateException {
    throw new IllegalStateException("Cannot enter a dead end node.");
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return false;
  }

  @Override
  public void setStrategy(HtwNodeStrategy strategy) throws IllegalStateException {
    throw new IllegalStateException("Cannot set strategy on a dead end node.");
  }
}
