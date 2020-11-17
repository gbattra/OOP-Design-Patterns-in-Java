package htw.level.nodes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import htw.game.IHtwPlayer;
import htw.level.strategies.IHtwNodeStrategy;
import maze.components.ICoordinates;
import maze.components.nodes.Node;
import maze.utils.Direction;

/**
 * Interface for a node in a Hunt the Wumpus maze.
 */
public interface IHtwNode extends Node {
  /**
   * Gets the nodes at each valid exit for this node.
   *
   * @return the node's neighbors
   */
  Map<Direction, Integer> neighbors();

  /**
   * Getter for the node's ID.
   *
   * @return the node's id
   */
  Integer getId();

  /**
   * Getter for the node's logger.
   *
   * @return the node's logger
   */
  Appendable logger();

  /**
   * Can the player can enter this node in the maze from their current position.
   *
   * @return true if can enter from current position
   */
  boolean canEnter();

  /**
   * Gets the direction from this node to the node with the target id.
   *
   * @param id the target id
   * @return the direction to that node
   */
  Direction directionTo(int id);

  /**
   * Get an node by its id.
   *
   * @param id the id of the target node
   * @return the target node
   */
  IHtwNode get(int id);

  /**
   * Helper for getting a node by its id.
   *
   * @param traversed the coordinates traversed
   * @param id the target id
   * @return the target node
   */
  IHtwNode getHelper(List<ICoordinates> traversed, int id);

  /**
   * Attempts to enter the node from the specified direction. Depending on the node's strategy
   * this may either return the called node, or (in the case of bats), return another random
   * node in the maze.
   *
   * @param from the direction from which the node was entered
   * @return the entered node
   */
  IHtwNode enter(Direction from) throws IOException;

  /**
   * Shoots an arrow in the specified direction. Each node traversed by the arrow decrements the
   * count before recursively calling the next node's shoot() method.
   *
   * @param direction the direction to shoot the arrow
   * @param count the number of nodes to traverse
   * @return true if the wumpus was struck
   */
  boolean shoot(Direction direction, int count);

  /**
   * Sets the strategy used by the node.
   *
   * @param strategy the strategy to set
   */
  void setStrategy(IHtwNodeStrategy strategy);

  /**
   * Takes a player object and interacts with it using the node's strategy. I.e. a wumpus strategy
   * would kill the player.
   *
   * @param player the player to receive
   */
  void receive(IHtwPlayer player) throws IOException;

  /**
   * Does the node contain a smell.
   *
   * @param from from which direction is this node being smelled (used for tunnel nodes)
   * @return true for the node with the Wumpus.
   */
  boolean smelly(Direction from);

  /**
   * Does the node contain a draft.
   *
   * @param from from which direction is this node being sensed (used for tunnel nodes)
   * @return true for the node with a bottomless pit.
   */
  boolean drafty(Direction from);

  /**
   * Returns a `Map` of `Direction -> node id` pairs for all caves connected
   * directly to this node.
   *
   * @param traversed nodes traversed so far (used by tunnel nodes)
   * @return this node if it is not a tunnel
   */
  IHtwNode adjacent(List<ICoordinates> traversed);
}
