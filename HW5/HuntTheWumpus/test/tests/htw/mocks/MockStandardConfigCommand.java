package tests.htw.mocks;

import java.io.IOException;

import htw.game.commands.ICommand;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;

public class MockStandardConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final StringBuilder log;

  public MockStandardConfigCommand(StringBuilder log) {
    this.log = log;
  }

  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver)
          throws IllegalArgumentException, IOException {
    this.log.append("standard - execute");
    return new HtwConfigurationBuilder();
  }
}
