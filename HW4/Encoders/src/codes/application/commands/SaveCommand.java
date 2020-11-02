package codes.application.commands;

import codes.application.EncoderController;

public class SaveCommand implements Command<EncoderController<String, String>> {
  private final String filepath;

  public SaveCommand(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) {
    try {
      receiver.saveEncoder(this.filepath);
    } catch (Exception e) {
      System.out.printf("Failed to save encoder. %s\n", e.getMessage());
    }
  }
}
