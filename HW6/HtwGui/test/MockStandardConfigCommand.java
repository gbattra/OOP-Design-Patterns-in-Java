import java.io.IOException;

import htw.ICommand;
import htw.HtwConfigurationBuilder;
import htw.IHtwConfigurationBuilder;

/**
 * Mock standard config command.
 */
public class MockStandardConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final StringBuilder log;

  /**
   * Constructor for mock.
   *
   * @param log log used for testing assertions
   */
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
