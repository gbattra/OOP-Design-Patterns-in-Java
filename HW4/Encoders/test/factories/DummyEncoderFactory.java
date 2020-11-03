package factories;

import java.io.IOException;

import codes.encoders.Encoder;
import codes.factories.EncoderFactory;
import mocks.DummyEncoder;

/**
 * Mock encoder factory for testing.
 */
public class DummyEncoderFactory implements EncoderFactory<String, String> {
  private final StringBuilder log;

  /**
   * Constructor takes a string builder object to populate when its methods are called.
   * @param log the log to maintain
   */
  public DummyEncoderFactory(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Encoder<String, String> load(String filepath) throws IOException {
    this.log.append(filepath);
    return new DummyEncoder(new StringBuilder());
  }

  @Override
  public Encoder<String, String> make(String codes, String symbols)
          throws IllegalArgumentException {
    this.log.append(codes).append(symbols);
    return new DummyEncoder(this.log);
  }
}
