package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import htw.level.IHtwNode;

public class NodeView extends JPanel implements IHtwNodeVisitor<Void> {
  private final List<PlayerIcon> playerIcons;

  public NodeView() {
    super();

    this.playerIcons = new ArrayList<>();
    this.playerIcons.add(new PlayerIcon(Color.MAGENTA));
    this.playerIcons.add(new PlayerIcon(Color.BLACK));
  }

  public void setOccupied(boolean occupied, int playerId) {
    this.add(new JLabel(this.playerIcons.get(playerId)));
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
