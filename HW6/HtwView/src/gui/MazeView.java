package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import htw.game.IHtwPlayer;
import htw.level.IHtwNode;
import maze.components.Coordinates;

public class MazeView extends JPanel implements INodeViewFeatures, IHtwPlayerVisitor<Void> {
  private JPanel nodeGrid;
  private NodeView[][] nodeViews;

  private final IMazeViewFeatures features;

  public MazeView(IHtwNode root, Dimension dimension, IMazeViewFeatures features) {
    super();
    this.features = features;

    this.setLayout(new BorderLayout());
    this.setBackground(Color.YELLOW);

    this.nodeGrid = new JPanel();
    this.nodeGrid.setSize(LayoutConfigs.NODE_SIZE * 10, LayoutConfigs.NODE_SIZE * 10);
    this.nodeGrid.setLayout(new GridLayout(10, 10));

    this.nodeViews = new NodeView[dimension.height][dimension.width];
    for (int r = 0; r < dimension.height; r++) {
      for (int c = 0; c < dimension.width; c++) {
        NodeView nodeView = new NodeView((IHtwNode) root.get(new Coordinates(c, r)), this);
        this.nodeGrid.add(nodeView);
        this.nodeViews[r][c] = nodeView;
      }
    }

    JScrollPane scrollPane = new JScrollPane(this.nodeGrid);
    this.add(scrollPane);
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

    nodeView.setOccupied(player.number());
    return null;
  }

  @Override
  public void onRightClick(int id) {
    JPanel shootForm = new JPanel();
    JLabel shootLabel = new JLabel("Shoot count: ");
    JFormattedTextField shootField = new JFormattedTextField(new NumberFormatter());
    shootField.setColumns(10);
    shootField.setValue(1);

    shootForm.add(shootLabel);
    shootForm.add(shootField);

    int result = JOptionPane.showConfirmDialog(
            null, shootForm, "Shoot", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      int shootCount = ((Number) shootField.getValue()).intValue();
      this.features.onShoot(id, shootCount);
    }
  }

  @Override
  public void onLeftClick(int id) {
    System.out.print("Left clicked");
  }
}
