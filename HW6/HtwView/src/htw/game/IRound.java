package htw.game;

import java.util.List;

import htw.game.events.IGameEvent;
import visitors.IRoundVisitor;
import visitors.IVisitable;

public interface IRound extends IVisitable<IRoundVisitor> {
  void addEvent(IGameEvent e) throws IllegalArgumentException;
  List<IGameEvent> getGameEvents();
}
