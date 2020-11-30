package gui;

import java.awt.*;
import java.io.IOException;

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

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    for (Character c : csq.toString().toCharArray()) {
      this.append(c);
    }
    return this;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    for (int i = start; i < end; i++) {
      this.append(csq.charAt(i));
    }
    return this;
  }

  @Override
  public Appendable append(char c) throws IOException {
    // append char to logger view
    return this;
  }
}
