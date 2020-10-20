package maze.models;

import maze.interfaces.Coordinates;

public class StandardRoomNode extends AbstractRoomNode {
  /**
   * First constructor for RoomNode. Use when room contains gold for the player.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @throws IllegalArgumentException when goldCount is negative
   */
  public StandardRoomNode(Coordinates coordinates) throws IllegalArgumentException {
    super(coordinates, 0, 0.0);
  }

  /**
   * Second constructor for StandardRoomNode. Use to set isExit true.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param isGoal true if this is the goal node
   * @throws IllegalArgumentException when goldCount is negative
   */
  public StandardRoomNode(
          Coordinates coordinates,
          boolean isGoal) throws IllegalArgumentException {
    super(coordinates, 0, 0.0, isGoal);
  }

  @Override
  public int loot(int gold) {
    return gold;
  }

  @Override
  public boolean isGoldRoom() {
    return false;
  }

  @Override
  public boolean isThiefRoom() {
    return false;
  }
}
