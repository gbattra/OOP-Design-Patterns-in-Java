package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.level.IHtwNode;

public class Container extends JFrame implements IButtonBarFeatures, IHtwMazeVisitor<Void> {
  private final IContainerFeatures features;

  private final ButtonBar buttonBar;
  private final StartMenu startMenu;
  private final PlayerBar playerBar;
  private MazeView mazeView;

  public Container(
          String caption,
          IContainerFeatures features,
          List<IHtwPlayer> players,
          IHtwMaze maze) {
    super(caption);
    if (features == null) {
      throw new IllegalArgumentException("Cannot instantiate Container. Features are null.");
    }

    this.features = features;

    this.setSize(LayoutConfigs.WIDTH, LayoutConfigs.HEIGHT);
    this.setLocation(0, 0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.startMenu = new StartMenu();

    this.buttonBar = new ButtonBar(this);
    this.buttonBar.setSize(LayoutConfigs.WIDTH, LayoutConfigs.SMALL);
    this.buttonBar.setLocation(0, 0);
    this.add(this.buttonBar);

    this.playerBar = new PlayerBar(players);
    this.playerBar.setSize(LayoutConfigs.WIDTH, LayoutConfigs.LARGE);
    this.playerBar.setLocation(0, this.buttonBar.getY() + this.buttonBar.getHeight());
    this.add(this.playerBar);

    maze.receive(this);

    JPanel buffer = new JPanel();
    buffer.setSize(LayoutConfigs.WIDTH, LayoutConfigs.LARGE);
    buffer.setLocation(0, this.playerBar.getY() + this.playerBar.getHeight());
    this.add(buffer);

    this.setVisible(true);
  }

  @Override
  public Void visitMaze(IHtwNode root, Dimension dimension) {
    this.mazeView = new MazeView(root, dimension);
    this.mazeView.setLocation(0, this.playerBar.getY() + this.playerBar.getHeight());
    this.mazeView.setSize(LayoutConfigs.WIDTH, LayoutConfigs.MAZE_HEIGHT);
    this.add(this.mazeView);
    return null;
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
      int arrowCount = ((Number) this.startMenu.arrowCount.getValue()).intValue();
      int rowCount = ((Number) this.startMenu.rowCount.getValue()).intValue();
      int colCount = ((Number) this.startMenu.columnCount.getValue()).intValue();
      boolean isRoomMaze = this.startMenu.isRoomMaze.isSelected();
      int finalEdgeCount = ((Number) this.startMenu.finalEdgeCount.getValue()).intValue();
      double pitFrequency = ((Number) this.startMenu.pitFrequency.getValue()).doubleValue();
      double batFrequency = ((Number) this.startMenu.batFrequency.getValue()).doubleValue();

      this.features.onRestart(
              new RestartRequest(
                      sameMaze,
                      multiplayer,
                      isRoomMaze,
                      arrowCount,
                      finalEdgeCount,
                      rowCount,
                      colCount,
                      pitFrequency,
                      batFrequency));
    }
  }
}
