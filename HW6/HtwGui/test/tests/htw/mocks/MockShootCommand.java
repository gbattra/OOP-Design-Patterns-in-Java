package tests.htw.mocks;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;

/**
 * Mock shoot command.
 */
public class MockShootCommand implements ICommand<IHtwGame> {
  private final StringBuffer log;
  private final Scanner scanner;

  /**
   * Constructor for mock.
   *
   * @param scanner for reading inputs
   * @param log log used for testing assertions
   */
  public MockShootCommand(Scanner scanner, StringBuffer log) {
    this.log = log;
    this.scanner = scanner;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    String dir = scanner.next();
    int count = scanner.nextInt();
    this.log.append(String.format("execute - shoot - %s - %s", dir, count));
    return receiver;
  }
}
