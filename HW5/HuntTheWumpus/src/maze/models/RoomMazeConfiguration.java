package maze.models;

import maze.interfaces.Coordinates;

/**
 * Configuration object for a room maze.
 */
public class RoomMazeConfiguration extends AbstractMazeConfiguration {
  /**
   * Standard constructor for the room maze configuration.
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
   * @param randomSeed the random seed to use when building the maze
   */
  public RoomMazeConfiguration(
          int rowCount,
          int columnCount,
          Coordinates start,
          Coordinates goal,
          double thiefPenalty,
          double thiefFrequency,
          double goldFrequency,
          int goldAmount,
          boolean isWrappingMaze,
          int targetExitCount,
          int randomSeed) {
    super(rowCount,
          columnCount,
          start,
          goal,
          thiefPenalty,
          thiefFrequency,
          goldFrequency,
          goldAmount,
          isWrappingMaze,
          true,
          targetExitCount,
          randomSeed);
  }
}
