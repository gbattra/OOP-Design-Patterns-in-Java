package maze.interfaces;

/**
 * Builder class to help setup a maze. It is recommended to use this class to build a maze.
 */
public interface Builder {
  /**
   * Sets the number of columns in the maze.
   *
   * @param columnCount the column count
   * @return the updated builder instance
   * @throws IllegalArgumentException if column count is negative or too big
   */
  Builder setColumnCount(int columnCount) throws IllegalArgumentException;

  /**
   * Sets the number of rows in the maze.
   *
   * @param rowCount the row count
   * @return the updated builder instance
   * @throws IllegalArgumentException if row count is negative or too big
   */
  Builder setRowCount(int rowCount) throws IllegalArgumentException;

  /**
   * Sets the start location in the maze.
   *
   * @param column the starting column
   * @param row the starting row
   * @return the updated builder instance
   * @throws IllegalArgumentException if column or row are out of range or negative
   */
  Builder setStart(int column, int row) throws IllegalArgumentException;

  /**
   * Sets the goal location in the maze.
   *
   * @param column the goal column
   * @param row the goal row
   * @return the updated builder instance
   * @throws IllegalArgumentException if column or row are out of range or negative
   */
  Builder setGoal(int column, int row) throws IllegalArgumentException;

  /**
   * Sets the thief penalty on the configuration.
   *
   * @param thiefPenalty the percent of gold to steal from the player
   * @return the updated builder instance
   * @throws IllegalArgumentException if thief penalty is negative or > 1
   */
  Builder setThiefPenalty(double thiefPenalty) throws IllegalArgumentException;

  /**
   * Sets the frequency of thief nodes in the maze.
   *
   * @param thiefFrequency the percent frequency of thieves in the maze
   * @return the updated builder instance
   * @throws IllegalArgumentException if thief freq is negative or > 1
   */
  Builder setThiefFrequency(double thiefFrequency) throws IllegalArgumentException;

  /**
   * Sets the frequency of gold nodes in the maze.
   *
   * @param goldFrequency the percent frequency of gold in the maze
   * @return the updated builder instance
   * @throws IllegalArgumentException if gold freq is negative or > 1
   */
  Builder setGoldFrequency(double goldFrequency) throws IllegalArgumentException;

  /**
   * Sets the amount of gold in a gold node.
   *
   * @param goldAmount the amount of gold
   * @return the updated builder instance
   * @throws IllegalArgumentException if goldAmount is negative
   */
  Builder setGoldAmount(int goldAmount) throws IllegalArgumentException;

  /**
   * Sets the desired edge count after the maze is built.
   *
   * @param targetRoomCount the desired final edge count
   * @return the updated builder instance
   * @throws IllegalArgumentException if targetRoomCount is negative or too high
   */
  Builder setTargetEdgeCount(int targetRoomCount) throws IllegalArgumentException;

  /**
   * Sets the random seed to use when generating the maze.
   *
   * @param randomSeed the random seed
   * @return the updated builder instance
   * @throws IllegalArgumentException if random seed is negative
   */
  Builder setRandomSeed(int randomSeed) throws IllegalArgumentException;

  /**
   * Sets the config as a wrapping maze if true.
   *
   * @param isWrappingMaze is this maze a wrapping maze
   * @return the updated builder instance
   */
  Builder setIsWrappingMaze(boolean isWrappingMaze);

  /**
   * Sets the config as a room maze if true.
   *
   * @param isRoomMaze is this maze a room maze.
   * @return the updated builder instance
   */
  Builder setIsRoomMaze(boolean isRoomMaze);

  /**
   * Getter for the columnCount.
   *
   * @return the columnCount
   */
  int getColumnCount();

  /**
   * Getter for the RowCount.
   *
   * @return the RowCount
   */
  int getRowCount();

  /**
   * Getter for the Start.
   *
   * @return the Start
   */
  Coordinates getStart();

  /**
   * Getter for the Goal.
   *
   * @return the Goal
   */
  Coordinates getGoal();

  /**
   * Getter for the ThiefPenalty.
   *
   * @return the ThiefPenalty
   */
  double getThiefPenalty();

  /**
   * Getter for the ThiefFrequency.
   *
   * @return the ThiefFrequency
   */
  double getThiefFrequency();

  /**
   * Getter for the GoldFrequency.
   *
   * @return the GoldFrequency
   */
  double getGoldFrequency();

  /**
   * Getter for the GoldAmount.
   *
   * @return the GoldAmount
   */
  int getGoldAmount();

  /**
   * Getter for the TargetEdgeCount.
   *
   * @return the TargetEdgeCount
   */
  int getTargetEdgeCount();

  /**
   * Getter for the RandomSeed.
   *
   * @return the RandomSeed
   */
  int getRandomSeed();

  /**
   * Getter for the IsWrappingMaze.
   *
   * @return the IsWrappingMaze
   */
  boolean getIsWrappingMaze();

  /**
   * Getter for the IsRoomMaze.
   *
   * @return the IsRoomMaze
   */
  boolean getIsRoomMaze();

  /**
   * Builds the maze using the setup configurations.
   *
   * @return the built maze
   */
  Maze build();
}
