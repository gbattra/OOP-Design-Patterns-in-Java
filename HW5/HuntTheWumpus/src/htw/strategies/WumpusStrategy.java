package htw.strategies;

import htw.nodes.HtwNode;
import maze.utils.Direction;

public class WumpusStrategy extends StandardStrategy implements HtwNodeStrategy {
  @Override
  public boolean shoot(Direction direction, int count, HtwNode curr) {
    if (count == 0) {
      return true;
    }

    return ((HtwNode) curr.getNode(direction)).shoot(direction, count - 1);
  }
}
