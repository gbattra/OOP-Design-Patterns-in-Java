package codes.application.commands;

import java.io.IOException;

import codes.application.Controller;

/**
 * A decode command for an encoder client. Calls the controller to decode a sequence.
 */
public class DecodeCommand implements Command<Controller<String, String>> {
  private final String sequence;
  private final Appendable out;

  /**
   * Constructor for the decode command. Takes the sequence to decode and an appendable to
   * write the decoding to.
   *
   * @param sequence the sequence to decode
   * @param out where to write the decoding
   */
  public DecodeCommand(String sequence, Appendable out) {
    this.sequence = sequence;
    this.out = out;
  }

  @Override
  public void execute(Controller<String, String> receiver) throws IOException {
    try {
      String decoding = receiver.decode(this.sequence);
      this.out.append(decoding).append("\n");
    } catch (Exception e) {
      this.out.append(String.format("Failed to decode sequence. %s\n", e.getMessage()));
    }
  }
}
