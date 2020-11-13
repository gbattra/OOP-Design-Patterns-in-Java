package maze;

import java.util.List;

import maze.components.ICoordinates;
import maze.components.IEdge;
import maze.components.IMaze;
import maze.components.nodes.Node;
import maze.config.IConfiguration;
import maze.utils.Direction;

/**
 * Builder class to help setup a maze. It is recommended to use this class to build a maze.
 */
public interface IMazeBuilder {
  /**
   * Builds the maze using the setup configurations.
   *
   * @return the built maze
   */
  IMaze build();

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
  List<IEdge> edges();

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
  Node generateRoom(ICoordinates coordinates);

  /**
   * Adds an edge at the specified coordinates.
   *
   * @param one the first coordinate
   * @param two the second coordinate
   * @param tail the edge direction respective to the tail
   * @param head the edge direction respective to the head
   */
  void addEdge(ICoordinates one, ICoordinates two, Direction tail, Direction head);

  /**
   * Computes the perfect room exit count and checks if existing room exit count is equal.
   *
   * @return true if maze has perfect exit count
   */
  boolean isPerfect();
}
