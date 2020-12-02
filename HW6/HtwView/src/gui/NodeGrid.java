package gui;

import java.awt.*;

import javax.swing.*;

import htw.game.IHtwPlayer;
import htw.level.IHtwNode;
import maze.components.Coordinates;

public class NodeGrid extends JPanel implements IHtwPlayerVisitor<Void> {
  private NodeView[][] nodeViews;

  public NodeGrid(IHtwNode root, Dimension dimension) {
    super();
//    this.setLayout(new GridLayout(dimension.width, dimension.height));
    this.nodeViews = new NodeView[dimension.height][dimension.width];
    for (int r = 0; r < dimension.height; r++) {
      for (int c = 0; c < dimension.width; c++) {
        NodeView nodeView = new NodeView();
        if (c % 2 == 0) {
          nodeView.setBackground(Color.BLACK);
        }
        ((IHtwNode) root.get(new Coordinates(c, r))).receive(nodeView);
        this.nodeViews[r][c] = nodeView;
        this.add(nodeView);
      }
    }
  }

  @Override
  public Void visitPlayer(IHtwPlayer player) {
//    if (nodeViews.length < player.currentPosition().getY()
//      || nodeViews[0].length < player.currentPosition().getX()) {
//      throw new IllegalStateException("Player position is out of range of node grid.");
//    }
//
//    NodeView nodeView =
//            this.nodeViews[player.currentPosition().getY()][player.currentPosition().getX()];
//    if (nodeView == null) {
//      throw new IllegalStateException(
//              "NodeGrid failed to update. No node present at player position.");
//    }
//
//    nodeView.setVisible(true);
//    nodeView.setOccupied(player.number());
    return null;
  }
}
