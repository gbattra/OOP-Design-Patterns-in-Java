package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;
import htw.level.nodes.IHtwNode;

/**
 * Interface for a game action strategy.
 */
public interface IActionStrategy {
  /**
   * Shoots an arrow through the maze using user input.
   *
   * @param in where to read input
   * @param game the game model to call
   */
  void shoot(Scanner in, IHtwGame game);

  /**
   * Moves player using user input.
   *
   * @param in where to read input
   * @param game the game model to call
   */
  void move(Scanner in, IHtwGame game);

  /**
   * Prints the game status to out.
   *
   * @param curr the node to assess
   * @return a string representation of the node state
   */
  String status(IHtwNode curr);
}
