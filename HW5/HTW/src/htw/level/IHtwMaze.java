package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.commands.IActionStrategy;
import maze.Direction;

/**
 * Interface for an HtwMaze.
 */
public interface IHtwMaze {
  /**
   * Returns a string representation of the maze state.
   *
   * @param strategy unique formatting for the state string
   * @return the state string
   */
  String status(IActionStrategy strategy);

  /**
   * Passes the player to the current node's receive() method, which operates on the player.
   *
   * @param player the player to update
   * @throws IOException if writes to out fail
   */
  void receive(IHtwPlayer player) throws IOException;

  /**
   * Moves the current pointer to the cave at the specified direction.
   *
   * @param direction the direction to move
   * @return true if move was successful
   * @throws IOException if writes to out fail
   */
  boolean move(Direction direction) throws IOException;

  /**
   * Moves the current pointer to the cave with the specified id.
   *
   * @param id the node to move toward
   * @return true if move was successful
   * @throws IOException if writes to out fail
   */
  boolean move(Integer id) throws IOException;

  /**
   * Shoots an arrow to the specified direction.
   *
   * @param direction the direction shoot toward
   * @param count how many caves to traverse
   * @return true if the Wumpus was hit
   * @throws IOException if writes to out fail
   */
  boolean shoot(Direction direction, int count);

  /**
   * Shoots an arrow to the cave with the specified id.
   *
   * @param id the node to shoot toward
   * @param count how many caves to traverse
   * @return true if the Wumpus was hit
   * @throws IOException if writes to out fail
   */
  boolean shoot(int id, int count);
}
