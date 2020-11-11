package maze.models;

import maze.interfaces.Configuration;
import maze.interfaces.ConfigurationBuilder;
import maze.interfaces.Coordinates;
import maze.interfaces.MazeBuilder;

public class MazeConfigurationBuilder implements ConfigurationBuilder {
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
  public ConfigurationBuilder setColumnCount(int columnCount) throws IllegalArgumentException {
    if (columnCount <= 0 || columnCount > 65) {
      throw new IllegalArgumentException("ColumnCount must be greater than zero but less than 65.");
    }
    this.columnCount = columnCount;
    return this;
  }

  @Override
  public ConfigurationBuilder setRowCount(int rowCount) throws IllegalArgumentException {
    if (rowCount <= 0 || rowCount > 65) {
      throw new IllegalArgumentException("RowCount must be greater than zero but less than 65.");
    }

    this.rowCount = rowCount;
    return this;
  }

  @Override
  public ConfigurationBuilder setStart(int column, int row) throws IllegalArgumentException {
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
  public ConfigurationBuilder setGoal(int column, int row) throws IllegalArgumentException {
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
  public ConfigurationBuilder setThiefPenalty(double thiefPenalty) throws IllegalArgumentException {
    if (thiefPenalty < 0 || thiefPenalty > 1) {
      throw new IllegalArgumentException("ThiefPenalty must be between 0 and 1 inclusive.");
    }

    this.thiefPenalty = thiefPenalty;
    return this;
  }

  @Override
  public ConfigurationBuilder setThiefFrequency(double thiefFrequency) throws IllegalArgumentException {
    if (thiefFrequency < 0 || thiefFrequency > 1) {
      throw new IllegalArgumentException("ThiefFrequency must be between 0 and 1 inclusive.");
    }

    this.thiefFrequency = thiefFrequency;
    return this;
  }

  @Override
  public ConfigurationBuilder setGoldFrequency(double goldFrequency) throws IllegalArgumentException {
    if (goldFrequency < 0 || goldFrequency > 1) {
      throw new IllegalArgumentException("GoldFrequency must be between 0 and 1 inclusive.");
    }

    this.goldFrequency = goldFrequency;
    return this;
  }

  @Override
  public ConfigurationBuilder setGoldAmount(int goldAmount) throws IllegalArgumentException {
    if (goldAmount < 0) {
      throw new IllegalArgumentException("GoldAmount must not be negative.");
    }

    this.goldAmount = goldAmount;
    return this;
  }

  @Override
  public ConfigurationBuilder setTargetEdgeCount(int targetEdgeCount) throws IllegalArgumentException {
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
  public ConfigurationBuilder setRandomSeed(int randomSeed) throws IllegalArgumentException {
    if (randomSeed < 0) {
      throw new IllegalArgumentException("Random seed must not be negative.");
    }

    this.randomSeed = randomSeed;
    return this;
  }

  @Override
  public ConfigurationBuilder setIsWrappingMaze(boolean isWrappingMaze) {
    this.isWrappingMaze = isWrappingMaze;
    return this;
  }

  @Override
  public ConfigurationBuilder setIsRoomMaze(boolean isRoomMaze) {
    this.isRoomMaze = isRoomMaze;
    return this;
  }

  @Override
  public Configuration build() {
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
