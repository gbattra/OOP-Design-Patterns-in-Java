package htw.game.events;

import htw.game.IHtwGame;
import visitors.IGameEventVisitor;

public class RestartEvent implements IGameEvent {
  public final IHtwGame game;

  public RestartEvent(IHtwGame game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("Unable to instantiate RestartEvent. Game is null.");
    }

    this.game = game;
  }
  @Override
  public void receive(IGameEventVisitor visitor) {
    visitor.restart(this.game);
  }
}
