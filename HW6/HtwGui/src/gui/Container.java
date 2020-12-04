package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.level.IHtwNode;
import maze.Direction;

/**
 * Main wrapper around entire view of the GUI.
 */
public class Container
        extends JPanel
        implements IButtonBarFeatures, IMazeViewFeatures, IHtwMazeVisitor<Void>, KeyListener {
  public ButtonBar buttonBar;
  public StartMenu startMenu;
  public PlayerBar playerBar;
  public MazeView mazeView;
  public TextArea logger;

  private final IContainerFeatures features;

  /**
   * Constructor for the container.
   *
   * @param features object implementing the callback functionality for the container
   * @param players the list of player objects to populate views
   * @param maze the maze to populate the view with
   * @param activePlayerNumber the player number of the player whose turn it is
   */
  public Container(
          IContainerFeatures features,
          List<IHtwPlayer> players,
          IHtwMaze maze,
          int activePlayerNumber) {
    super();
    if (features == null) {
      throw new IllegalArgumentException("Cannot instantiate Container. Features are null.");
    }

    this.features = features;

    this.setSize(LayoutConfigs.WIDTH, LayoutConfigs.HEIGHT);
    this.setLocation(0, 0);
    this.setLayout(new BorderLayout());
    this.setBackground(Color.BLACK);
    this.addKeyListener(this);

    this.startMenu = new StartMenu();

    this.buttonBar = new ButtonBar(this);
    this.buttonBar.setSize(LayoutConfigs.WIDTH, LayoutConfigs.SMALL);
    this.buttonBar.setLocation(0, 0);
    this.add(this.buttonBar);

    JPanel playerTurnView = new JPanel();
    playerTurnView.setSize(LayoutConfigs.WIDTH, LayoutConfigs.SMALL / 2);
    playerTurnView.setBackground(PlayerConfigs.COLORS.get(activePlayerNumber - 1));
    playerTurnView.setLocation(0, this.buttonBar.getY() + this.buttonBar.getHeight());
    this.add(playerTurnView);

    this.playerBar = new PlayerBar(players);
    this.playerBar.setSize(LayoutConfigs.WIDTH, LayoutConfigs.LARGE);
    this.playerBar.setLocation(0, playerTurnView.getY() + playerTurnView.getHeight());
    this.add(this.playerBar);

    maze.receive(this);
    for (IHtwPlayer player : players) {
      player.receive(this.mazeView);
    }

    JPanel loggerView = new JPanel();
    loggerView.setBackground(Color.orange);
    loggerView.setLayout(new GridLayout(1, 1));
    loggerView.setSize(LayoutConfigs.WIDTH, LayoutConfigs.LARGE * 2);
    loggerView.setLocation(0, this.mazeView.getY() + this.mazeView.getHeight());

    this.logger = new TextArea();
    this.logger.setSize(LayoutConfigs.WIDTH, LayoutConfigs.LARGE * 2);
    loggerView.add(this.logger);

    this.add(loggerView);

    JPanel buffer = new JPanel();
    buffer.setSize(LayoutConfigs.WIDTH, LayoutConfigs.LARGE);
    buffer.setLocation(0, this.playerBar.getY() + this.playerBar.getHeight());
    this.add(buffer);
  }

  @Override
  public Void visitMaze(IHtwNode root, Dimension dimension) {
    this.mazeView = new MazeView(root, dimension, this);
    this.mazeView.setLocation(0, this.playerBar.getY() + this.playerBar.getHeight());
    this.mazeView.setSize(
            LayoutConfigs.WIDTH - LayoutConfigs.SCROLLBAR_OFFSET, LayoutConfigs.MAZE_HEIGHT);
    this.add(this.mazeView);
    return null;
  }

  @Override
  public void onQuit() {
    features.onQuit();
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

  @Override
  public void onShoot(int id, int shootCount) {
    this.features.onShoot(id, shootCount);
  }

  @Override
  public void onMove(int id) {
    this.features.onMove(id);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      this.features.onMove(Direction.NORTH);
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      this.features.onMove(Direction.SOUTH);
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      this.features.onMove(Direction.EAST);
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      this.features.onMove(Direction.WEST);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }
}
