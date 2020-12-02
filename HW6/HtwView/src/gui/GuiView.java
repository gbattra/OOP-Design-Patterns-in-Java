package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

public class GuiView implements IView, IHtwGameVisitor<Void> {
  private JFrame frame;
  private Container container;
  private IViewFeatures features;

  public GuiView() {
    frame = new JFrame("Hunt the Wumpus");
    frame.setSize(LayoutConfigs.WIDTH, LayoutConfigs.HEIGHT);
    frame.setLocation(0, 0);
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  public void setFeatures(IViewFeatures features) {
    this.features = features;
  }

  @Override
  public void populate(IHtwGame game) {
    game.receive(this);
  }

  @Override
  public Void visitGame(List<IHtwPlayer> players, IHtwMaze maze) {
    if (this.container != null) {
      frame.remove(this.container);
    }

    this.container = new Container(this, players, maze);
    frame.add(this.container);
    frame.revalidate();
    frame.repaint();
    return null;
  }

  @Override
  public void onRestart(RestartRequest restartRequest) {
    this.features.restart(restartRequest);
  }

  @Override
  public void onMove(int id) {
    this.features.onMove(id);
  }

  @Override
  public void onQuit() {
    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
