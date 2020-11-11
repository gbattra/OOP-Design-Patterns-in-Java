package htw.strategies;

import htw.game.IPlayer;

public class PitStrategy extends StandardStrategy implements INodeStrategy {
  @Override
  public void receive(IPlayer player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    player.kill();
  }
}
