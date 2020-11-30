package full_mvc;

import model.IModel;

/**
 * The controller now implements the Features interface. This means each of
 * those functions will give control to the controller.
 */
public class Controller implements Features {
  private IModel model;
  private IView view;

  /**
   * Constructor.
   * 
   * @param m the model to use
   */
  public Controller(IModel m) {
    model = m;
  }

  /**
   * Mutator for the view.
   * 
   * @param v the view to use
   */
  public void setView(IView v) {
    view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  @Override
  public void processInput(String text) {
    model.setString(text);

    // clear input text field
    view.clearInputString();
    // finally echo the string in view
    view.setEchoOutput(model.getString());

    // set focus back to main frame so that keyboard events work
    view.resetFocus();

  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void toggleColor() {
    view.toggleColor();
  }

  @Override
  public void makeUppercase() {
    String text = model.getString();
    text = text.toUpperCase();
    view.setEchoOutput(text);
  }

  @Override
  public void restoreCase() {
    String text = model.getString();
    view.setEchoOutput(text);
  }
}