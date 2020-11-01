package codes.application;

import java.io.IOException;

import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;

public class PrefixEncoderController implements EncoderController<String, String> {
  private Encoder<String, String> encoder;

  @Override
  public boolean loadEncoder(String filepath) throws IllegalArgumentException {
    if (filepath == null || filepath.isEmpty()) {
      throw new IllegalArgumentException("Filepath cannot be empty.");
    }

    try {
      this.encoder = new PrefixEncoder(filepath);
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
      this.encoder = new PrefixEncoder(codes, symbols);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
