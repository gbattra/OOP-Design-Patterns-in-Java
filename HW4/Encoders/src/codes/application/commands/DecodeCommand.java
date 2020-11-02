package codes.application.commands;

import java.io.IOException;

import codes.application.EncoderController;

public class DecodeCommand implements Command<EncoderController<String, String>> {
  private final String sequence;
  private final Appendable out;

  public DecodeCommand(String sequence, Appendable out) {
    this.sequence = sequence;
    this.out = out;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) throws IOException {
    try {
      String decoding = receiver.decode(this.sequence);
      this.out.append(decoding);
    } catch (Exception e) {
      this.out.append(String.format("Failed to decode sequence. %s\n", e.getMessage()));
    }
  }
}
