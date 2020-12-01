package htw.game;

import gui.IHtwPlayerVisitor;
import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.game.MazePlayer;

/**
 * Player in a HTW maze.
 */
public class HtwPlayer extends MazePlayer implements IHtwPlayer {
  private int arrowCount;
  private boolean alive;

  private ICoordinates currentPosition = new Coordinates(0, 0);

  /**
   * Constructor for the player.
   *
   * @param name the player's name
   * @param arrowCount the number of arrows the player starts with
   */
  public HtwPlayer(String name, int arrowCount) {
    super(name);
    this.alive = true;
    this.arrowCount = Math.max(1, arrowCount);
  }

  @Override
  public void setCurrentPosition(ICoordinates coordinates) {
    this.currentPosition = coordinates;
  }

  @Override
  public ICoordinates currentPosition() {
    return this.currentPosition;
  }

  @Override
  public int number() {
    return 1;
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
  public void decrementArrowCount() {
    this.arrowCount--;
  }

  @Override
  public <R> R receive(IHtwPlayerVisitor<R> visitor) {
    return visitor.visitPlayer(this.number(), this.arrowCount, this.isAlive());
  }
}
