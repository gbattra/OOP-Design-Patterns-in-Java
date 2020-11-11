package maze.interfaces;

import java.util.List;

import maze.enums.Direction;

/**
 * Builder class to help setup a maze. It is recommended to use this class to build a maze.
 */
public interface MazeBuilder {
  /**
   * Builds the maze using the setup configurations.
   *
   * @return the built maze
   */
  Maze build();

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
   * Adds an edge at the specified coordinates.
   *
   * @param one the first coordinate
   * @param two the second coordinate
   * @param tail the edge direction respective to the tail
   * @param head the edge direction respective to the head
   */
  void addEdge(Coordinates one, Coordinates two, Direction tail, Direction head);

  /**
   * Computes the perfect room exit count and checks if existing room exit count is equal.
   *
   * @return true if maze has perfect exit count
   */
  boolean isPerfect();
}
