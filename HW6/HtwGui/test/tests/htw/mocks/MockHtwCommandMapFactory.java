package tests.htw.mocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import htw.game.IHtwGame;
import htw.game.commands.IActionStrategy;
import htw.game.commands.ICommand;
import htw.game.commands.ICommandMapFactory;

/**
 * Mock class for HtwCommandMapFactory.
 */
public class MockHtwCommandMapFactory implements ICommandMapFactory<IHtwGame> {
  private final StringBuffer log;
  private final Scanner scanner;

  /**
   * Constructor for mock.
   *
   * @param scanner for reading inputs
   * @param log log used for testing assertions
   */
  public MockHtwCommandMapFactory(Scanner scanner, StringBuffer log) {
    this.log = log;
    this.scanner = scanner;
  }

  @Override
  public Map<String, Function<Scanner, ICommand<IHtwGame>>> create(
          Appendable out,
          IActionStrategy strategy) {
    return new HashMap<String, Function<Scanner, ICommand<IHtwGame>>>() {{
        put("restart", s -> new MockStartGameCommand(scanner, log));
        put("move", s -> new MockMoveCommand(scanner, log));
        put("shoot", s -> new MockShootCommand(scanner, log));
      }};
  }
}
