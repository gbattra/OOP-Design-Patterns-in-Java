package htw.game;

import maze.game.MazePlayer;

public class Player extends MazePlayer implements IPlayer {
  private int arrowCount;
  private boolean alive;

  public Player(String name, int arrowCount) {
    super(name);
    this.alive = true;
    this.arrowCount = Math.max(0, arrowCount);
  }

  @Override
  public boolean isAlive() {
    return this.alive;
  }

  @Override
  public int arrowCount() {
    return this.arrowCount;
  }
}
