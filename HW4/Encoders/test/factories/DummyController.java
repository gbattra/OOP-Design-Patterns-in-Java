package factories;

import java.io.IOException;

import codes.application.EncoderController;

public class DummyController implements EncoderController<String, String> {
  private final StringBuilder log;

  public DummyController(StringBuilder log) {
    this.log = log;
  }

  @Override
  public boolean loadEncoder(String filepath) throws IllegalArgumentException, IOException {
    this.log.append(String.format("load-%s", filepath));
    return false;
  }

  @Override
  public boolean newEncoder(String codes, String symbols) throws IllegalArgumentException {
    this.log.append(String.format("new-%s-%s", codes, symbols));
    return false;
  }

  @Override
  public boolean saveEncoder(String filename)
          throws IllegalArgumentException, IllegalStateException, IOException {
    this.log.append(String.format("save-%s", filename));
    return false;
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException, IllegalStateException {
    this.log.append(String.format("encode-%s", sequence));
    return null;
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException, IllegalStateException {
    this.log.append(String.format("decode-%s", sequence));
    return null;
  }
}
