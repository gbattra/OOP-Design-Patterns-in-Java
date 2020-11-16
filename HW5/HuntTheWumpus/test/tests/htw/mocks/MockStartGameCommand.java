package tests.htw.mocks;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;

public class MockStartGameCommand implements ICommand<IHtwGame> {
  private final StringBuffer log;
  private final Scanner scanner;

  public MockStartGameCommand(Scanner scanner, StringBuffer log) {
    this.log = log;
    this.scanner = scanner;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    String name = scanner.next();
    int arrowCount = scanner.nextInt();
    String mazeType = scanner.next();
    this.log.append(
            String.format("execute - start - %s - %s - %s", name, arrowCount, mazeType));
    return new MockGame(this.log);
  }
}
