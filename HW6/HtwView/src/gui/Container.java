package gui;

import java.awt.*;

import javax.swing.*;

public class Container extends JFrame implements IContainer {
  private final ButtonBar buttonBar;

  public Container(String caption, IGuiFeatures features) {
    super(caption);

    this.setSize(500, 300);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    this.buttonBar = new ButtonBar("ButtonBar", features);
    this.add(this.buttonBar);

    this.pack();
    this.setVisible(true);
  }
}
