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
    this.setLayout(new GridBagLayout());
    for (int i = 0; i < 10; i++) {
      for (int d = 0; d < 10; d++) {
        this.add(new NodeView());
      }
    }
    this.nodeViews = new NodeView[1][1];
  }

  @Override
  public Void visitPlayer(IHtwPlayer player) {
    if (nodeViews.length < player.currentPosition().getY()
      || nodeViews[0].length < player.currentPosition().getX()) {
      throw new IllegalStateException("Player position is out of range of node grid.");
    }

    NodeView nodeView =
            this.nodeViews[player.currentPosition().getY()][player.currentPosition().getX()];
    if (nodeView == null) {
      throw new IllegalStateException(
              "NodeGrid failed to update. No node present at player position.");
    }

    nodeView.setVisible(true);
    nodeView.setOccupied(player.number());
    return null;
  }

  @Override
  public Void visitMaze(IHtwNode root, Dimension dimension) {
    this.nodeViews = new NodeView[dimension.height][dimension.width];
    for (int r = 0; r < dimension.height; r++) {
      for (int c = 0; c < dimension.width; c++) {
        NodeView nodeView = new NodeView();
        ((IHtwNode) root.get(new Coordinates(c, r))).receive(nodeView);
        this.nodeViews[r][c] = nodeView;
        this.add(nodeView);
      }
    }

    return null;
  }
}
