package gui;

import javax.swing.*;

import htw.level.IHtwNode;

public class NodeView extends JPanel implements IHtwNodeVisitor<Void> {
  public NodeView() {
    super();
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
