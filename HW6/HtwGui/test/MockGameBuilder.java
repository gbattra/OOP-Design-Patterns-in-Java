import htw.IHtwGame;
import htw.IHtwConfiguration;
import htw.IHtwGameBuilder;

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
