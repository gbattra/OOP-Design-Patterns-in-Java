package gui;

import htw.level.IHtwNode;

public interface IHtwNodeVisitor<R> {
  R visitBatCave(IHtwNode node);
  R visitPitCave(IHtwNode node);
  R visitWumpus(IHtwNode node);
  R visitTunnel(IHtwNode node);
  R visitStandardCave(IHtwNode node);
}
