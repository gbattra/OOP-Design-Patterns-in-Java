package htw.view;

import htw.game.IHtwGame;
import htw.game.events.IGameEvent;
import maze.components.ICoordinates;
import visitors.IVisitable;
import visitors.IVisitor;

public class ConsoleView implements IView {
  @Override
  public <R extends IVisitor, T extends IVisitable<R>> void visit(T receiver) {
  }

  @Override
  public void renderRestart(IHtwGame game) {
  }

  @Override
  public void renderMove(ICoordinates coordinates) {

  }

  @Override
  public void renderAlert(String message) {

  }
}
