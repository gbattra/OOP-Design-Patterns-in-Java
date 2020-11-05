package codes.application.commands;

import java.io.IOException;

import codes.application.Controller;

/**
 * An encode command for an encoder client. Calls the controller to encode a sequence.
 */
public class EncodeCommand implements Command<Controller<String, String>> {
  private final String sequence;
  private final Appendable out;

  /**
   * Constructor for the encode command. Takes the sequence to encode and an appendable to write
   * the encoding to.
   *
   * @param sequence the sequence to encode
   * @param out where to write the encoding
   */
  public EncodeCommand(String sequence, Appendable out) {
    this.sequence = sequence;
    this.out = out;
  }

  @Override
  public void execute(Controller<String, String> receiver) throws IOException {
    try {
      String encoding = receiver.encode(this.sequence);
      this.out.append(encoding).append("\n");
    } catch (Exception e) {
      this.out.append(String.format("Failed to encode sequence. %s\n", e.getMessage()));
    }
  }
}
