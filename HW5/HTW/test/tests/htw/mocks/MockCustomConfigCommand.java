package tests.htw.mocks;

import java.io.IOException;

import htw.game.commands.ICommand;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;

/**
 * Mock class for CustomConfigCommand.
 */
public class MockCustomConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final StringBuilder log;

  /**
   * Constructor for mock.
   *
   * @param log log used for testing assertions
   */
  public MockCustomConfigCommand(StringBuilder log) {
    this.log = log;
  }

  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver) throws IOException {
    this.log.append("custom - execute");
    return new HtwConfigurationBuilder();
  }
}
