package htw.maze.strategies;

import htw.game.IPlayer;
import htw.maze.nodes.INode;

/**
 * Strategy for a bottomless pit cave.
 */
public class PitStrategy extends StandardStrategy implements INodeStrategy {
  @Override
  public void receive(IPlayer player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    player.kill();
  }

  @Override
  public boolean drafty(INode curr) {
    return true;
  }

  @Override
  public String toString() {
    return "Pit";
  }
}
