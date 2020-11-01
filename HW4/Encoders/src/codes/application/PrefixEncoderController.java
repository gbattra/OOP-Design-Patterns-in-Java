package codes.application;

import codes.encoders.Encoder;

public class PrefixEncoderController implements EncoderController<String, String> {
  private Encoder<String, String> encoder;

  @Override
  public boolean loadEncoder(String filepath) {
    return false;
  }
}
