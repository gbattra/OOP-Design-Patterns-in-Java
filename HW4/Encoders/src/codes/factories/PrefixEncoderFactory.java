package codes.factories;

import java.io.IOException;

import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;

/**
 * Factory for instantiating prefix encoders.
 */
public class PrefixEncoderFactory implements EncoderFactory<String, String> {
  @Override
  public Encoder<String, String> make(String codes, String symbols)
          throws IllegalArgumentException {
    return new PrefixEncoder(codes, symbols);
  }

  @Override
  public Encoder<String, String> load(String filepath) throws IOException {
    return new PrefixEncoder().load(filepath);
  }
}
