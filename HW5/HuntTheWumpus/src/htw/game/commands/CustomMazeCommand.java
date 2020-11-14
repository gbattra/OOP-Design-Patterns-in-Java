package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;

public class CustomMazeCommand implements ICommand<IHtwGame> {
  private final Appendable out;
  private final Scanner in;

  public CustomMazeCommand(Scanner in, Appendable out) throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("In and out cannot be null.");
    }

    this.in = in;
    this.out = out;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    if (receiver == null) {
      throw new IllegalArgumentException("Receiver cannot be null.");
    }
    return null;
  }
}
