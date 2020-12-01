package gui;

import java.awt.*;

import javax.swing.*;

public class ButtonBar extends JPanel {
  private final JButton quitBtn;
  private final JButton restartBtn;

  public ButtonBar(
          String caption,
          IButtonBarFeatures features) throws IllegalArgumentException {
    super();
    if (features == null) {
      throw new IllegalArgumentException(
              "Cannot instantiate ButtonBar. Features are null.");
    }

    this.quitBtn = new JButton("Quit");
    this.quitBtn.addActionListener(l -> features.onQuit());
    this.add(this.quitBtn);

    this.restartBtn = new JButton("Restart");
    this.restartBtn.addActionListener(l -> features.onRestart());
    this.add(this.restartBtn);
  }
}
