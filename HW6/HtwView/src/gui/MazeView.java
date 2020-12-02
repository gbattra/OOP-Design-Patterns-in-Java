package gui;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.level.IHtwNode;

public class MazeView extends JPanel implements IHtwMazeVisitor<Void> {
  private JPanel nodeGrid;

  public MazeView() {
    super();
    this.setLayout(new BorderLayout());
    this.setBackground(Color.YELLOW);

    JPanel gridWrapper = new JPanel();
    gridWrapper.setLocation(0, 0);
    gridWrapper.setBackground(Color.BLUE);
    gridWrapper.setSize(
            LayoutConfigs.NODE_SIZE * 10,
            LayoutConfigs.NODE_SIZE * 10);

    this.nodeGrid = new JPanel();
    this.nodeGrid.setSize(LayoutConfigs.NODE_SIZE * 10, LayoutConfigs.NODE_SIZE * 10);
    this.nodeGrid.setLayout(new GridLayout(10, 10));
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        NodeView nodeView = new NodeView();
        if ((c + r) % 2 == 0) {
          nodeView.setBackground(Color.BLACK);
        }
        this.nodeGrid.add(nodeView);
      }
    }
    this.add(this.nodeGrid);
  }

  public void populate(List<IHtwPlayer> players, IHtwMaze maze) {
    maze.receive(this);
  }

  @Override
  public Void visitMaze(IHtwNode root, Dimension dimension) {

    return null;
  }
}
