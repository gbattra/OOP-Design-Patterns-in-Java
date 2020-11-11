package maze.config;

import java.util.Random;

import maze.components.ICoordinates;

/**
 * Abstract class holding basic configuration getter methods as well as some helper methods
 * for creating the start node and generating nodes as the maze is built.
 */
public abstract class AbstractMazeConfiguration implements IConfiguration {
  protected final int columnCount;
  protected final int rowCount;
  protected final ICoordinates start;
  protected final ICoordinates goal;
  protected final int randomSeed;
  protected final Random random;
  protected final double thiefPenalty;
  protected final double thiefFrequency;
  protected final double goldFrequency;
  protected final int goldAmount;
  protected final boolean isWrappingMaze;
  protected final boolean isRoomMaze;
  protected final int perfectExitCount;
  protected final int targetEdgeCount;

  /**
   * Constructor for the AbstractMazeConfiguration.
   *
   * @param rowCount the row count of the maze
   * @param columnCount the column count of the maze
   * @param start the start coordinates of the maze
   * @param goal the goal coordinates of the maze
   * @param thiefPenalty the thief penalty for a thief node
   * @param thiefFrequency the frequency of thief nodes in the maze
   * @param goldFrequency the frequency of gold nodes in the maze
   * @param goldAmount the amount of gold in a gold node
   * @param isWrappingMaze is this maze a wrapping maze?
   * @param isRoomMaze is this maze a room maze?
   * @param targetEdgeCount the desired target edge count after the maze is built
   * @param randomSeed the random seed to use when building the maze
   * @throws IllegalArgumentException when values are negative or out of bounds
   */
  public AbstractMazeConfiguration(
          int rowCount,
          int columnCount,
          ICoordinates start,
          ICoordinates goal,
          double thiefPenalty,
          double thiefFrequency,
          double goldFrequency,
          int goldAmount,
          boolean isWrappingMaze,
          boolean isRoomMaze,
          int targetEdgeCount,
          int randomSeed) throws IllegalArgumentException {
    if (goldAmount < 0 || targetEdgeCount < 0) {
      throw new IllegalArgumentException(
              "goldAmount and targetEdgeCount must not be negative.");
    }

    if (thiefPenalty < 0 || thiefPenalty > 1
        || thiefFrequency < 0 || thiefFrequency > 1
        || goldFrequency < 0 || goldFrequency > 1) {
      throw new IllegalArgumentException(
              "thiefPenalty, thiefFrequency, and goldFrequency must be between 0 and 1 inclusive.");
    }

    if (columnCount <= 0 || rowCount <= 0) {
      throw new IllegalArgumentException(
              "columnCount and rowCount must be greater than zero.");
    }


    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.start = start;
    this.goal = goal;
    this.thiefPenalty = thiefPenalty;
    this.thiefFrequency = thiefFrequency;
    this.goldFrequency = goldFrequency;
    this.goldAmount = goldAmount;
    this.perfectExitCount = (rowCount * columnCount) - 1;
    this.targetEdgeCount = targetEdgeCount;
    this.isRoomMaze = isRoomMaze;
    this.isWrappingMaze = isWrappingMaze;
    this.randomSeed = randomSeed;
    this.random = randomSeed > 0 ? new Random(randomSeed) : new Random();
  }

  @Override
  public int columnCount() {
    return this.columnCount;
  }

  @Override
  public int rowCount() {
    return this.rowCount;
  }

  @Override
  public double thiefPenalty() {
    return this.thiefPenalty;
  }

  @Override
  public double thiefFrequency() {
    return this.thiefFrequency;
  }

  @Override
  public double goldFrequency() {
    return this.goldFrequency;
  }

  @Override
  public int goldAmount() {
    return this.goldAmount;
  }

  @Override
  public Random random() {
    return this.random;
  }

  @Override
  public int randomSeed() {
    return this.randomSeed;
  }

  @Override
  public int targetEdgeCount() {
    return this.targetEdgeCount;
  }

  @Override
  public boolean isRoomMaze() {
    return this.isRoomMaze;
  }

  @Override
  public boolean isWrappingMaze() {
    return this.isWrappingMaze;
  }

  @Override
  public ICoordinates start() {
    return this.start;
  }

  @Override
  public ICoordinates goal() {
    return this.goal;
  }

  @Override
  public int perfectExitCount() {
    return this.perfectExitCount;
  }
}
