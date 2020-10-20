package maze.interfaces;

/**
 * Interface for a player in a maze.
 */
public interface Player {
  /**
   * Getter for the player name.
   *
   * @return the name
   */
  String getName();

  /**
   * Getter for the player's gold count.
   *
   * @return the gold count
   */
  int getGold();

  /**
   * Loots the node for gold, or gets robbed. Sets current node on player instance.
   *
   * @param node the node to enter
   * @return the updated player instance
   */
  Player loot(Node node);
}
