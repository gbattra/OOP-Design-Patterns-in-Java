package gui;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import htw.level.IHtwNode;
import maze.Direction;

/**
 * View for a single node in the maze.
 */
public class NodeView extends JPanel implements MouseListener, IHtwNodeVisitor<Void> {
  private final INodeViewFeatures features;
  private final int nodeId;

  private BufferedImage nodeImage;
  private JLabel graphics;

  /**
   * Constructor for the node view.
   *
   * @param node the node to display
   * @param features the callbacks for events on this view
   * @throws IllegalArgumentException if args are null
   */
  public NodeView(IHtwNode node, INodeViewFeatures features) throws IllegalArgumentException {
    super();
    if (features == null || node == null) {
      throw new IllegalArgumentException("Cannot instantiate NodeView. Features or node is null.");
    }
    this.features = features;
    this.nodeId = node.getId();
    this.setLayout(new FlowLayout());
    this.addMouseListener(this);

    this.graphics = this.drawGraphics(node);
    this.graphics.setVisible(node.visited());

    this.add(graphics);
  }

  /**
   * Sets this node as occupied by a player.
   *
   * @param playerId the id of the player occupying the node
   */
  public void setOccupied(int playerId) {
    if (playerId < 1) {
      throw new IllegalArgumentException("Invalid player ID provided.");
    }

    try  {
      this.remove(this.graphics);
      URL path = this.getClass().getResource("/images/player.png");
      this.nodeImage = ImageHelper.overlay(this.nodeImage, path, 15);
      this.setBackground(PlayerConfigs.COLORS.get(playerId - 1));
      this.graphics = new JLabel(new ImageIcon(this.nodeImage));
      this.add(this.graphics);
      this.graphics.setVisible(true);
    } catch (IOException ignored) {
    }
  }

  @Override
  public Void visitBatCave(IHtwNode node) {
    try {
      URL path = this.getClass().getResource("/images/bats.png");
      this.nodeImage = ImageHelper.overlay(this.nodeImage, path, 0);
    } catch (IOException ignored) {
      return null;
    }
    return null;
  }

  @Override
  public Void visitPitCave(IHtwNode node) {
    try {
      URL path = this.getClass().getResource("/images/pit.png");
      this.nodeImage = ImageHelper.overlay(this.nodeImage, path, 0);
    } catch (IOException ignored) {
      return null;
    }
    return null;
  }

  @Override
  public Void visitWumpus(IHtwNode node) {
    try {
      URL path = this.getClass().getResource("/images/wumpus.png");
      this.nodeImage = ImageHelper.overlay(this.nodeImage, path, 0);
    } catch (IOException ignored) {
      return null;
    }
    return null;
  }

  @Override
  public Void visitTunnel(IHtwNode node) {
    return null;
  }

  @Override
  public Void visitStandardCave(IHtwNode node) {
    try {
      if (node.hasDraftyNeighbor()) {
        URL path = this.getClass().getResource("/images/breeze.png");
        this.nodeImage = ImageHelper.overlay(this.nodeImage, path, 0);
      }
      if (node.hasSmellyNeighbor()) {
        URL path = this.getClass().getResource("/images/stench.png");
        this.nodeImage = ImageHelper.overlay(this.nodeImage, path, 0);
      }
    } catch (IOException ignored) {
      return null;
    }
    return null;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      this.features.onLeftClick(this.nodeId);
    } else if (SwingUtilities.isRightMouseButton(e)) {
      this.features.onRightClick(this.nodeId);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  private JLabel drawGraphics(IHtwNode node) {
    List<Direction> exits = node.exits();
    String bgIconPath = exits.stream().map(
            d -> d.toString().substring(0, 1)).collect(Collectors.joining(""));
    try {
      URL path = this.getClass().getResource("/images/" + bgIconPath + ".png");
      this.nodeImage = ImageIO.read(path);
      node.receive(this);
      return new JLabel(new ImageIcon(this.nodeImage));
    } catch (Exception ignored) {
      return new JLabel();
    }
  }
}
