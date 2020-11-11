package htw.strategies;

import htw.nodes.HTWNode;
import maze.utils.Direction;

public class StandardStrategy implements Strategy {
  @Override
  public HTWNode enter(Direction from, HTWNode curr) {
    return curr;
  }

  @Override
  public boolean shoot(Direction direction, int count, HTWNode curr) {
    if (count == 0) {
      return false;
    }
    return ((HTWNode) curr.getNode(direction)).shoot(direction, count - 1);
  }
}
