package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.game.HtwGame;
import htw.game.HtwPlayer;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.game.IRound;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfigurationBuilder;

/**
 * Command for initiliazing the game.
 */
public class StartGameCommand implements ICommand<IHtwGame> {
  private final Scanner in;
  private final IRound round;
  private final Appendable out;
  private final ICommand<IHtwConfigurationBuilder> configCmd;

  /**
   * Constructor for the command.
   *
   * @param in scanner for reading inputs
   * @param round round object to store events
   * @param out appendable for writing out logs
   * @param configCmd nested command for building the maze config
   * @throws IllegalArgumentException if params are null
   */
  public StartGameCommand(
          Scanner in,
          IRound round,
          Appendable out,
          ICommand<IHtwConfigurationBuilder> configCmd)
          throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Params cannot be null.");
    }
    this.in = in;
    this.round = round;
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
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(
            this.configCmd.execute(new HtwConfigurationBuilder())
            .build())
            .build();
    return new HtwGame(player, maze, this.out);
  }
}
