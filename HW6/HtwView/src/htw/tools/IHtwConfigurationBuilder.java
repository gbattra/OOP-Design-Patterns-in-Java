package htw.tools;

import maze.config.IConfigurationBuilder;

/**
 * Builder for a config object to use when creating a maze for Hunt the Wumpus.
 */
public interface IHtwConfigurationBuilder extends IConfigurationBuilder {
  /**
   * Set the starting arrow count for players.
   *
   * @param arrowCount the starting arrow count
   * @return updated builder instance
   */
  IHtwConfigurationBuilder setArrowCount(int arrowCount);

  /**
   * Set the number of players in the game.
   *
   * @param numplayers the number of players in the game
   * @return the updated builder instance
   */
  IHtwConfigurationBuilder setNumPlayers(int numplayers);

  /**
   * The frequency of pits in the maze.
   *
   * @param pitFrequency value between 0 and 1
   * @return updated maze builder instance
   */
  IHtwConfigurationBuilder setPitFrequency(double pitFrequency);

  /**
   * The frequency of bats in the maze.
   *
   * @param batFrequency value between 0 and 1
   * @return updated maze builder instance
   */
  IHtwConfigurationBuilder setBatFrequency(double batFrequency);

  /**
   * Sets the logger to use in the maze.
   *
   * @param logger the logger for the maze
   * @return updated maze builder instance
   */
  IHtwConfigurationBuilder setLogger(Appendable logger);

  /**
   * Builds the configuration.
   *
   * @return the customized config object
   */
  IHtwConfiguration build();
}
