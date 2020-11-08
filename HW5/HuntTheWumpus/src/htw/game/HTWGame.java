package htw.game;
public class HTWGame implements Game {
  private boolean done;

  @Override
  public void set(boolean done) {
    this.done = done;
  }

  @Override
  public boolean getDone() {
    return this.done;
  }

  @Override
  public boolean hasNext() {
    return !this.getDone();
  }

  @Override
  public Round next() {
    return new HTWRound();
  }
}
