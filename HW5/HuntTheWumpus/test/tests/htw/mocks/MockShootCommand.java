package tests.htw.mocks;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;

public class MockShootCommand implements ICommand<IHtwGame> {
  private final StringBuffer log;
  private final Scanner scanner;

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
