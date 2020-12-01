package gui;

import java.awt.*;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwNode;
import maze.components.Coordinates;

public class NodeGrid extends JPanel implements IHtwPlayerVisitor<Void>, IHtwMazeVisitor<Void> {
  private NodeView[][] nodeViews;

  public NodeGrid() {
    super();

    this.setLayout(new GridLayout(10, 10));

  }

  @Override
  public Void visitPlayer(IHtwPlayer player) {
    return null;
  }

  @Override
  public Void visitMaze(IHtwNode root, Dimension dimension) {
    this.setLayout(new GridLayout(dimension.height, dimension.width));
    for (int r = 0; r < dimension.height; r++) {
      for (int w = 0; w < dimension.width; w++) {
        NodeView nodeView = new NodeView();
        ((IHtwNode) root.get(new Coordinates(w, r))).receive(nodeView);
        this.nodeViews[r][w] = nodeView;
        this.add(nodeView);
      }
    }

    return null;
  }
}
