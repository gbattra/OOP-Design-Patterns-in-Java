package maze.interfaces;

import java.util.List;
import java.util.Random;

import maze.enums.Direction;

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
   * Getter for the maze start coordinates.
   *
   * @return the start coordinates
   */
  Coordinates getStartCoordinates();

  /**
   * Getter for the maze goal coordinates.
   *
   * @return the goal coordinates
   */
  Coordinates getGoalCoordinates();

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
   * Returns the current number of room exits in the maze.
   *
   * @return the exit count
   */
  int exitCount();

  /**
   * Is this a room maze.
   *
   * @return true if room maze
   */
  boolean isRoomMaze();

  /**
   * Computes the perfect room exit count and checks if existing room exit count is equal.
   *
   * @return true if maze has perfect exit count
   */
  boolean isPerfect();

  /**
   * Is this a wrapping maze.
   *
   * @return wrapping maze if true
   */
  boolean isWrappingMaze();

  /**
   * Keeps track of which coordinates have been visited when building the maze.
   * Indices are [rowCount][columnCount].
   *
   * @return the list tracking if a coordinate set has been visited
   */
  Node[][] visited();

  /**
   * Keeps track of the edges produced when building the maze.
   *
   * @return the list of edges produced
   */
  List<Edge> edges();

  /**
   * Sets the node in the visited 2D array.
   *
   * @param node the node to set
   */
  void addVisited(Node node);

  /**
   * Generates a node for the specified coordinates using the maze configs.
   *
   * @param coordinates the coordinates at which the node will reside.
   * @return the created node
   */
  Node generateRoom(Coordinates coordinates);

  /**
   * Generates the starting node for the maze.
   *
   * @return the starting node
   */
  Node generateStart();

  /**
   * Adds an edge at the specified coordinates.
   *
   * @param one the first coordinate
   * @param two the second coordinate
   * @param tail the edge direction respective to the tail
   * @param head the edge direction respective to the head
   */
  void addEdge(Coordinates one, Coordinates two, Direction tail, Direction head);

  /**
   * Grows the maze using the configuration.
   *
   * @return the updated configuration
   */
  Configuration growMaze();
}
