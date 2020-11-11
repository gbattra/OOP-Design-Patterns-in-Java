package htw.strategies;

import htw.game.IPlayer;
import htw.nodes.INode;
import maze.utils.Direction;

public class StandardStrategy implements INodeStrategy {
  @Override
  public INode enter(Direction from, INode curr) {
    return curr;
  }

  @Override
  public boolean shoot(Direction direction, int count, INode curr) {
    if (count == 0) {
      return false;
    }
    return ((INode) curr.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public void receive(IPlayer player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    // do nothing
  }
}
