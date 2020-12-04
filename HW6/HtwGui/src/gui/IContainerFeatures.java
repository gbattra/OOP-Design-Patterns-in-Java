package gui;

import maze.Direction;

/**
 * Features for the container object.
 */
public interface IContainerFeatures {
  /**
   * Functionality for when the user wants to quit.
   */
  void onQuit();

  /**
   * Functionality for when the user wants to restart the game.
   *
   * @param restartRequest the restart request holding the user inputs for the game configs
   */
  void onRestart(RestartRequest restartRequest);

  /**
   * Functionality for when a user wants to move the player.
   *
   * @param id the target node id
   */
  void onMove(int id);

  /**
   * Functionality for when a user wants to move the player.
   *
   * @param dir the direction to move
   */
  void onMove(Direction dir);

  /**
   * Functionality for when the user wants to shoot an arrow.
   *
   * @param id the node id toward which to shoot the arrow
   * @param count how many nodes to traverse
   */
  void onShoot(int id, int count);
}
