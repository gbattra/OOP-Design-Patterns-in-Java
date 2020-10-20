package maze.helpers;

import maze.enums.Direction;
import maze.interfaces.Coordinates;

/**
 * Helper class to determine directions relative to one another.
 */
public final class DirectionHelper {
  /**
   * Returns the direction opposite the one provided.
   *
   * @param direction the reference direction
   * @return the opposite direction
   */
  public static Direction oppositeOf(Direction direction) {
    switch (direction) {
      case NORTH -> {
        return Direction.SOUTH;
      }
      case SOUTH -> {
        return Direction.NORTH;
      }
      case EAST -> {
        return Direction.WEST;
      }
      case WEST -> {
        return Direction.EAST;
      }
      default -> throw new IllegalArgumentException("Invalid direction provided.");
    }
  }
}
