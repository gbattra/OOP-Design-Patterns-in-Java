package htw.level.strategies;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;

/**
 * Strategy for a bottomless pit cave.
 */
public class PitStrategy extends StandardStrategy implements IHtwNodeStrategy {
  @Override
  public void receive(IHtwPlayer player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    player.kill();
  }

  @Override
  public boolean drafty(IHtwNode curr) {
    return true;
  }

  @Override
  public String toString() {
    return "Pit";
  }
}
