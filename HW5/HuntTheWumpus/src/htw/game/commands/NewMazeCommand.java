package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.game.HtwPlayer;
import htw.level.IHtwMaze;
import htw.tools.HtwConfiguration;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwConfigurationBuilder;
import htw.tools.IHtwMazeBuilder;
import maze.components.IMaze;

public class NewMazeCommand implements ICommand<IHtwMazeBuilder> {
  private final Appendable out;
  private final Scanner in;
  private final ICommand<IHtwConfigurationBuilder> customCmd;
  private final ICommand<IHtwConfigurationBuilder> standardCmd;

  public NewMazeCommand(
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
  public IHtwMazeBuilder execute(IHtwMazeBuilder receiver) throws IllegalArgumentException, IOException {
    this.out.append("Maze type ('standard' or 'custom'): ");
    String gameType = this.in.next();
    IHtwConfiguration configuration;
    if (gameType.equalsIgnoreCase("standard")) {
      configuration = this.standardCmd.execute(new HtwConfigurationBuilder()).build();
    } else {
      configuration = this.customCmd.execute(new HtwConfigurationBuilder()).build();
    }

    return new HtwMazeBuilder(configuration);
  }
}
