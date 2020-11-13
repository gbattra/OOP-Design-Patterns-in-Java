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
  double getBatFrequency();

  /**
   * Getter for the pit frequency.
   *
   * @return the pit frequency
   */
  double getPitFrequency();

  /**
   * Returns the logger for the game.
   *
   * @return appendable out object
   */
  Appendable getLogger();
}
