package maze.config;

import java.util.Random;

import maze.components.Coordinates;

/**
 * A maze configuration which will be used by the maze builder and nodes to setup the maze.
 */
public interface Configuration {
  /**
   * Getter for the maze column count.
   *
   * @return the column count
   */
  int columnCount();

  /**
   * Getter for the maze row count.
   *
   * @return the maze row count
   */
  int rowCount();

  /**
   * Getter for the thief penalty. Thief penalty determines percent of gold taken.
   *
   * @return the thief penalty
   */
  double thiefPenalty();

  /**
   * How frequently should thieves appear in the maze.
   *
   * @return the frequency of thief appearances
   */
  double thiefFrequency();

  /**
   * How frequently should gold appear in the maze.
   *
   * @return the frequency of gold appearances
   */
  double goldFrequency();

  /**
   * Getter for the amount of gold to put in a room.
   *
   * @return the int gold count in a room with gold
   */
  int goldAmount();

  /**
   * The random generator used for constructing maze (or reconstructing another, i.e. copying).
   *
   * @return the random generator
   */
  Random random();

  /**
   * Gets the random seed used for this configuration.
   *
   * @return the random seed used
   */
  int randomSeed();

  /**
   * Getter for the desired number of remaining edges after maze is complete.
   *
   * @return the total edge count
   */
  int targetEdgeCount();

  /**
   * Is this a room maze.
   *
   * @return true if room maze
   */
  boolean isRoomMaze();

  /**
   * Is this a wrapping maze.
   *
   * @return wrapping maze if true
   */
  boolean isWrappingMaze();

  /**
   * Getter for the maze start coordinates.
   *
   * @return the start coordinates
   */
  Coordinates start();

  /**
   * Getter for the maze goal coordinates.
   *
   * @return the goal coordinates
   */
  Coordinates goal();

  /**
   * Returns the perfect exit count for the configuration.
   *
   * @return int the perfect exit count
   */
  int perfectExitCount();
}
