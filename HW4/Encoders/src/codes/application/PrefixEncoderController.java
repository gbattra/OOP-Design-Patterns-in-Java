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
  public boolean loadEncoder(String filename) throws IllegalArgumentException {
    if (filename == null || filename.isEmpty()) {
      throw new IllegalArgumentException("Filename cannot be empty.");
    }

    try {
      this.encoder = this.factory.load(filename);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  @Override
  public boolean newEncoder(String codes, String symbols) throws IllegalArgumentException {
    if (codes == null || codes.isEmpty() || symbols == null || symbols.isEmpty()) {
      throw new IllegalArgumentException("Codes and symbols cannot be empty.");
    }

    try {
      this.encoder = this.factory.make(codes, symbols);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean saveEncoder(String filename)
          throws IllegalArgumentException, IllegalStateException {
    if (filename == null || filename.isEmpty()) {
      throw new IllegalArgumentException("Filename cannot be empty.");
    }
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder not yet loaded.");
    }

    try {
      this.encoder.save(filename);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException, IllegalStateException {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException("Sequence cannot be empty.");
    }
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder not yet loaded for use.");
    }

    return this.encoder.encode(sequence);
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException, IllegalStateException {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException("Sequence cannot be empty.");
    }
    if (this.encoder == null) {
      throw new IllegalStateException("Encoder not yet loaded for use.");
    }

    return this.encoder.decode(sequence);
  }
}
