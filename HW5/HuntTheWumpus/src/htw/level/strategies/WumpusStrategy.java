package htw.level.strategies;

import htw.game.IPlayer;
import htw.level.nodes.INode;
import maze.utils.Direction;

/**
 * Strategy for the cave with the wumpus. Receive() kills the player and shoot() returns true.
 */
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

  @Override
  public boolean smelly(INode node) {
    return true;
  }

  @Override
  public String toString() {
    return "Wumpus";
  }
}
