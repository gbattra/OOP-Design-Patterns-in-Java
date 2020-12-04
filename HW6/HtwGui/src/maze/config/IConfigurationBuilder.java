package maze.config;

/**
 * Builder for a maze configuration.
 */
public interface IConfigurationBuilder {
  /**
   * Sets the number of columns in the maze.
   *
   * @param columnCount the column count
   * @return the updated builder instance
   * @throws IllegalArgumentException if column count is negative or too big
   */
  IConfigurationBuilder setColumnCount(int columnCount) throws IllegalArgumentException;

  /**
   * Sets the number of rows in the maze.
   *
   * @param rowCount the row count
   * @return the updated builder instance
   * @throws IllegalArgumentException if row count is negative or too big
   */
  IConfigurationBuilder setRowCount(int rowCount) throws IllegalArgumentException;

  /**
   * Sets the start location in the maze.
   *
   * @param column the starting column
   * @param row the starting row
   * @return the updated builder instance
   * @throws IllegalArgumentException if column or row are out of range or negative
   */
  IConfigurationBuilder setStart(int column, int row) throws IllegalArgumentException;

  /**
   * Sets the goal location in the maze.
   *
   * @param column the goal column
   * @param row the goal row
   * @return the updated builder instance
   * @throws IllegalArgumentException if column or row are out of range or negative
   */
  IConfigurationBuilder setGoal(int column, int row) throws IllegalArgumentException;

  /**
   * Sets the thief penalty on the configuration.
   *
   * @param thiefPenalty the percent of gold to steal from the player
   * @return the updated builder instance
   * @throws IllegalArgumentException if thief penalty is negative or > 1
   */
  IConfigurationBuilder setThiefPenalty(double thiefPenalty) throws IllegalArgumentException;

  /**
   * Sets the frequency of thief nodes in the maze.
   *
   * @param thiefFrequency the percent frequency of thieves in the maze
   * @return the updated builder instance
   * @throws IllegalArgumentException if thief freq is negative or > 1
   */
  IConfigurationBuilder setThiefFrequency(double thiefFrequency) throws IllegalArgumentException;

  /**
   * Sets the frequency of gold nodes in the maze.
   *
   * @param goldFrequency the percent frequency of gold in the maze
   * @return the updated builder instance
   * @throws IllegalArgumentException if gold freq is negative or > 1
   */
  IConfigurationBuilder setGoldFrequency(double goldFrequency) throws IllegalArgumentException;

  /**
   * Sets the amount of gold in a gold node.
   *
   * @param goldAmount the amount of gold
   * @return the updated builder instance
   * @throws IllegalArgumentException if goldAmount is negative
   */
  IConfigurationBuilder setGoldAmount(int goldAmount) throws IllegalArgumentException;

  /**
   * Sets the desired edge count after the maze is built.
   *
   * @param targetRoomCount the desired final edge count
   * @return the updated builder instance
   * @throws IllegalArgumentException if targetRoomCount is negative or too high
   */
  IConfigurationBuilder setTargetEdgeCount(int targetRoomCount) throws IllegalArgumentException;

  /**
   * Sets the random seed to use when generating the maze.
   *
   * @param randomSeed the random seed
   * @return the updated builder instance
   * @throws IllegalArgumentException if random seed is negative
   */
  IConfigurationBuilder setRandomSeed(int randomSeed) throws IllegalArgumentException;

  /**
   * Sets the config as a wrapping maze if true.
   *
   * @param isWrappingMaze is this maze a wrapping maze
   * @return the updated builder instance
   */
  IConfigurationBuilder setIsWrappingMaze(boolean isWrappingMaze);

  /**
   * Sets the config as a room maze if true.
   *
   * @param isRoomMaze is this maze a room maze.
   * @return the updated builder instance
   */
  IConfigurationBuilder setIsRoomMaze(boolean isRoomMaze);

  /**
   * Builds the configurations.
   *
   * @return the built maze
   */
  IConfiguration build();
}
