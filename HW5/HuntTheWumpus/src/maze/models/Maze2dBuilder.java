package maze.models;

import maze.interfaces.Builder;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Maze;

/**
 * Builder class for the Maze2d class. Contains default configs to make customizing easier.
 */
public class Maze2dBuilder implements Builder {
  private int columnCount = 10;
  private int rowCount = 10;
  private int randomSeed = 0;
  private double thiefPenalty = 0.1;
  private double thiefFrequency = 0.2;
  private double goldFrequency = 0.3;
  private int goldAmount = 10;
  private boolean isWrappingMaze = false;
  private boolean isRoomMaze = false;
  private int targetEdgeCount;
  private Coordinates start;
  private Coordinates goal;

  @Override
  public Builder setColumnCount(int columnCount) throws IllegalArgumentException {
    if (columnCount <= 0 || columnCount > 65) {
      throw new IllegalArgumentException("ColumnCount must be greater than zero but less than 65.");
    }
    this.columnCount = columnCount;
    return this;
  }

  @Override
  public Builder setRowCount(int rowCount) throws IllegalArgumentException {
    if (rowCount <= 0 || rowCount > 65) {
      throw new IllegalArgumentException("RowCount must be greater than zero but less than 65.");
    }

    this.rowCount = rowCount;
    return this;
  }

  @Override
  public Builder setStart(int column, int row) throws IllegalArgumentException {
    if (column < 0 || row < 0) {
      throw new IllegalArgumentException("Column and row index cannot be negative.");
    }
    if (column > this.columnCount - 1 || row > this.rowCount - 1) {
      throw new IllegalArgumentException("Column or row index is out of bounds.");
    }
    this.start = new MazeCoordinates(column, row);
    return this;
  }

  @Override
  public Builder setGoal(int column, int row) throws IllegalArgumentException {
    if (column < 0 || row < 0) {
      throw new IllegalArgumentException("Column and row index cannot be negative.");
    }
    if (column > this.columnCount - 1 || row > this.rowCount - 1) {
      throw new IllegalArgumentException("Column or row index is out of bounds.");
    }
    this.goal = new MazeCoordinates(column, row);
    return this;
  }

  @Override
  public Builder setThiefPenalty(double thiefPenalty) throws IllegalArgumentException {
    if (thiefPenalty < 0 || thiefPenalty > 1) {
      throw new IllegalArgumentException("ThiefPenalty must be between 0 and 1 inclusive.");
    }

    this.thiefPenalty = thiefPenalty;
    return this;
  }

  @Override
  public Builder setThiefFrequency(double thiefFrequency) throws IllegalArgumentException {
    if (thiefFrequency < 0 || thiefFrequency > 1) {
      throw new IllegalArgumentException("ThiefFrequency must be between 0 and 1 inclusive.");
    }

    this.thiefFrequency = thiefFrequency;
    return this;
  }

  @Override
  public Builder setGoldFrequency(double goldFrequency) throws IllegalArgumentException {
    if (goldFrequency < 0 || goldFrequency > 1) {
      throw new IllegalArgumentException("GoldFrequency must be between 0 and 1 inclusive.");
    }

    this.goldFrequency = goldFrequency;
    return this;
  }

  @Override
  public Builder setGoldAmount(int goldAmount) throws IllegalArgumentException {
    if (goldAmount < 0) {
      throw new IllegalArgumentException("GoldAmount must not be negative.");
    }

    this.goldAmount = goldAmount;
    return this;
  }

  @Override
  public Builder setTargetEdgeCount(int targetEdgeCount) throws IllegalArgumentException {
    if (targetEdgeCount < 0) {
      throw new IllegalArgumentException("targetEdgeCount must not be negative.");
    }
    if (targetEdgeCount > (columnCount - 1) * (rowCount - 1)) {
      throw new IllegalArgumentException(
              "targetEdgeCount cannot be greater than perfect maze edge count.");
    }

    this.targetEdgeCount = targetEdgeCount;
    return this;
  }

  @Override
  public Builder setRandomSeed(int randomSeed) throws IllegalArgumentException {
    if (randomSeed < 0) {
      throw new IllegalArgumentException("Random seed must not be negative.");
    }

    this.randomSeed = randomSeed;
    return this;
  }

  @Override
  public Builder setIsWrappingMaze(boolean isWrappingMaze) {
    this.isWrappingMaze = isWrappingMaze;
    return this;
  }

  @Override
  public Builder setIsRoomMaze(boolean isRoomMaze) {
    this.isRoomMaze = isRoomMaze;
    return this;
  }

  @Override
  public Maze build() {
    Configuration configuration = this.buildConfiguration();
    configuration = configuration.growMaze();
    return new Maze2d(
            configuration.visited()[this.start.getY()][this.start.getX()],
            configuration.visited()[this.goal.getY()][this.goal.getX()]);
  }

  @Override
  public int getColumnCount() {
    return this.columnCount;
  }

  @Override
  public int getRowCount() {
    return this.rowCount;
  }

  @Override
  public Coordinates getStart() {
    return this.start;
  }

  @Override
  public Coordinates getGoal() {
    return this.goal;
  }

  @Override
  public double getThiefPenalty() {
    return this.thiefPenalty;
  }

  @Override
  public double getThiefFrequency() {
    return this.thiefFrequency;
  }

  @Override
  public double getGoldFrequency() {
    return this.goldFrequency;
  }

  @Override
  public int getGoldAmount() {
    return this.goldAmount;
  }

  @Override
  public int getTargetEdgeCount() {
    return this.targetEdgeCount;
  }

  @Override
  public int getRandomSeed() {
    return this.randomSeed;
  }

  @Override
  public boolean getIsWrappingMaze() {
    return this.isWrappingMaze;
  }

  @Override
  public boolean getIsRoomMaze() {
    return this.isRoomMaze;
  }

  private Configuration buildConfiguration() {
    if (isRoomMaze) {
      return new RoomMazeConfiguration(
              this.columnCount,
              this.rowCount,
              this.start,
              this.goal,
              this.thiefPenalty,
              this.thiefFrequency,
              this.goldFrequency,
              this.goldAmount,
              this.isWrappingMaze,
              this.targetEdgeCount,
              this.randomSeed);
    } else {
      return new PerfectMazeConfiguration(
              this.columnCount,
              this.rowCount,
              this.start,
              this.goal,
              this.thiefPenalty,
              this.thiefFrequency,
              this.goldFrequency,
              this.goldAmount,
              this.isWrappingMaze,
              this.randomSeed);
    }
  }
}
