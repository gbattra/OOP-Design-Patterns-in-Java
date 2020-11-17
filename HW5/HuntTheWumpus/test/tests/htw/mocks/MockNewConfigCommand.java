package tests.htw.mocks;

import java.io.IOException;

import htw.game.commands.ICommand;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;

/**
 * Mock new config command.
 */
public class MockNewConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final StringBuilder log;

  /**
   * Constructor for mock.
   *
   * @param log log used for testing assertions
   */
  public MockNewConfigCommand(StringBuilder log) {
    this.log = log;
  }

  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver)
          throws IllegalArgumentException, IOException {
    this.log.append("config - execute");
    return new HtwConfigurationBuilder();
  }
}
