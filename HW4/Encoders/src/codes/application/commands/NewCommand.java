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
      boolean success = receiver.newEncoder(this.codes, this.symbols);
      this.out.append(success ? "New encoder created.\n" : "Unable to create encoder.\n");
    } catch (IllegalArgumentException e) {
      this.out.append(String.format("Failed to create new encoder. %s\n", e.getMessage()));
    }
  }
}
