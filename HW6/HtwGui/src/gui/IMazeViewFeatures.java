package gui;

/**
 * The callback features for the maze view.
 */
public interface IMazeViewFeatures {
  /**
   * Functionality for when the user wants to shoot an arrow.
   *
   * @param id the target node to shoot toward
   * @param shootCount the number of nodes to traverse
   */
  void onShoot(int id, int shootCount);

  /**
   * Callback for when a user wants to move the player.
   *
   * @param id the target node to move to
   */
  void onMove(int id);
}
