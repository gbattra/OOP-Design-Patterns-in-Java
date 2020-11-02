package codes.application.commands;

import java.io.IOException;

import codes.application.EncoderController;

public class NewCommand implements Command<EncoderController<String, String>> {
  private final String codes;
  private final String symbols;
  private final Appendable out;

  public NewCommand(String codes, String symbols, Appendable out) {
    this.codes = codes;
    this.symbols = symbols;
    this.out = out;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) throws IOException {
    try {
      receiver.newEncoder(this.codes, this.symbols);
    } catch (IllegalArgumentException e) {
      this.out.append(String.format("Failed to create new encoder. %s\n", e.getMessage()));
    }
  }
}
