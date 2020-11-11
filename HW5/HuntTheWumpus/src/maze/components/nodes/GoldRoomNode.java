package maze.components.nodes;

import maze.components.ICoordinates;

/**
 * Maze node which has gold for the player to pick up.
 */
public class GoldRoomNode extends AbstractRoomNode {
  /**
   * First constructor for GoldRoomNode. Use when room contains gold for the player.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param goldCount the amount of gold for the player
   * @throws IllegalArgumentException when goldCount is negative
   */
  public GoldRoomNode(
          ICoordinates coordinates,
          int goldCount) throws IllegalArgumentException {
    super(coordinates, goldCount, 0.0);
  }

  /**
   * Second constructor for GoldRoomNode. Use to set isExit true.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param goldCount the amount of gold for the player
   * @param isGoal true if this is the goal node
   * @throws IllegalArgumentException when goldCount is negative
   */
  public GoldRoomNode(
          ICoordinates coordinates,
          int goldCount,
          boolean isGoal) throws IllegalArgumentException {
    super(coordinates, goldCount, 0.0, isGoal);
  }

  @Override
  public int loot(int gold) {
    return gold + this.getGoldCount();
  }
}
