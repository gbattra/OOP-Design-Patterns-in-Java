package codes.application.commands;

import java.io.IOException;

import codes.application.EncoderController;

public class LoadCommand implements Command<EncoderController<String, String>> {
  private final String filepath;
  private final Appendable out;

  public LoadCommand(String filepath, Appendable out) {
    this.filepath = filepath;
    this.out = out;
  }

  @Override
  public void execute(EncoderController<String, String> receiver) throws IOException {
    try {
      receiver.loadEncoder(this.filepath);

    } catch (Exception e) {
      this.out.append(String.format("Failed to load encoder. %s\n", e.getMessage()));
    }
  }
}
