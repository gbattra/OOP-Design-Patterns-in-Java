package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import htw.level.IHtwNode;

public class NodeView extends JPanel implements MouseListener, IHtwNodeVisitor<Void> {
  private final List<PlayerIcon> playerIcons;
  private final INodeViewFeatures features;
  private final int nodeId;

  public NodeView(IHtwNode node, INodeViewFeatures features) {
    super();
    if (features == null || node == null) {
      throw new IllegalArgumentException("Cannot instantiate NodeView. Features or node is null.");
    }
    this.features = features;
    this.nodeId = node.getId();

    this.setLayout(new GridLayout(1, 1));
    this.setSize(LayoutConfigs.NODE_SIZE, LayoutConfigs.NODE_SIZE);
    this.setBackground(Color.PINK);
    this.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    new EmptyBorder(10, 10, 10, 10)));
    this.addMouseListener(this);

    this.playerIcons = new ArrayList<>();
    this.playerIcons.add(new PlayerIcon(Color.MAGENTA));
    this.playerIcons.add(new PlayerIcon(Color.BLACK));

    if (node.visited()) {
      this.setBackground(Color.BLACK);
    }
  }

  public void setOccupied(int playerId) {
    if (playerId < 1) {
      throw new IllegalArgumentException("Invalid player ID provided.");
    }
    this.setBackground(Color.BLACK);
    this.add(new JLabel(this.playerIcons.get(playerId - 1)));
  }

  @Override
  public Void visitBatCave(IHtwNode node) {
    return null;
  }

  @Override
  public Void visitPitCave(IHtwNode node) {
    return null;
  }

  @Override
  public Void visitWumpus(IHtwNode node) {
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
}
