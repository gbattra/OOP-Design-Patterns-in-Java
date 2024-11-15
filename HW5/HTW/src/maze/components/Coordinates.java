package maze.components;

/**
 * Concrete representation of a coordinate set.
 */
public class Coordinates implements ICoordinates {
  private final int x;
  private final int y;

  /**
   * MazeCoordinate constructor. Takes X and Y coordinates.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @throws IllegalArgumentException if x or y is negative
   */
  public Coordinates(int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("X and Y coordinates cannot be negative.");
    }

    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", this.x, this.y);
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other instanceof ICoordinates) {
      ICoordinates o = (ICoordinates) other;
      return this.x == o.getX() && this.y == o.getY();
    }

    return false;
  }
}
