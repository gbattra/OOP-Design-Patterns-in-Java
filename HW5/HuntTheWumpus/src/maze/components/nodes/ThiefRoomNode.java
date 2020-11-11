package maze.components.nodes;

import maze.components.Coordinates;

/**
 * Maze node which contains a thief that steals a percent of the player's gold if they enter.
 */
public class ThiefRoomNode extends AbstractRoomNode {
  /**
   * First constructor for ThiefRoomNode. Use when room contains gold for the player.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param thiefPenalty what percentage of gold should be stolen by thief
   * @throws IllegalArgumentException when goldCount is negative
   */
  public ThiefRoomNode(
          Coordinates coordinates,
          double thiefPenalty) throws IllegalArgumentException {
    super(coordinates, 0, thiefPenalty);
  }

  /**
   * Second constructor for ThiefRoomNode. Use to set isExit true.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param thiefPenalty what percentage of gold should be stolen by thief
   * @param isGoal true if this is the goal node
   * @throws IllegalArgumentException when goldCount is negative
   */
  public ThiefRoomNode(
          Coordinates coordinates,
          double thiefPenalty,
          boolean isGoal) throws IllegalArgumentException {
    super(coordinates, 0, thiefPenalty, isGoal);
  }

  @Override
  public int loot(int gold) {
    gold *= 1 - this.getThiefPenalty();
    return gold;
  }

  @Override
  public boolean isThiefRoom() {
    return true;
  }

  @Override
  public boolean isGoldRoom() {
    return false;
  }
}
