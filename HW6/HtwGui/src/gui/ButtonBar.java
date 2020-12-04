package gui;

import java.awt.*;

import javax.swing.*;

/**
 * Wrapper panel for the button bar atop the view.
 */
public class ButtonBar extends JPanel {
  private final JButton quitBtn;
  private final JButton restartBtn;

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

    this.quitBtn = new JButton("Quit");
    this.quitBtn.addActionListener(l -> features.onQuit());
    this.add(this.quitBtn);

    this.restartBtn = new JButton("Restart");
    this.restartBtn.addActionListener(l -> features.onRestart());
    this.add(this.restartBtn);
  }
}
