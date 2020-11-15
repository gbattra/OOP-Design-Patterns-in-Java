package maze.utils;

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

  /**
   * Gets the direction given a character.
   *
   * @param input the character to convert to Direction
   * @return the corresponding direction
   */
  public static Direction stringToDirection(String input) {
    if (input.toLowerCase().equals("n") || input.toLowerCase().equals("north")) {
      return Direction.NORTH;
    }
    if (input.toLowerCase().equals("s") || input.toLowerCase().equals("south")) {
      return Direction.SOUTH;
    }
    if (input.toLowerCase().equals("e") || input.toLowerCase().equals("east")) {
      return Direction.EAST;
    }
    if (input.toLowerCase().equals("w") || input.toLowerCase().equals("west")) {
      return Direction.WEST;
    }
    throw new IllegalArgumentException("Input direction not valid.");
  }
}
