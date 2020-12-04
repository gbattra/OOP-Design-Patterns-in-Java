package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.level.IHtwNode;

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
  void shoot(Scanner in, IHtwGame game) throws IOException;

  /**
   * Moves player using user input.
   *
   * @param in where to read input
   * @param game the game model to call
   */
  void move(Scanner in, IHtwGame game) throws IOException;

  /**
   * Prints the game status to out.
   *
   * @param playerNumber the no. of the active player in the game
   * @param curr the node to assess
   * @return a string representation of the node state
   */
  String status(int playerNumber, IHtwNode curr);
}
