package gui;

import htw.game.IHtwPlayer;

/**
 * Interface for a visitor of an HtwPlayer object.
 *
 * @param <R> the return type of the function
 */
public interface IHtwPlayerVisitor<R> {
  /**
   * The player will call this function when it receives the visitor.
   *
   * @param player the player being visited
   * @return R
   */
  R visitPlayer(IHtwPlayer player);
}
