package gui;

import java.awt.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Container extends JFrame implements IButtonBarFeatures {
  private static final int WIDTH = 750;
  private static final int HEIGHT = 750;
  private static final int BTN_BAR_HEIGHT = 50;
  private static final int START_MENU_HEIGHT = 400;

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
    this.buttonBar.setSize(WIDTH, BTN_BAR_HEIGHT);
    this.buttonBar.setLocation(0, 0);
    this.add(this.buttonBar);

    this.startMenu = new StartMenu("StartMenu");

    this.add(this.wrapper);
    this.setVisible(true);
  }

  @Override
  public void onQuit() {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

  @Override
  public void onRestart() {
    int result = JOptionPane.showConfirmDialog(
            null, this.startMenu, "Configure", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      boolean sameMaze = this.startMenu.useSameMaze.isSelected();
      boolean multiplayer = this.startMenu.isMultiplayer.isSelected();
      int rowCount = Integer.parseInt(this.startMenu.rowCount.getText());
      int colCount = Integer.parseInt(this.startMenu.columnCount.getText());
      double pitFrequency = Double.parseDouble(this.startMenu.pitFrequency.getText());
      double batFrequency = Double.parseDouble(this.startMenu.batFrequency.getText());

      this.features.onRestart(
              new RestartRequest(
                      sameMaze,
                      multiplayer,
                      false,
                      0,
                      rowCount,
                      colCount,
                      pitFrequency,
                      batFrequency));
    }
  }
}
