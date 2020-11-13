package maze.components.nodes;

import maze.components.ICoordinates;
import maze.components.IPath;
import maze.utils.Direction;

/**
 * A node representing a space in the maze. Either a dead end or a room.
 */
public interface Node {
  /**
   * Gets the node at the specified coordinates.
   *
   * @param coordinates the coordinates at which the desired node resides
   * @return the queried node
   */
  Node get(ICoordinates coordinates);

  /**
   * Helper method for getting a node at a coordinate set.
   *
   * @param path the path object tracking the search through the maze
   * @return the queried node
   */
  Node getHelper(IPath path);

  /**
   * Getter for the node's gold count.
   *
   * @return how much gold is in the node.
   */
  int getGoldCount();

  /**
   * Is this node the goal to the maze.
   *
   * @return true if maze goal, false otherwise
   */
  boolean isGoal();

  /**
   * How much gold is stolen when a player enters this node.
   *
   * @return the percentage to decrement the player's gold.
   */
  double getThiefPenalty();

  /**
   * Getter for the node's coordinates.
   *
   * @return the node's coordinates
   */
  ICoordinates getCoordinates();

  /**
   * Sets the provided node at the direction indicated. I.e. setNode(node, NORTH) sets this node's
   * north node.
   *
   * @param node the node to set
   * @param dir the direction to set it
   * @throws IllegalStateException if accessed in an dead end node
   * @throws IllegalArgumentException if direction is invalid
   */
  void setNode(Node node, Direction dir) throws IllegalStateException, IllegalArgumentException;

  /**
   * Get the node at the specified direction.
   *
   * @param dir the direction to query
   * @return the node at that direction
   */
  Node getNode(Direction dir);

  /**
   * Determines if target coordinates can be reached from the cell.
   *
   * @param coordinates the target coordinates
   * @return true if can reach target, false otherwise
   * @throws IllegalArgumentException if coordinates are out of bounds
   */
  boolean canReach(ICoordinates coordinates) throws IllegalArgumentException;

  /**
   * Helper method for determining if target coordinates can be reached from the cell.
   *
   * @return true if can reach target, false otherwise
   * @throws IllegalArgumentException if coordinates are out of bounds
   */
  boolean canReachHelper(IPath path);

  /**
   * Traverses the maze to find the path to desired coordinates which yields the highest gold count.
   *
   * @param coordinates the target coordinates
   * @return the path taken through the maze
   */
  IPath wealthiestPathTo(ICoordinates coordinates);

  /**
   * Helper method for finding the wealthiest path through the maze.
   *
   * @param path the path object being built
   * @return the path taken through the maze
   */
  IPath wealthiestPathToHelper(IPath path);

  /**
   * Traverses the entire maze, reaching every room within the maze and returning the path
   * taken to get to each room.
   *
   * @return the path taken through the maze
   */
  IPath exploreTo(ICoordinates coordinates);

  /**
   * Helper method for explore() which builds a path object as it traverses the maze.
   *
   * @param path the path object used to track visited nodes
   * @return the path taken through the maze
   */
  IPath exploreHelper(IPath path);

  /**
   * Traverses the maze to find a path reaching the specified coordinates.
   *
   * @param coordinates the target coordinates
   * @return the path taken through the maze
   */
  IPath pathTo(ICoordinates coordinates);

  /**
   * Helper method for pathTo() which builds a path object as it traverses the maze.
   *
   * @param path the path object used to track visited tiles
   * @return the path taken through the maze
   */
  IPath pathToHelper(IPath path);

  /**
   * Room adds to gold if GoldRoom, else if ThiefRoom steals some gold, else adds nothing.
   *
   * @param gold the gold to add to or steal from
   * @return the updated gold count
   */
  int loot(int gold);
}
