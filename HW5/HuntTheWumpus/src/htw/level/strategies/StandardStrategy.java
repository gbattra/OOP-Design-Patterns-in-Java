package htw.level.strategies;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;
import maze.utils.Direction;

/**
 * The base strategy for a cave in the maze. All other strategies extend this strategy and
 * typically override only one or two of its methods.
 */
public class StandardStrategy implements IHtwNodeStrategy {
  @Override
  public IHtwNode enter(Direction from, IHtwNode curr) {
    return curr;
  }

  @Override
  public boolean shoot(Direction direction, int count, IHtwNode curr) {
    if (count == 0) {
      return false;
    }
    return ((IHtwNode) curr.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public void receive(IHtwPlayer player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    // do nothing
  }

  @Override
  public boolean smelly(IHtwNode curr) {
    return false;
  }

  @Override
  public boolean drafty(IHtwNode curr) {
    return false;
  }

  @Override
  public String toString() {
    return "Standard";
  }
}
