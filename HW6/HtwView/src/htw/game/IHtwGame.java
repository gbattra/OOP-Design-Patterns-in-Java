package htw.game;

import java.io.IOException;
import java.util.Iterator;

import htw.game.commands.IActionStrategy;
import maze.Direction;

/**
 * Interface for an instance of a Hunt the Wumpus game.
 */
public interface IHtwGame extends Iterator<IRound> {
  /**
   * Computes a string representation of the game state.
   *
   * @param strategy a strategy for how to compute the game state
   * @return a string representation of the game state
   */
  String status(IActionStrategy strategy);

  /**
   * Is the game over? (i.e. is the player dead, out of arrows or is the Wumpus slain?)
   *
   * @return true if game over
   */
  boolean isOver();

  /**
   * Moves the player to the cave at the specified direction.
   *
   * @param direction the direction to move
   * @param round round object to store events
   * @return true if move was successful
   */
  boolean move(Direction direction, IRound round) throws IOException;

  /**
   * Moves the player to the cave with the specified id.
   *
   * @param id the node to move toward
   * @param round round object to store events
   * @return true if move was successful
   */
  boolean move(int id, IRound round);

  /**
   * Shoots an arrow to the specified direction.
   *
   * @param direction the direction shoot toward
   * @param count how many caves to traverse
   * @return true if the Wumpus was hit
   */
  boolean shoot(Direction direction, int count);

  /**
   * Shoots an arrow to the cave with the specified id.
   *
   * @param id the node to shoot toward
   * @param count how many caves to traverse
   * @return true if the Wumpus was hit
   */
  boolean shoot(int id, int count);
}
