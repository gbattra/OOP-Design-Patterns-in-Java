package tests.gui.mocks;

import htw.game.IHtwGame;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwGameBuilder;
import tests.htw.mocks.MockGame;

/**
 * Mock game builder for testing.
 */
public class MockGameBuilder implements IHtwGameBuilder {
  private final StringBuffer log;

  /**
   * Constructor.
   *
   * @param log log for testing
   */
  public MockGameBuilder(StringBuffer log) {
    this.log = log;
  }

  @Override
  public IHtwGameBuilder setConfiguration(IHtwConfiguration configuration) {
    this.log.append("Added configuration\n");
    return this;
  }

  @Override
  public IHtwGame build() {
    return new MockGame(this.log);
  }
}
