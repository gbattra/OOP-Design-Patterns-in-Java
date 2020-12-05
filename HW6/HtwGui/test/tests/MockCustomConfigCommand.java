package tests;

import java.io.IOException;

import htw.ICommand;
import htw.HtwConfigurationBuilder;
import htw.IHtwConfigurationBuilder;

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
