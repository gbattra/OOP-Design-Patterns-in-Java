package htw.maze.nodes;

import java.util.List;

import htw.game.IPlayer;
import htw.maze.strategies.INodeStrategy;
import maze.components.ICoordinates;
import maze.components.nodes.Node;
import maze.utils.Direction;

/**
 * Interface for a node in a Hunt the Wumpus maze.
 */
public interface INode extends Node {
  /**
   * Getter for the node's ID.
   *
   * @return the node's id
   */
  Integer id();

  /**
   * Get an node by its id.
   *
   * @param id the id of the target node
   * @return the target node
   */
  INode get(int id);

  /**
   * Helper for getting a node by its id.
   *
   * @param traversed the coordinates traversed
   * @param id the target id
   * @return the target node
   */
  INode getHelper(List<ICoordinates> traversed, int id);

  /**
   * Attempts to enter the node from the specified direction. Depending on the node's strategy
   * this may either return the called node, or (in the case of bats), return another random
   * node in the maze.
   *
   * @param from the direction from which the node was entered
   * @return the entered node
   */
  INode enter(Direction from);

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
  void setStrategy(INodeStrategy strategy);

  /**
   * Takes a player object and interacts with it using the node's strategy. I.e. a wumpus strategy
   * would kill the player.
   *
   * @param player the player to receive
   */
  void receive(IPlayer player);

  boolean smelly();

  boolean drafty();
}
