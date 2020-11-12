package htw.game;

/**
 * Interface for a player in the HTW maze game.
 */
public interface IPlayer {
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
}
