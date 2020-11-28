package htw.view;

import htw.game.IHtwGame;
import htw.game.IRound;
import htw.game.events.IGameEvent;
import maze.components.ICoordinates;

public class ConsoleView implements IView {
  @Override
  public void visitRound(IRound round) {
    for (IGameEvent event : round.getGameEvents()) {
      event.receive(this);
    }
  }

  @Override
  public void restart(IHtwGame game) {

  }

  @Override
  public void move(ICoordinates coordinates) {

  }

  @Override
  public void alert(String message) {

  }
}
