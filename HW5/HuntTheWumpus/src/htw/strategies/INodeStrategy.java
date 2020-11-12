package htw.strategies;

import htw.game.IPlayer;
import htw.nodes.INode;
import maze.utils.Direction;

/**
 * Interface for a strategy used by a node in a Hunt the Wumpus cave.
 */
public interface INodeStrategy {
  /**
   * Functionality for entering the node.
   *
   * @param from the direction from which the node is entered
   * @param curr the node itself
   * @return the node entered
   */
  INode enter(Direction from, INode curr);

  /**
   * Shoots an arrow in the specified direction and traverses the specified number of caves.
   *
   * @param direction the direction to shoot the arrow
   * @param count how many caves the arrow should traverse
   * @param curr the node itself
   * @return true if the wumpus is struck
   */
  boolean shoot(Direction direction, int count, INode curr);

  /**
   * Receives a player and modifies/interacts with it. I.e. a wumpus strategy will kill the player.
   *
   * @param player the player to interact with
   */
  void receive(IPlayer player);
}
