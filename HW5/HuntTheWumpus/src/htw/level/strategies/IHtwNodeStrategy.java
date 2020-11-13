package htw.level.strategies;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;
import maze.utils.Direction;

/**
 * Interface for a strategy used by a node in a Hunt the Wumpus cave.
 */
public interface IHtwNodeStrategy {

  /**
   * Functionality for entering the node.
   *
   * @param from the direction from which the node is entered
   * @param curr the node itself
   * @return the node entered
   */
  IHtwNode enter(Direction from, IHtwNode curr);

  /**
   * Shoots an arrow in the specified direction and traverses the specified number of caves.
   *
   * @param direction the direction to shoot the arrow
   * @param count how many caves the arrow should traverse
   * @param curr the node itself
   * @return true if the wumpus is struck
   */
  boolean shoot(Direction direction, int count, IHtwNode curr);

  /**
   * Receives a player and modifies/interacts with it. I.e. a wumpus strategy will kill the player.
   *
   * @param player the player to interact with
   */
  void receive(IHtwPlayer player);

  boolean smelly(IHtwNode curr);

  boolean drafty(IHtwNode curr);
}
