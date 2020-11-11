package htw.strategies;

import htw.game.IPlayer;
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

  @Override
  public void receive(IPlayer player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }

    player.kill();
  }
}
