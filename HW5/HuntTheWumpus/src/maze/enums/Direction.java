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
   * @return the opposite direction
   */
  public Direction opposite() {
    if (this == Direction.NORTH) {
      return Direction.SOUTH;
    }
    if (this == Direction.SOUTH) {
      return Direction.NORTH;
    }
    if (this == Direction.EAST) {
      return Direction.WEST;
    }
    if (this == Direction.WEST) {
      return Direction.EAST;
    }
    throw new IllegalArgumentException("Invalid direction provided.");
  }
}
