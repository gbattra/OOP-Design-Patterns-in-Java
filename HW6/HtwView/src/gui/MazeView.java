package gui;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

public class MazeView extends JScrollPane {
  private final NodeGrid nodeGrid;

  public MazeView() {
    super();
    this.setLayout(new ScrollPaneLayout());
    this.setBackground(Color.YELLOW);

    this.nodeGrid = new NodeGrid();
    this.nodeGrid.setLocation(0, 0);
    this.nodeGrid.setBackground(Color.PINK);
    this.add(this.nodeGrid);
  }

  public void populate(List<IHtwPlayer> players, IHtwMaze maze) {
    maze.receive(this.nodeGrid);
    for (IHtwPlayer player : players) {
      player.receive(this.nodeGrid);
    }
  }
}
