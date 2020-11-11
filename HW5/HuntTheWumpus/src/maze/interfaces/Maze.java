package maze.interfaces;

import maze.enums.Direction;

/**
 * Represents a maze object. Mazes have a starting point and an exit, as well as a current node
 * at which the navigator/player is currently located.
 */
public interface Maze {
  /**
   * Gets the starting node for the maze.
   *
   * @return the start node
   */
  Node getStart();

  /**
   * Gets the current node in the maze where the player is located.
   *
   * @return the current node
   */
  Node getCurrent();

  /**
   * Gets the exit of the maze.
   *
   * @return the goal node
   */
  Node getGoal();

  /**
   * Moves in the given direction and sets the current node to the node at that direction.
   *
   * @param dir the direction to move
   * @return true if move was made, false if move cannot be made (i.e. dead end node at dir)
   */
  boolean move(Direction dir);

  /**
   * Traverses the maze to find the path to desired coordinates which yields the highest gold count.
   *
   * @param coordinates the target coordinates
   * @return the path taken through the maze
   */
  Path wealthiestPathTo(Coordinates coordinates);

  /**
   * Traverses the maze to find a path reaching the specified coordinates.
   *
   * @param coordinates the target coordinates
   * @return the path taken through the maze
   */
  Path pathTo(Coordinates coordinates);

  /**
   * Traverses the entire maze, reaching every room within the maze and returning the path
   * taken to get to each room.
   *
   * @param coordinates the target coordinates
   * @return the path taken through the maze
   */
  Path exploreTo(Coordinates coordinates);

  /**
   * Gets the node at the specified coordinates.
   *
   * @param coordinates the coordinates at which the desired node resides
   * @return the queried node
   */
  Node get(Coordinates coordinates);

  /**
   * Determines if target coordinates can be reached from the cell.
   *
   * @param coordinates the target coordinates
   * @return true if can reach target, false otherwise
   * @throws IllegalArgumentException if coordinates are out of bounds
   */
  boolean canReach(Coordinates coordinates) throws IllegalArgumentException;
}
