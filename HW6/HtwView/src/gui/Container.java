package gui;

import java.awt.*;

import javax.swing.*;

public class Container extends JFrame implements IContainer {
  private final IButtonBar buttonBar;

  public Container(String caption, IGuiFeatures features) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    this.buttonBar = new ButtonBar("ButtonBar", features);

    pack();
    setVisible(true);
  }
}
