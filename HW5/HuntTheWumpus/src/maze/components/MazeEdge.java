package maze.components;

import maze.utils.Direction;

/**
 * Concrete representation of a maze edge (or wall). Takes a tail node, head node and their
 * respective directions relative to the center of the wall.
 *
 */
public class MazeEdge implements IEdge {
  private final ICoordinates tail;
  private final ICoordinates head;
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
          ICoordinates tail,
          ICoordinates head,
          Direction tailDirection,
          Direction headDirection) {
    this.tail = tail;
    this.head = head;
    this.tailDirection = tailDirection;
    this.headDirection = headDirection;
  }

  @Override
  public ICoordinates getTail() {
    return this.tail;
  }

  @Override
  public ICoordinates getHead() {
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

    if (obj instanceof IEdge) {
      IEdge o = (IEdge) obj;
      return (o.getTail().equals(this.getTail()) || o.getTail().equals(this.getHead()))
              && (o.getHead().equals(this.getHead()) || o.getHead().equals(this.getTail()));
    }

    return false;
  }
}
