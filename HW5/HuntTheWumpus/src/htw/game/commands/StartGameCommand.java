package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.HtwGame;
import htw.game.HtwPlayer;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfigurationBuilder;

public class StartGameCommand implements ICommand<IHtwGame> {
  private final Scanner in;
  private final Appendable out;
  private final ICommand<IHtwConfigurationBuilder> configCmd;

  public StartGameCommand(
          Scanner in,
          Appendable out,
          ICommand<IHtwConfigurationBuilder> configCmd)
          throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }
    this.in = in;
    this.out = out;
    this.configCmd = configCmd;
  }

  @Override
  public IHtwGame execute(IHtwGame receiver) throws IllegalArgumentException, IOException {
    this.out.append("Player name (first only): ");
    String name = this.in.next();
    this.out.append("Starting arrow count: ");
    int arrowCount = this.in.nextInt();
    IHtwPlayer player = new HtwPlayer(name, arrowCount);
    this.out.append("Configure maze:\n");
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(
            this.configCmd.execute(new HtwConfigurationBuilder())
            .build())
            .build();
    return new HtwGame(player, maze);
  }
}
