package htw.level;

import java.io.IOException;
import java.util.List;

import htw.game.IHtwPlayer;
import maze.components.ICoordinates;
import maze.Direction;

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
  IHtwNode enter(Direction from, IHtwNode curr) throws IOException;

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
   * @param curr the node itself
   */
  void receive(IHtwPlayer player, IHtwNode curr) throws IOException;

  /**
   * Does the node contain a smell.
   *
   * @param from from which direction is this node being smelled (used for tunnel strategies)
   * @param curr the node to smell
   * @return true for the node with the Wumpus.
   */
  boolean smelly(Direction from, IHtwNode curr);

  /**
   * Does the node contain a draft.
   *
   * @param from from which direction is this node being sensed (used for tunnel strategies)
   * @param curr the node to sense
   * @return true for the node with a bottomless pit.
   */
  boolean drafty(Direction from, IHtwNode curr);

  /**
   * Returns a `Map` of `Direction -> node id` pairs for all caves connected
   * directly to this node.
   *
   * @param traversed nodes traversed so far (used by tunnel strategies)
   * @param curr the node being traversed currently
   * @return this node if it is not a tunnel
   */
  IHtwNode adjacent(List<ICoordinates> traversed, IHtwNode curr);
}
