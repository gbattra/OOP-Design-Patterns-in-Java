package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.strategies.IMoveStrategy;

public class MoveCommand implements ICommand<IHtwGame> {
  private final Scanner in;
  private final Appendable out;
  private final IMoveStrategy strategy;

  public MoveCommand(
          Scanner in,
          Appendable out,
          IMoveStrategy strategy) throws IllegalArgumentException {
    if (in == null || out == null || strategy == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }

    this.in = in;
    this.out = out;
    this.strategy = strategy;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    this.out.append("Move to: ");
    this.strategy.move(this.in, receiver);
    return receiver;
  }
}
