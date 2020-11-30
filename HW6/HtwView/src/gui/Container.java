package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

public class Container extends JFrame implements IMenuFeatures, IButtonBarFeatures, Appendable {
  private final IContainerFeatures features;

  private final ButtonBar buttonBar;
  private final StartMenu startMenu;

  public Container(String caption, IContainerFeatures features) {
    super(caption);
    if (features == null) {
      throw new IllegalArgumentException("Cannot instantiate Container. Features are null.");
    }

    this.features = features;

    this.setSize(500, 300);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    this.buttonBar = new ButtonBar("ButtonBar", this);
    this.buttonBar.setSize(200, 50);
    this.buttonBar.setLocation(0, 0);
    this.add(this.buttonBar);

    this.startMenu = new StartMenu("StartMenu", this);
    this.startMenu.setSize(200, 400);
    this.startMenu.setLocation(0, 50);
    this.startMenu.setVisible(false);
    this.add(this.startMenu);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void onQuit() {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

  @Override
  public void onRestart() {
    System.out.print("Restarting");
    this.startMenu.setVisible(true);
  }

  @Override
  public void onSubmit() {
    System.out.print("STARTING NEW GAME");
    this.startMenu.setVisible(false);
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