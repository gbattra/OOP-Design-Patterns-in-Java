package mocks;

import java.io.IOException;

import codes.encoders.PrefixEncoder;

/**
 * A mock encoder for testing.
 */
public class DummyEncoder extends PrefixEncoder {
  private final StringBuilder log;

  /**
   * Constructor takes a string builder object to populate when its methods are called.
   * @param log the log to maintain
   */
  public DummyEncoder(StringBuilder log) {
    this.log = log;
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException {
    return sequence;
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException {
    return sequence;
  }

  @Override
  public boolean save(String filepath) throws IOException {
    this.log.setLength(0);
    this.log.append(filepath);
    return true;
  }
}
