package htw.game;

import htw.view.IView;

public class RestartEvent implements IGameEvent {
  @Override
  public void receive(IView visitor) {
    visitor.renderRestart(this);
  }
}
