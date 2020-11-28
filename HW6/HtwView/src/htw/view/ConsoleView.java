package htw.view;

import htw.game.IHtwGame;
import maze.components.ICoordinates;
import visitors.IVisitable;
import visitors.IVisitor;

public class ConsoleView implements IView {
  @Override
  public <R extends IVisitor, T extends IVisitable<R>> void visit(T receiver) {
  }

  @Override
  public void handleRestart(IHtwGame game) {
  }

  @Override
  public void handleMove(ICoordinates coordinates) {

  }

  @Override
  public void handleAlert(String message) {

  }
}
