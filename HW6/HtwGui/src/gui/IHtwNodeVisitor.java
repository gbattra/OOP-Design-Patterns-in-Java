package gui;

import htw.IHtwNode;

/**
 * Interface for a visitor of an HtwNode object.
 *
 * @param <R> the return type of the function
 */
public interface IHtwNodeVisitor<R> {
  /**
   * A bat cave will call this function it receives a visitor.
   *
   * @param node the node being visited
   * @return R
   */
  R visitBatCave(IHtwNode node);

  /**
   * A pit cave will call this function it receives a visitor.
   *
   * @param node the node being visited
   * @return R
   */
  R visitPitCave(IHtwNode node);

  /**
   * A wumpus cave will call this function it receives a visitor.
   *
   * @param node the node being visited
   * @return R
   */
  R visitWumpus(IHtwNode node);

  /**
   * A tunnel will call this function it receives a visitor.
   *
   * @param node the node being visited
   * @return R
   */
  R visitTunnel(IHtwNode node);

  /**
   * A standard cave will call this function it receives a visitor.
   *
   * @param node the node being visited
   * @return R
   */
  R visitStandardCave(IHtwNode node);
}
