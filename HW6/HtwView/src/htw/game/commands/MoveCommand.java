package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.IRound;

/**
 * Command for moving through the maze.
 */
public class MoveCommand implements ICommand<IHtwGame> {
  private final Scanner in;
  private final IRound round;
  private final IActionStrategy strategy;

  /**
   * Constructor for the command.
   *
   * @param in scanner for reading inputs
   * @param round an object for capturing in-game events
   * @param out appendable for writing out logs
   * @throws IllegalArgumentException if params are null
   */
  public MoveCommand(
          Scanner in,
          IRound round,
          Appendable out,
          IActionStrategy strategy) throws IllegalArgumentException {
    if (in == null || out == null || strategy == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }

    this.in = in;
    this.round = round;
    this.strategy = strategy;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    this.strategy.move(this.in, receiver, this.round);
    return receiver;
  }
}
