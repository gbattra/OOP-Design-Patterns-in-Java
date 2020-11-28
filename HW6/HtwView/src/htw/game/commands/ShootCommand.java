package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.IRound;

/**
 * Command for shooting an arrow in the game.
 */
public class ShootCommand implements ICommand<IHtwGame> {
  private final Scanner in;
  private final IRound round;
  private final IActionStrategy strategy;

  /**
   * Constructor for the command.
   *
   * @param in scanner for reading inputs
   * @param round object for tracking in-game events
   * @param out appendable for writing out logs
   * @throws IllegalArgumentException if params are null
   */
  public ShootCommand(
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
    this.strategy.shoot(this.in, receiver, this.round);
    return receiver;
  }
}
