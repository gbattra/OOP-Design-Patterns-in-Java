package htw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Implementation of a command map factory.
 */
public class HtwCommandMapFactory implements ICommandMapFactory<IHtwGame> {
  @Override
  public Map<String, Function<Scanner, ICommand<IHtwGame>>> create(
          Appendable out,
          IActionStrategy strategy) {
    return new HashMap<String, Function<Scanner, ICommand<IHtwGame>>>() {{
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
