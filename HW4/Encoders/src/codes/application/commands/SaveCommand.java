package codes.application.commands;

import java.io.IOException;

import codes.application.EncoderController;

public class SaveCommand implements Command<EncoderController<String, String>> {
  private final String filepath;
  private final Appendable out;

  public SaveCommand(String filepath, Appendable out) {
    this.filepath = filepath;
    this.out = out;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) throws IOException {
    try {
      boolean success = receiver.saveEncoder(this.filepath);
      this.out.append(success ? "Encoder saved successfully.\n" : "Unable to save encoder.\n");
    } catch (Exception e) {
      this.out.append(String.format("Failed to save encoder. %s\n", e.getMessage()));
    }
  }
}
