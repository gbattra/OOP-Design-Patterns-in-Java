package codes.application;

import java.io.IOException;

import codes.encoders.Encoder;
import codes.factories.EncoderFactory;

/**
 * Async controller for a PrefixEncoder object. Endpoints allow for creating new encoders,
 * saving / loading encoders, and encoding / decoding sequences.
 */
public class PrefixEncoderController implements EncoderController<String, String> {
  private final EncoderFactory<String, String> factory;

  private Encoder<String, String> encoder;

  /**
   * Constructor for the controller. Takes an encoder factory to support dependency injection.
   *
   * @param factory the factory which instantiates the encoder to use
   */
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
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is not yet loaded.");
    }
    return this.encoder.save(filename);
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException, IllegalStateException {
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is not yet loaded.");
    }
    return this.encoder.encode(sequence);
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException, IllegalStateException {
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is not yet loaded.");
    }
    return this.encoder.decode(sequence);
  }
}
