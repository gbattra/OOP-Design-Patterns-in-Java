package htw.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

import htw.game.commands.CustomConfigCommand;
import htw.game.commands.ICommand;
import htw.game.commands.MoveCommand;
import htw.game.commands.NewConfigCommand;
import htw.game.commands.StartGameCommand;
import htw.game.commands.ShootCommand;
import htw.game.commands.StandardConfigCommand;
import htw.game.commands.strategies.IActionStrategy;

public class HtwController implements IController {
  private final Map<String, Function<Scanner, ICommand<IHtwGame>>> commands;
  private final Scanner scanner;
  private final Appendable out;
  private final IActionStrategy strategy;

  private IHtwGame game;
  private boolean started;

  public HtwController(Scanner scanner, Appendable out, IActionStrategy strategy) {
    this.scanner = scanner;
    this.out = out;
    this.strategy = strategy;
    this.commands = new HashMap<>() {{
      put("restart", s -> new StartGameCommand(
              s,
              out,
              new NewConfigCommand(
                      s,
                      out,
                      new CustomConfigCommand(s, out),
                      new StandardConfigCommand())));
      put("move", s -> new MoveCommand(s, out, strategy));
      put("shoot", s -> new ShootCommand(s, out, strategy));
    }};
  }

  @Override
  public int run() {
    try {
      this.out.append("Press ENTER to begin, or type 'q' or 'quit' to exit.\n");
      String next = this.scanner.nextLine();
      if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
        this.out.append("Quitting...\n");
        return 1;
      }

      this.out.append("Starting new game. You may restart at any time by entering 'restart'.\n");
      Function<Scanner, ICommand<IHtwGame>> entry = commands.get("restart");
      ICommand<IHtwGame> cmd = entry.apply(this.scanner);
      this.game = cmd.execute(this.game);
    } catch (Exception e) {
      return 0;
    }

    int status = 1;
    while (true) {
      try {
        this.out.append("Shoot or move (S, M)? ");
        String next = this.scanner.next();
        Function<Scanner, ICommand<IHtwGame>> entry = commands.get(next);
        if (entry == null) {
          this.out.append("Command not found. Try again.\n");
          continue;
        }

        ICommand<IHtwGame> cmd = entry.apply(this.scanner);
        this.game = cmd.execute(this.game);
      } catch (Exception e) {
        status = 0;
        break;
      }
    }
    return status;
  }
}
