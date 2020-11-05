package codes.application.commands;

import java.io.IOException;

import codes.application.Controller;

/**
 * New command for an encoder client. Tells the controller to create a new encoder
 * from the provided codes and symbols.
 */
public class NewCommand implements Command<Controller<String, String>> {
  private final String codes;
  private final String symbols;
  private final Appendable out;

  /**
   * Constructor for the new command. Takes the codes and symbols for the encoder, as well as
   * an appendable to write the output of the command to.
   *
   * @param codes the codes for the encoder
   * @param symbols the symbols for the encoder
   * @param out where the output from the command goes
   */
  public NewCommand(String codes, String symbols, Appendable out) {
    this.codes = codes;
    this.symbols = symbols;
    this.out = out;
  }

  @Override
  public void execute(Controller<String, String> receiver) throws IOException {
    try {
      boolean success = receiver.newEncoder(this.codes, this.symbols);
      this.out.append(success ? "New encoder created.\n" : "Unable to create encoder.\n");
    } catch (IllegalArgumentException e) {
      this.out.append(String.format("Failed to create new encoder. %s\n", e.getMessage()));
    }
  }
}
