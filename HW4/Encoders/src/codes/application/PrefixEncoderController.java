package codes.application;

import java.io.IOException;

import codes.encoders.Encoder;
import codes.encoders.EncoderFactory;

/**
 * Async controller for a PrefixEncoder object. Endpoints allow for creating new encoders,
 * saving / loading encoders, and encoding / decoding sequences.
 */
public class PrefixEncoderController<K, S> implements EncoderController<K, S> {
  private final EncoderFactory<K, S> factory;

  private Encoder<K, S> encoder;

  /**
   * Constructor for the controller. Takes an encoder factory to support dependency injection.
   *
   * @param factory the factory which instantiates the encoder to use
   */
  public PrefixEncoderController(EncoderFactory<K, S> factory) {
    this.factory = factory;
  }

  @Override
  public boolean loadEncoder(String filename) throws IllegalArgumentException, IOException {
    this.encoder = this.factory.load(filename);
    return true;
  }

  @Override
  public boolean newEncoder(K codes, S symbols) throws IllegalArgumentException {
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
  public K encode(S sequence) throws IllegalArgumentException, IllegalStateException {
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is not yet loaded.");
    }
    return this.encoder.encode(sequence);
  }

  @Override
  public S decode(K sequence) throws IllegalArgumentException, IllegalStateException {
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder is not yet loaded.");
    }
    return this.encoder.decode(sequence);
  }
}
