package maze.models;

import maze.interfaces.Coordinates;

/**
 * Configuration object for a perfect maze. Minimizes configuration constructor to prevent a bad
 * configuration state. (i.e. doesn't not allow user to specify 'targetEdgeCount' as that is
 * not modifiable for a perfect maze).
 */
public class PerfectMazeConfiguration extends AbstractMazeConfiguration {
  /**
   * Standard constructor for the perfect maze config.
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
  public PerfectMazeConfiguration(
          int rowCount,
          int columnCount,
          Coordinates start,
          Coordinates goal,
          double thiefPenalty,
          double thiefFrequency,
          double goldFrequency,
          int goldAmount,
          boolean isWrappingMaze,
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
          false,
          (rowCount - 1) * (columnCount - 1),
          randomSeed);
  }
}
