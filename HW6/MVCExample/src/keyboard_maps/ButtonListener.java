package keyboard_maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Simple button listener class that stores a map of <button, action> pairs.
 */
public class ButtonListener implements ActionListener {
  private Map<String, Runnable> buttonClickedActions;

  /**
   * Default constructor.
   */
  public ButtonListener() {
    buttonClickedActions = null;
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are
   * characters
   * 
   * @param map the actions for button clicks
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
