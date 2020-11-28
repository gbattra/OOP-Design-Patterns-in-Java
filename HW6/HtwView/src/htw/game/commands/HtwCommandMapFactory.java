package htw.game.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

import htw.game.IHtwGame;
import htw.game.IRound;

/**
 * Implementation of a command map factory.
 */
public class HtwCommandMapFactory implements ICommandMapFactory<IHtwGame> {
  @Override
  public Map<String, BiFunction<Scanner, IRound, ICommand<IHtwGame>>> create(
          Appendable out,
          IActionStrategy strategy) {
    return new HashMap<>() {{
        put("restart", (s, r) -> new StartGameCommand(
                s,
                r,
                out,
                new NewConfigCommand(
                        s,
                        out,
                        new CustomConfigCommand(s, out),
                        new StandardConfigCommand())));
        put("move", (s, r) -> new MoveCommand(s, r, out, strategy));
        put("shoot", (s, r) -> new ShootCommand(s, r, out, strategy));
      }};
  }
}
