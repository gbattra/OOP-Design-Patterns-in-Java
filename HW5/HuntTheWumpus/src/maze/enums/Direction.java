package maze.enums;

/**
 * Enum capturing the various directions at which a node may be connected to another node.
 */
public enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST;

  /**
   * Returns the direction opposite the one provided.
   *
   * @param direction the reference direction
   * @return the opposite direction
   */
  public static Direction oppositeOf(Direction direction) {
    if (direction == Direction.NORTH) {
      return Direction.SOUTH;
    }
    if (direction == Direction.SOUTH) {
      return Direction.NORTH;
    }
    if (direction == Direction.EAST) {
      return Direction.WEST;
    }
    if (direction == Direction.WEST) {
      return Direction.EAST;
    }
    throw new IllegalArgumentException("Invalid direction provided.");
  }
}
