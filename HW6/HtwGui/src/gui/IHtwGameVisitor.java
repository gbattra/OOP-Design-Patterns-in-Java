package gui;

import java.util.List;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

/**
 * Interface for a visitor of an IHtwGame object.
 *
 * @param <R> the return type of the function
 */
public interface IHtwGameVisitor<R> {
  /**
   * Callback which the HtwGame object will call on the visitor.
   *
   * @param players the list of players in the game
   * @param maze the maze in the game
   * @param activePlayerNumber the number of the player whose turn it is
   * @return R
   */
  R visitGame(List<IHtwPlayer> players, IHtwMaze maze, int activePlayerNumber);
}
