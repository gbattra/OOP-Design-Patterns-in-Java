package htw.game;

import java.util.ArrayList;
import java.util.List;

import htw.game.events.IGameEvent;
import visitors.IRoundVisitor;

public class Round implements IRound {
  private final int number;
  private final List<IGameEvent> events = new ArrayList<>();

  public Round(int number) throws IllegalArgumentException {
    if (number < 0) {
      throw new IllegalArgumentException("Cannot instantiate Round. Number is negative.");
    }

    this.number = number;
  }

  @Override
  public void receive(IRoundVisitor visitor) {
    visitor.visitRound(this);
  }

  @Override
  public List<IGameEvent> getGameEvents() {
    return this.events;
  }

  @Override
  public void addEvent(IGameEvent e) throws IllegalArgumentException {
    if (e == null) {
      throw new IllegalArgumentException("Cannot add event to round. Event object is null.");
    }

    this.events.add(e);
  }
}
