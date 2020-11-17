package htw.game;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import htw.game.commands.ICommand;
import htw.game.commands.factories.ICommandMapFactory;
import htw.game.commands.strategies.IActionStrategy;

public class HtwController implements Runnable {
  private final Map<String, Function<Scanner, ICommand<IHtwGame>>> commands;
  private final Scanner scanner;
  private final Appendable out;
  private final IActionStrategy strategy;

  private IHtwGame game;
  private boolean started;

  public HtwController(
          Scanner scanner,
          Appendable out,
          IActionStrategy strategy,
          ICommandMapFactory<IHtwGame> factory)
          throws IllegalArgumentException {
    if (scanner == null || out == null || strategy == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }

    this.scanner = scanner;
    this.out = out;
    this.strategy = strategy;
    this.commands = factory.create(out, strategy);
  }

  @Override
  public void run() {
    // initialize the game
    if (this.game == null) {
      this.init();
    }

    // run the game
    while (!this.game.isOver()) {
      try {
        this.out.append("\n").append(this.game.status(strategy));
        this.out.append("\n'shoot' or 'move'? ");
        String next = this.scanner.next();
        if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
          this.out.append("Quitting...");
          break;
        }

        Function<Scanner, ICommand<IHtwGame>> entry = commands.get(next);
        if (entry == null) {
          this.out.append("Command not found. Try again.");
          continue;
        }

        ICommand<IHtwGame> cmd = entry.apply(this.scanner);
        this.game = cmd.execute(this.game);
      } catch (Exception e) {
        break;
      }
    }
  }

  private void init() {
    try {
      Function<Scanner, ICommand<IHtwGame>> entry = commands.get("restart");
      ICommand<IHtwGame> cmd = entry.apply(this.scanner);
      this.game = cmd.execute(this.game);

      this.out.append("\n").append("Starting game...");
      this.out.append("\n").append("Quit -> 'q' / 'quit'");
      this.out.append("\n").append("Restart -> 'restart'");
      this.out.append("\n");
    } catch (Exception e)  {
      throw new IllegalStateException("Failed to initialize game.");
    }
  }
}
