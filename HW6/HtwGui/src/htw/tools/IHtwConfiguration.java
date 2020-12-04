package htw.tools;

import maze.config.IConfiguration;

/**
 * Configuration object for building a hunt the wumpus maze.
 */
public interface IHtwConfiguration extends IConfiguration {
  /**
   * Getter for the bat frequency.
   *
   * @return the bat frequency
   */
  double batFrequency();

  /**
   * Getter for the pit frequency.
   *
   * @return the pit frequency
   */
  double pitFrequency();

  /**
   * Getter for the arrow count.
   *
   * @return the arrow count
   */
  int arrowCount();

  /**
   * Getter for the number of players in the game.
   *
   * @return the number of players
   */
  int numPlayers();

  /**
   * Returns the logger for the game.
   *
   * @return appendable out object
   */
  Appendable getLogger();
}
