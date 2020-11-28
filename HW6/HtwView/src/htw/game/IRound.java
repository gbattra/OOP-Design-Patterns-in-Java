package htw.game;

import htw.game.events.IGameEvent;
import htw.view.IView;
import visitors.IVisitable;

public interface IRound extends IVisitable<IView> {
  void addEvent(IGameEvent e) throws IllegalArgumentException;
}
