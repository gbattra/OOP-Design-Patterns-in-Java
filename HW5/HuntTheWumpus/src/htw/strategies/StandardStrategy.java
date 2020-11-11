package htw.strategies;

import htw.nodes.HtwNode;
import maze.utils.Direction;

public class StandardStrategy implements HtwNodeStrategy {
  @Override
  public HtwNode enter(Direction from, HtwNode curr) {
    return curr;
  }

  @Override
  public boolean shoot(Direction direction, int count, HtwNode curr) {
    if (count == 0) {
      return false;
    }
    return ((HtwNode) curr.getNode(direction)).shoot(direction, count - 1);
  }
}
