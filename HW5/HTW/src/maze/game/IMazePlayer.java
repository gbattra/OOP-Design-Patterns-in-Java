package maze.game;

import maze.components.Node;

/**
 * Interface for a player in a maze.
 */
public interface IMazePlayer {
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
  IMazePlayer loot(Node node);
}
