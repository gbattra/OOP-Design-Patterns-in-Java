package htw.tools;

import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.config.AbstractMazeConfiguration;

public class HtwConfiguration extends AbstractMazeConfiguration implements IHtwConfiguration {
  private final double pitFrequency;
  private final double batFrequency;

  public HtwConfiguration(
          int rowCount,
          int columnCount,
          ICoordinates start,
          double pitFrequency,
          double batFrequency,
          boolean isRoomMaze,
          boolean isWrappingMaze,
          int targetExitCount,
          int randomSeed) throws IllegalArgumentException {
    super(rowCount,
            columnCount,
            start,
            new Coordinates(columnCount - 1, rowCount - 1),
            0, 0, 0, 0,
            isWrappingMaze,
            isRoomMaze,
            targetExitCount,
            randomSeed);
    if (pitFrequency < 0 || batFrequency < 0) {
      throw new IllegalArgumentException("Pit and bat frequency must be non-negative.");
    }
    this.pitFrequency = pitFrequency;
    this.batFrequency = batFrequency;
  }

  @Override
  public double getBatFrequency() {
    return this.batFrequency;
  }

  @Override
  public double getPitFrequency() {
    return this.pitFrequency;
  }
}
