package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.strategies.IActionStrategy;

public class ShootCommand implements ICommand<IHtwGame> {
  private final Scanner in;
  private final Appendable out;
  private final IActionStrategy strategy;

  public ShootCommand(
          Scanner in,
          Appendable out,
          IActionStrategy strategy) throws IllegalArgumentException {
    if (in == null || out == null || strategy == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }

    this.in = in;
    this.out = out;
    this.strategy = strategy;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    this.out.append("Direction and count:\n");
    this.strategy.shoot(this.in, receiver);
    return receiver;
  }
}
