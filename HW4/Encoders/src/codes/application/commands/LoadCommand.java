package codes.application.commands;

import codes.application.EncoderController;

public class LoadCommand implements Command<EncoderController<String, String>> {
  private final String filepath;

  public LoadCommand(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) {
    try {
      receiver.loadEncoder(this.filepath);
    } catch (Exception e) {
      System.out.printf("Failed to load encoder. %s\n", e.getMessage());
    }
  }
}
