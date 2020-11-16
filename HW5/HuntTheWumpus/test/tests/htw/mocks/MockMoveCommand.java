package tests.htw.mocks;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;

public class MockMoveCommand implements ICommand<IHtwGame> {
  private final StringBuffer log;
  private final Scanner scanner;

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
