package htw.strategies;

import htw.game.IPlayer;
import htw.nodes.INode;
import maze.utils.Direction;

/**
 * The base strategy for a cave in the maze. All other strategies extend this strategy and
 * typically override only one or two of its methods.
 */
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
