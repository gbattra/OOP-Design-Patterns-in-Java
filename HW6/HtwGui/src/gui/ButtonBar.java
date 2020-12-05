package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Wrapper panel for the button bar atop the view.
 */
public class ButtonBar extends JPanel {
  /**
   * Constructor for the button bar.
   *
   * @param features the object implementing the functionality for the view
   * @throws IllegalArgumentException if features is null
   */
  public ButtonBar(IButtonBarFeatures features) throws IllegalArgumentException {
    super();
    if (features == null) {
      throw new IllegalArgumentException(
              "Cannot instantiate ButtonBar. Features are null.");
    }

    this.setLayout(new GridLayout(0, 2));

    JButton quitBtn = new JButton("Quit");
    quitBtn.addActionListener(l -> features.onQuit());
    this.add(quitBtn);

    JButton restartBtn = new JButton("Restart");
    restartBtn.addActionListener(l -> features.onRestart());
    this.add(restartBtn);
  }
}
