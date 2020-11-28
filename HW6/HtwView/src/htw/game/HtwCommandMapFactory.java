package htw.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import htw.game.commands.CustomConfigCommand;
import htw.game.commands.IActionStrategy;
import htw.game.commands.ICommand;
import htw.game.commands.ICommandMapFactory;
import htw.game.commands.MoveCommand;
import htw.game.commands.NewConfigCommand;
import htw.game.commands.ShootCommand;
import htw.game.commands.StandardConfigCommand;
import htw.game.commands.StartGameCommand;

/**
 * Implementation of a command map factory.
 */
public class HtwCommandMapFactory implements ICommandMapFactory<IHtwGame> {
  @Override
  public Map<String, Function<Scanner, ICommand<IHtwGame>>> create(
          Appendable out,
          IActionStrategy strategy) {
    return new HashMap<>() {{
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
}
