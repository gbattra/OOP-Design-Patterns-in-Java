package htw.strategies;

import htw.nodes.INode;
import maze.utils.Direction;

public class WumpusStrategy extends StandardStrategy implements INodeStrategy {
  @Override
  public boolean shoot(Direction direction, int count, INode curr) {
    if (count == 0) {
      return true;
    }

    return ((INode) curr.getNode(direction)).shoot(direction, count - 1);
  }
}
