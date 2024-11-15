package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import maze.Direction;

/**
 * Strategy for a bottomless pit cave.
 */
public class PitStrategy extends StandardStrategy implements IHtwNodeStrategy {
  @Override
  public void receive(IHtwPlayer player, IHtwNode curr)
          throws IllegalArgumentException, IOException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    curr.logger().append("'Ahhhhhhhh', you shout as you fall into a bottomless pit!\n");
    player.kill();
  }

  @Override
  public boolean drafty(Direction from, IHtwNode curr) {
    return true;
  }

  @Override
  public String toString() {
    return "Pit";
  }
}
