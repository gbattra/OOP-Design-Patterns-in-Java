package gui;

import maze.Direction;

/**
 * Callbacks for the IView object.
 */
public interface IViewFeatures {
  /**
   * Functionality for when a user wants to restart the game.
   *
   * @param restartRequest the restart request with user-defined game configs
   */
  void restart(RestartRequest restartRequest);

  /**
   * Functionality for when a user wants to move.
   *
   * @param id the id of the target node to move toward
   */
  void onMove(int id);

  /**
   * Functionality for when a user wants to move.
   *
   * @param direction the direction to move in
   */
  void onMove(Direction direction);

  /**
   * Functionality for when a user wants to shoot an arrow.
   *
   * @param id the id of the target node to shoot toward
   * @param count the number of nodes to traverse
   */
  void onShoot(int id, int count);
}
