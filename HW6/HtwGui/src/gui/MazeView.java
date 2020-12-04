package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import htw.game.IHtwPlayer;
import htw.level.IHtwNode;
import maze.components.Coordinates;

/**
 * View component representing the maze in the game.
 */
public class MazeView extends JPanel implements INodeViewFeatures, IHtwPlayerVisitor<Void> {
  private JPanel nodeGrid;
  private NodeView[][] nodeViews;

  private final IMazeViewFeatures features;

  /**
   * Constructor for the maze object.
   *
   * @param root the root node of the maze
   * @param dimension the dimensions of the maze
   * @param features the callbacks for this view
   * @throws IllegalArgumentException if args are null
   */
  public MazeView(
          IHtwNode root,
          Dimension dimension,
          IMazeViewFeatures features) throws IllegalArgumentException {
    super();
    if (root == null || dimension == null || features == null) {
      throw new IllegalArgumentException("Cannot instantiate view. Args are null.");
    }

    this.features = features;

    this.setLayout(new BorderLayout());
    this.setBackground(Color.YELLOW);

    this.nodeGrid = new JPanel();
    this.nodeGrid.setSize(
            LayoutConfigs.NODE_SIZE * dimension.width, LayoutConfigs.NODE_SIZE * dimension.width);
    this.nodeGrid.setLayout(new GridLayout(dimension.height, dimension.width));

    this.nodeViews = new NodeView[dimension.height][dimension.width];
    for (int r = 0; r < dimension.height; r++) {
      for (int c = 0; c < dimension.width; c++) {
        IHtwNode node = (IHtwNode) root.get(new Coordinates(c, r));
        NodeView nodeView = new NodeView(node, this);
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
    this.features.onMove(id);
  }
}
