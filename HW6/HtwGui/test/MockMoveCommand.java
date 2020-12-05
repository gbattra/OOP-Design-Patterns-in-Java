import java.io.IOException;
import java.util.Scanner;

import htw.IHtwGame;
import htw.ICommand;

/**
 * Mock move command.
 */
public class MockMoveCommand implements ICommand<IHtwGame> {
  private final StringBuffer log;
  private final Scanner scanner;

  /**
   * Constructor for mock.
   *
   * @param scanner for reading inputs
   * @param log log used for testing assertions
   */
  public MockMoveCommand(Scanner scanner, StringBuffer log) {
    this.log = log;
    this.scanner = scanner;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    String dir = this.scanner.next();
    this.log.append(String.format("execute - move - %s", dir));
    return receiver;
  }
}
