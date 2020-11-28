package htw.game;

import htw.view.IView;

public class Round implements IRound {
  private final int number;

  public Round(int number) {
    this.number = number;
  }
  @Override
  public void receive(IView visitor) {
    visitor.visit(this);
  }
}
