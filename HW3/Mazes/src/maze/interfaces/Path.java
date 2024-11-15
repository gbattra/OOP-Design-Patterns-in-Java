package maze.interfaces;

import java.util.List;

/**
 * Represents a possible path through a 2D maze. Tallies gold collected along the way, indicates
 * whether the path reaches its specified target, and holds the list of coordinates taken.
 */
public interface Path {
  /**
   * Does this path reach its target.
   *
   * @return true if target reached by path
   */
  boolean reachesTarget();

  /**
   * The total gold collected by this path.
   *
   * @return int sum of gold collected
   */
  int totalGold();

  /**
   * Returns this path's target coordinates.
   *
   * @return the target coordinates
   */
  Coordinates getTarget();

  /**
   * Getter for the list of coordinates traversed by this path.
   *
   * @return the list of coordinates
   */
  List<Coordinates> getCoordinatesTraversed();

  /**
   * Adds a coordinate pair to their respective lists.
   *
   * @param coordinates the coordinates traversed
   * @return the path with updated directions
   */
  Path addCoordinates(Coordinates coordinates);

  /**
   * Loots the provided node (either adds gold or gets robbed).
   *
   * @param node the node to add
   * @return a new updated Path instance
   */
  Path loot(Node node);

  /**
   * Enters the target node.
   *
   * @param node the target node
   */
  Path enter(Node node);

  /**
   * Use to set reachesTarget property on path object.
   *
   * @param doesReach true / false does this path reach the target
   * @return a new updated Path instance
   */
  Path setReachesTarget(boolean doesReach);
}
