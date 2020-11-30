package gui;

import javax.swing.*;

public class StartMenu extends JPanel {
  public StartMenu(String caption, IMenuFeatures features) {
    super();
    if (features == null) {
      throw new IllegalArgumentException("Cannot instantiate StartMenu. Features are null.");
    }

    this.add(new JLabel(caption));
  }
}
