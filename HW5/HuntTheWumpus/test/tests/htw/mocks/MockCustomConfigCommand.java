package tests.htw.mocks;

import java.io.IOException;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;

public class MockCustomConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final StringBuilder log;

  public MockCustomConfigCommand(StringBuilder log) {
    this.log = log;
  }

  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver) throws IOException {
    this.log.append("custom - execute");
    return new HtwConfigurationBuilder();
  }
}
