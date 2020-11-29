package htw.game;

import maze.components.ICoordinates;
import maze.game.IMazePlayer;

/**
 * Interface for a player in the HTW maze game.
 */
public interface IHtwPlayer extends IMazePlayer {
  /**
   * Gets the player number.
   *
   * @return the player number
   */
  int number();

  /**
   * Gets the player's current coordinates.
   *
   * @return the coordinates of the node where the player is located
   */
  ICoordinates currentPosition();

  /**
   * Set current position for the player.
   *
   * @param coordinates the coordinates for the node where the player is located
   */
  void setCurrentPosition(ICoordinates coordinates);

  /**
   * The number of arrows the player starts with.
   *
   * @return the arrow count
   */
  int arrowCount();

  /**
   * Is the player still alive...
   *
   * @return true if the player is still alive
   */
  boolean isAlive();

  /**
   * Sets isAlive to false.
   */
  void kill();

  /**
   * Decrements the arrow count by one.
   */
  void decrementArrowCount();
}
