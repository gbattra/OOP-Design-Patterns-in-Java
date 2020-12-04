package gui;

import htw.game.IHtwGame;

/**
 * Interface for the main view object used by the controller.
 */
public interface IView extends IContainerFeatures, Appendable {
  /**
   * Sets the callbacks for the view features.
   *
   * @param features the object implementing the view features
   */
  void setFeatures(IViewFeatures features);

  /**
   * Populates the view given the updated model.
   *
   * @param game the model to populate the view with
   */
  void populate(IHtwGame game);

  /**
   * Alerts the user with a provided message.
   *
   * @param message the message to display
   */
  void alert(String message);
}
