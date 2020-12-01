package gui;

import java.awt.*;

import javax.swing.*;

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
}
