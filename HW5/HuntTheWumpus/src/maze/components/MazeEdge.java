package maze.components;

import maze.utils.Direction;

/**
 * Concrete representation of a maze edge (or wall). Takes a tail node, head node and their
 * respective directions relative to the center of the wall.
 *
 */
public class MazeEdge implements Edge {
  private final Coordinates tail;
  private final Coordinates head;
  private final Direction tailDirection;
  private final Direction headDirection;

  /**
   * Standard constructor for a MazeEdge.
   *
   * @param tail the tail node
   * @param head the head node
   * @param tailDirection the tail direction
   * @param headDirection the head direction
   */
  public MazeEdge(
          Coordinates tail,
          Coordinates head,
          Direction tailDirection,
          Direction headDirection) {
    this.tail = tail;
    this.head = head;
    this.tailDirection = tailDirection;
    this.headDirection = headDirection;
  }

  @Override
  public Coordinates getTail() {
    return this.tail;
  }

  @Override
  public Coordinates getHead() {
    return this.head;
  }

  @Override
  public Direction getTailDirection() {
    return this.tailDirection;
  }

  @Override
  public Direction getHeadDirection() {
    return this.headDirection;
  }

  @Override
  public String toString() {
    return String.format("%s | %s", this.tail.toString(), this.head.toString());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Edge) {
      Edge o = (Edge) obj;
      return (o.getTail().equals(this.getTail()) || o.getTail().equals(this.getHead()))
              && (o.getHead().equals(this.getHead()) || o.getHead().equals(this.getTail()));
    }

    return false;
  }
}
