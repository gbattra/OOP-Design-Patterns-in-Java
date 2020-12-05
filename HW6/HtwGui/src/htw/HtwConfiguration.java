package htw;

import maze.Coordinates;
import maze.ICoordinates;
import maze.AbstractMazeConfiguration;

/**
 * Configuration object used by the maze builder when building the maze.
 */
public class HtwConfiguration extends AbstractMazeConfiguration implements IHtwConfiguration {
  private final double pitFrequency;
  private final double batFrequency;
  private final int numPlayers;
  private final int arrowCount;
  private final Appendable logger;

  /**
   * Main constructor for the maze configuration.
   *
   * @param rowCount how many rows in the maze
   * @param columnCount how many columns in the maze
   * @param start what is the starting coordinates
   * @param pitFrequency how frequently should a cave have a pit
   * @param batFrequency how frequently should a cave have bats
   * @param isRoomMaze is the maze a room maze
   * @param isWrappingMaze is the maze wrapping
   * @param targetEdgeCount the final number of edges in the maze
   * @param randomSeed to control how a maze is built
   * @param logger the logger for the game
   * @param arrowCount number of arrows to start with
   * @param numPlayers number of players in the game
   * @throws IllegalArgumentException if bat frequency or pit frequency is negative
   */
  public HtwConfiguration(
          int rowCount,
          int columnCount,
          ICoordinates start,
          double pitFrequency,
          double batFrequency,
          boolean isRoomMaze,
          boolean isWrappingMaze,
          int targetEdgeCount,
          int randomSeed,
          Appendable logger,
          int arrowCount,
          int numPlayers) throws IllegalArgumentException {
    super(rowCount,
            columnCount,
            start,
            new Coordinates(columnCount - 1, rowCount - 1),
            0, 0, 0, 0,
            isWrappingMaze,
            isRoomMaze,
            targetEdgeCount,
            randomSeed);
    if (pitFrequency < 0 || batFrequency < 0) {
      throw new IllegalArgumentException("Pit and bat frequency must be non-negative.");
    }
    this.pitFrequency = pitFrequency;
    this.batFrequency = batFrequency;
    this.logger = logger;
    this.arrowCount = arrowCount;
    this.numPlayers = numPlayers;
  }

  @Override
  public int numPlayers() {
    return this.numPlayers;
  }

  @Override
  public int arrowCount() {
    return this.arrowCount;
  }

  @Override
  public Appendable getLogger() {
    return this.logger;
  }

  @Override
  public double batFrequency() {
    return this.batFrequency;
  }

  @Override
  public double pitFrequency() {
    return this.pitFrequency;
  }
}
