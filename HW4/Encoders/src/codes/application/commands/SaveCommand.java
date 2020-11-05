package codes.application.commands;

import java.io.IOException;

import codes.application.Controller;

/**
 * Save command for an encoder client. Saves the encoder to the provided filepath.
 */
public class SaveCommand implements Command<Controller<String, String>> {
  private final String filepath;
  private final Appendable out;

  /**
   * Constructor for the save command. Takes the name of the file to save to save to and an
   * appendable to write the command output.
   *
   * @param filepath the name of the file to save to
   * @param out where to write the command output
   */
  public SaveCommand(String filepath, Appendable out) {
    this.filepath = filepath;
    this.out = out;
  }

  @Override
  public void execute(Controller<String, String> receiver) throws IOException {
    try {
      boolean success = receiver.saveEncoder(this.filepath);
      this.out.append(success ? "Encoder saved successfully.\n" : "Unable to save encoder.\n");
    } catch (Exception e) {
      this.out.append(String.format("Failed to save encoder. %s\n", e.getMessage()));
    }
  }
}
