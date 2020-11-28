package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.IRound;
import htw.level.IHtwNode;

/**
 * Interface for a game action strategy.
 */
public interface IActionStrategy {
  /**
   * Shoots an arrow through the maze using user input.
   *
   * @param in where to read input
   * @param round object for tracking in-game events
   * @param game the game model to call
   */
  void shoot(Scanner in, IHtwGame game, IRound round);

  /**
   * Moves player using user input.
   *
   * @param in where to read input
   * @param round object for tracking in-game events
   * @param game the game model to call
   */
  void move(Scanner in, IHtwGame game, IRound round) throws IOException;

  /**
   * Prints the game status to out.
   *
   * @param curr the node to assess
   * @return a string representation of the node state
   */
  String status(IHtwNode curr);
}
