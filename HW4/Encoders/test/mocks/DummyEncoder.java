package mocks;

import java.io.IOException;

import codes.encoders.PrefixEncoder;

public class DummyEncoder extends PrefixEncoder {
  private StringBuilder log;

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
    return false;
  }
}
