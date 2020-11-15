package htw.level.strategies;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;
import maze.utils.Direction;

/**
 * Strategy for the cave with the wumpus. Receive() kills the player and shoot() returns true.
 */
public class WumpusStrategy extends StandardStrategy implements IHtwNodeStrategy {
  @Override
  public boolean shoot(Direction direction, int count, IHtwNode curr) {
    if (count == 0) {
      return true;
    }

    return ((IHtwNode) curr.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public void receive(IHtwPlayer player, IHtwNode curr) throws IOException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    curr.logger().append("Chomp chomp! You've been eaten by the Wumpus!");
    player.kill();
  }

  @Override
  public boolean smelly(Direction from, IHtwNode node) {
    return true;
  }

  @Override
  public String toString() {
    return "Wumpus";
  }
}
