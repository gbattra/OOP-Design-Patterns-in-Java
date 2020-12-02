package gui;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

public class MazeView extends JPanel {
  private final NodeGrid nodeGrid;

  public MazeView() {
    super();
    this.setBackground(Color.YELLOW);
    this.setLayout(new GridLayout(1, 1));
    this.nodeGrid = new NodeGrid();
    this.nodeGrid.setBackground(Color.BLUE);
    this.nodeGrid.setLocation(0, 0);
    this.add(this.nodeGrid);
  }

  public void populate(List<IHtwPlayer> players, IHtwMaze maze) {
    maze.receive(this.nodeGrid);
    for (IHtwPlayer player : players) {
      player.receive(this.nodeGrid);
    }
  }
}
