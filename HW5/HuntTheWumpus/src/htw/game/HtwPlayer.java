package htw.game;

import maze.game.MazePlayer;

/**
 * Player in a HTW maze.
 */
public class HtwPlayer extends MazePlayer implements IHtwPlayer {
  private int arrowCount;
  private boolean alive;

  /**
   * Constructor for the player.
   *
   * @param name the player's name
   * @param arrowCount the number of arrows the player starts with
   */
  public HtwPlayer(String name, int arrowCount) {
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

  @Override
  public void kill() {
    this.alive = false;
  }

  @Override
  public void decrementArrowCount(int count) {
    this.arrowCount -= count;
  }
}
