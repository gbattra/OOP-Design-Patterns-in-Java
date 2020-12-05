package maze;

/**
 * Represents an edge in a maze (i.e. a wall or bridge between two coordinates).
 */
public interface IEdge {
  /**
   * Getter for the coordinates on one side of the edge.
   *
   * @return the coordinates on one side of the edge
   */
  ICoordinates getTail();

  /**
   * Getter for the coordinates on the other side of the edge.
   *
   * @return the coordinates on the other side of the edge
   */
  ICoordinates getHead();

  /**
   * Gets the edge direction to the tail coordinates.
   *
   * @return the edge direction
   */
  Direction getTailDirection();

  /**
   * Gets the edge direction to the head coordinates.
   *
   * @return the edge direction
   */
  Direction getHeadDirection();
}
