package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import htw.level.IHtwNode;

public class NodeView extends JPanel implements IHtwNodeVisitor<Void> {
  private final List<PlayerIcon> playerIcons;

  public NodeView(IHtwNode node) {
    super();
    this.setLayout(new GridLayout(1, 1));
    this.setSize(LayoutConfigs.NODE_SIZE, LayoutConfigs.NODE_SIZE);
    this.setBackground(Color.PINK);
    this.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    new EmptyBorder(10, 10, 10, 10)));

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
}
