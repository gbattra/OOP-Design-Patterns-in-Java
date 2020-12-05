package htw;

import java.io.IOException;
import java.util.Scanner;

/**
 * Command for creating a new configuration for the maze.
 */
public class NewConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final Appendable out;
  private final Scanner in;
  private final ICommand<IHtwConfigurationBuilder> customCmd;
  private final ICommand<IHtwConfigurationBuilder> standardCmd;

  /**
   * Constructor for the command.
   *
   * @param in scanner for reading inputs
   * @param out appendable for writing out logs
   * @param customCmd command for building a custom config
   * @param standardCmd command for building a standard config
   * @throws IllegalArgumentException if params are null
   */
  public NewConfigCommand(
          Scanner in,
          Appendable out,
          ICommand<IHtwConfigurationBuilder> customCmd,
          ICommand<IHtwConfigurationBuilder> standardCmd)
          throws IllegalArgumentException {
    if (in == null || out == null || customCmd == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }
    this.out = out;
    this.in = in;
    this.customCmd = customCmd;
    this.standardCmd = standardCmd;
  }

  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver)
          throws IllegalArgumentException, IOException {
    this.out.append("Maze type ('standard' or 'custom'): ");
    String gameType = this.in.next();
    if (gameType.equalsIgnoreCase("standard")) {
      return this.standardCmd.execute(new HtwConfigurationBuilder().setLogger(this.out));
    } else {
      return this.customCmd.execute(new HtwConfigurationBuilder().setLogger(this.out));
    }
  }
}
