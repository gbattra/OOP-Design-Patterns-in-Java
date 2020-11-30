package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

public class Container extends JFrame implements IMenuFeatures, IButtonBarFeatures, Appendable {
  private static final int WIDTH = 750;
  private static final int HEIGHT = 750;

  private final IContainerFeatures features;

  private final JPanel wrapper;
  private final ButtonBar buttonBar;
  private final StartMenu startMenu;

  public Container(String caption, IContainerFeatures features) {
    super(caption);
    if (features == null) {
      throw new IllegalArgumentException("Cannot instantiate Container. Features are null.");
    }

    this.features = features;
    this.setSize(WIDTH, HEIGHT);
    this.setLocation(0, 0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.wrapper = new JPanel();
    this.wrapper.setSize(WIDTH, HEIGHT);
    this.wrapper.setLocation(0, 0);
    this.wrapper.setBackground(Color.BLACK);

    this.buttonBar = new ButtonBar("ButtonBar", this);
    this.buttonBar.setSize(WIDTH, 50);
    this.buttonBar.setLocation(0, 0);
    this.buttonBar.setBackground(Color.BLUE);
    this.add(this.buttonBar);

    this.startMenu = new StartMenu("StartMenu", this);
    this.startMenu.setSize(WIDTH, 400);
    this.startMenu.setLocation(0, 50);
    this.startMenu.setBackground(Color.RED);
    this.startMenu.setVisible(false);
    this.add(this.startMenu);

    this.add(this.wrapper);
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
