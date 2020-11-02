package codes.application;

import java.io.IOException;

import codes.encoders.Encoder;
import codes.factories.EncoderFactory;

public class PrefixEncoderController implements EncoderController<String, String> {
  private final EncoderFactory<String, String> factory;

  private Encoder<String, String> encoder;

  public PrefixEncoderController(EncoderFactory<String, String> factory) {
    this.factory = factory;
  }

  @Override
  public boolean loadEncoder(String filename) throws IllegalArgumentException, IOException {
    this.encoder = this.factory.load(filename);
    return true;
  }

  @Override
  public boolean newEncoder(String codes, String symbols) throws IllegalArgumentException {
    this.encoder = this.factory.make(codes, symbols);
    return true;
  }

  @Override
  public boolean saveEncoder(String filename)
          throws IllegalArgumentException, IllegalStateException, IOException {
    this.encoder.save(filename);
    return true;
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException, IllegalStateException {
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is null.");
    }
    return this.encoder.encode(sequence);
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException, IllegalStateException {
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is null.");
    }
    return this.encoder.decode(sequence);
  }
}
