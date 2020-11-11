package htw.nodes;

import htw.strategies.INodeStrategy;
import maze.components.nodes.DeadEndNode;
import maze.utils.Direction;

public class DeadEnd extends DeadEndNode implements INode {
  @Override
  public INode enter(Direction from) throws IllegalStateException {
    throw new IllegalStateException("Cannot enter a dead end node.");
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return false;
  }

  @Override
  public void setStrategy(INodeStrategy strategy) throws IllegalStateException {
    throw new IllegalStateException("Cannot set strategy on a dead end node.");
  }
}
