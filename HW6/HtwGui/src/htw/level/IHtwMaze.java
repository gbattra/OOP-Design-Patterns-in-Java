package htw.level;

import java.io.IOException;

import gui.IHtwMazeVisitor;
import htw.game.IHtwPlayer;
import htw.game.commands.IActionStrategy;
import maze.Direction;
import maze.components.ICoordinates;

/**
 * Interface for an HtwMaze.
 */
public interface IHtwMaze {
  /**
   * Returns a random coordinate pair within the maze.
   *
   * @return a coordinate pair
   */
  ICoordinates randomCoordinates();

  /**
   * Returns a string representation of the maze state.
   *
   * @param player the player taking the action
   * @param strategy unique formatting for the state string
   * @return the state string
   */
  String status(IHtwPlayer player, IActionStrategy strategy);

  /**
   * Passes the player to the current node's receive() method, which operates on the player.
   *
   * @param player the player to update
   * @throws IOException if writes to out fail
   */
  void receive(IHtwPlayer player) throws IOException;

  /**
   * Visitor function for the maze.
   *
   * @param visitor the visitor of the maze
   * @param <R> the return type
   * @return R
   */
  <R> R receive(IHtwMazeVisitor<R> visitor);

  /**
   * Moves the current pointer to the cave at the specified direction.
   *
   * @param player the player taking the action
   * @param direction the direction to move
   * @return true if move was successful
   * @throws IOException if writes to out fail
   */
  boolean move(IHtwPlayer player, Direction direction) throws IOException;

  /**
   * Moves the current pointer to the cave with the specified id.
   *
   * @param player the player taking the action
   * @param id the node to move toward
   * @return true if move was successful
   * @throws IOException if writes to out fail
   */
  boolean move(IHtwPlayer player, Integer id) throws IOException;

  /**
   * Shoots an arrow to the specified direction.
   *
   * @param player the player taking the action
   * @param direction the direction shoot toward
   * @param count how many caves to traverse
   * @return true if the Wumpus was hit
   * @throws IOException if writes to out fail
   */
  boolean shoot(IHtwPlayer player, Direction direction, int count);

  /**
   * Shoots an arrow to the cave with the specified id.
   *
   * @param player the player taking the action
   * @param id the node to shoot toward
   * @param count how many caves to traverse
   * @return true if the Wumpus was hit
   * @throws IOException if writes to out fail
   */
  boolean shoot(IHtwPlayer player, int id, int count);
}
