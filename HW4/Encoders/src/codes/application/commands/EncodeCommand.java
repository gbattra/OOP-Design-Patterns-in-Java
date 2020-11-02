package codes.application.commands;

import java.io.IOException;

import codes.application.EncoderController;

public class EncodeCommand implements Command<EncoderController<String, String>> {
  private final String sequence;
  private final Appendable out;

  public EncodeCommand(String sequence, Appendable out) {
    this.sequence = sequence;
    this.out = out;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) throws IOException {
    try {
      String encoding = receiver.encode(this.sequence);
      this.out.append(encoding);
    } catch (Exception e) {
      this.out.append(String.format("Failed to encode sequence. %s\n", e.getMessage()));
    }
  }
}
