package htw.view;

import htw.game.IGameEvent;
import visitors.IVisitable;
import visitors.IVisitor;

public class ConsoleView implements IView {
  @Override
  public <R extends IVisitor, T extends IVisitable<R>> void visit(T receiver) {
  }

  @Override
  public void renderRestart(IGameEvent e) {
  }
}
