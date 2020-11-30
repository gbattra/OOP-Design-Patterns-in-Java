package keyboard_events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

/**
 * Implementation of our controller.
 */
public class Controller implements ActionListener, KeyListener {
  private IModel model;
  private IView view;

  /**
   * Constructor.
   * 
   * @param m the model to use
   * @param v the view to use
   */
  public Controller(IModel m, IView v) {
    model = m;
    view = v;
    v.setListeners(this, this); // This controller can handle both kinds of events directly
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
    // read from the input text field
    case "Echo Button":
      String text = view.getInputString();
      // send text to the model
      model.setString(text);

      // clear input text field
      view.clearInputString();
      // finally echo the string in view
      text = model.getString();
      view.setEchoOutput(text);

      // set focus back to main frame so that keyboard events work
      view.resetFocus();

      break;
    case "Exit Button":
      System.exit(0);
      break;

    default:
      throw new IllegalStateException("Error: Unknown button");
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == 'd') {
      // toggle color
      view.toggleColor();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_C) { // caps
      String text = model.getString();
      text = text.toUpperCase();
      view.setEchoOutput(text);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_C) { // caps
      String text = model.getString();
      view.setEchoOutput(text);
    }
  }
}