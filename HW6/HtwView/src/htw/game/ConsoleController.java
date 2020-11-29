package htw.game;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import htw.game.commands.IActionStrategy;
import htw.game.commands.ICommand;
import htw.game.commands.ICommandMapFactory;

/**
 * Controller for a Hunt the Wumpus game.
 */
public class ConsoleController implements Runnable {
  private final Map<String, Function<Scanner, ICommand<IHtwGame>>> commands;
  private final Scanner scanner;
  private final Appendable out;
  private final IActionStrategy strategy;

  private IHtwGame game;

  /**
   * Constructor for the controller.
   *
   * @param scanner reads user inputs
   * @param out writes logs to this appendable
   * @param strategy the strategy / controller mode to use
   * @param factory generates the command map for the controller
   * @throws IllegalArgumentException if params are null
   */
  public ConsoleController(
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

  /**
   * Secondary constructor for the controller. Allows setting the game manually.
   *
   * @param scanner reads user inputs
   * @param out writes logs to this appendable
   * @param strategy the strategy / controller mode to use
   * @param factory generates the command map for the controller
   * @param game the game instance to use
   * @throws IllegalArgumentException if params are null
   */
  public ConsoleController(
          Scanner scanner,
          Appendable out,
          IActionStrategy strategy,
          ICommandMapFactory<IHtwGame> factory,
          IHtwGame game)
          throws IllegalArgumentException {
    if (scanner == null || out == null || strategy == null || game == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }

    this.scanner = scanner;
    this.out = out;
    this.strategy = strategy;
    this.commands = factory.create(out, strategy);
    this.game = game;
  }

  @Override
  public void run() {
    // initialize the game
    if (this.game == null) {
      this.init();
    }

    // run the game
    while (this.game.hasNext()) {
      try {
        this.game.next();
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
      } catch (IllegalStateException | IllegalArgumentException | IOException e) {
        try {
          this.out.append(e.getMessage());
        } catch (IOException ex) {
          break;
        }
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
