package basic_mvc;

import java.awt.event.ActionListener;

/**
 * The interface for our view class.
 */
public interface IView {
  /**
   * Set the label that is showing what the model stores.
   * 
   * @param s the value for the label
   */
  void setEchoOutput(String s);

  /**
   * Get the string from the text field and return it.
   * 
   * @return the string in the text field
   */
  String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work
   * for this purpose but would be incorrect. This is because the text field is
   * not set programmatically in general but input by the user.
   */
  void clearInputString();

  /**
   * Set the listener for any actions.
   * 
   * @param listener the listener to use
   */
  void setListener(ActionListener listener);

  /**
   * Display this view.
   */
  void display();
}
