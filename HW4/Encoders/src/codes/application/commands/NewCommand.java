package codes.application.commands;

import codes.application.EncoderController;

public class NewCommand implements Command<EncoderController<String, String>> {
  private final String codes;
  private final String symbols;

  public NewCommand(String codes, String symbols) {
    this.codes = codes;
    this.symbols = symbols;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) {
    try {
      receiver.newEncoder(this.codes, this.symbols);
    } catch (IllegalArgumentException e) {
      System.out.printf("Failed to create new encoder. %s\n", e.getMessage());
    }
  }
}
