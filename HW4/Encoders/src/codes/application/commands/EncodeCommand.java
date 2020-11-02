package codes.application.commands;

import codes.application.EncoderController;

public class EncodeCommand implements Command<EncoderController<String, String>> {
  private final String sequence;

  public EncodeCommand(String sequence) {
    this.sequence = sequence;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) {
    try {
      receiver.encode(this.sequence);
    } catch (Exception e) {
      System.out.printf("Failed to encode sequence. %s\n", e.getMessage());
    }
  }
}
