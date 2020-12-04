package gui;

/**
 * The features for a node view.
 */
public interface INodeViewFeatures {
  /**
   * Functionality for when a user right-clicks the node view.
   *
   * @param id the id of the node clicked
   */
  void onRightClick(int id);

  /**
   * Functionality for when a user left-clicks the node view.
   *
   * @param id the id of the node clicked
   */
  void onLeftClick(int id);
}
