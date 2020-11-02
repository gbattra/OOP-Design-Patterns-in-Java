package codes.application.commands;

import codes.application.EncoderController;

public class DecodeCommand implements Command<EncoderController<String, String>> {
  private final String sequence;

  public DecodeCommand(String sequence) {
    this.sequence = sequence;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) {
    try {
      receiver.decode(this.sequence);
    } catch (Exception e) {
      System.out.printf("Failed to decode sequence. %s\n", e.getMessage());
    }
  }
}
