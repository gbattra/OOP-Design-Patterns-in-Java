package htw.game;

import htw.view.IView;

public class Round implements IRound {
  @Override
  public void receive(IView visitor) {
    visitor.visit(this);
  }
}
