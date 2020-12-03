package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import htw.level.IHtwNode;
import maze.Direction;

public class NodeView extends JPanel implements MouseListener, IHtwNodeVisitor<Void> {
  private final List<PlayerIcon> playerIcons;
  private final INodeViewFeatures features;
  private final int nodeId;

  private BufferedImage nodeImage;
  private JLabel graphics;

  public NodeView(IHtwNode node, INodeViewFeatures features) {
    super();
    if (features == null || node == null) {
      throw new IllegalArgumentException("Cannot instantiate NodeView. Features or node is null.");
    }
    this.features = features;
    this.nodeId = node.getId();

    this.addMouseListener(this);

    this.playerIcons = new ArrayList<>();
    this.playerIcons.add(new PlayerIcon(Color.MAGENTA));
    this.playerIcons.add(new PlayerIcon(Color.BLACK));

    this.graphics = this.drawGraphics(node);
//    this.graphics.setVisible(node.visited());

    this.add(graphics);
  }

  public void setOccupied(int playerId) {
    if (playerId < 1) {
      throw new IllegalArgumentException("Invalid player ID provided.");
    }

    this.setBackground(Color.RED);
    this.graphics.setVisible(true);
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
