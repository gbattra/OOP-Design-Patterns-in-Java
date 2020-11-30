package gui;

import java.util.function.Function;

import javax.swing.*;

public class ButtonBar extends JFrame implements IButtonBar {
  private final JButton quitBtn;

  public ButtonBar(
          String caption,
          IButtonBarFeatures features) throws IllegalArgumentException {
    super(caption);
    if (features == null) {
      throw new IllegalArgumentException(
              "Cannot instantiate ButtonBar. Features are null.");
    }

    this.quitBtn = new JButton("Quit");
    this.quitBtn.setSize(100, 50);
    this.quitBtn.addActionListener(l -> features.onQuit());
  }
}
