package htw.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htw.HtwMultiPlayer;
import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

/**
 * Builder object for an HtwGame.
 */
public class HtwGameBuilder implements IHtwGameBuilder {
  private final List<String> names = new ArrayList<>(Arrays.asList("Joe", "Sally"));

  private IHtwConfiguration configuration;

  public HtwGameBuilder() {
    this.configuration = new HtwConfigurationBuilder().build();
  }

  @Override
  public IHtwGameBuilder setConfiguration(IHtwConfiguration configuration) {
    if (configuration == null) {
      throw new IllegalArgumentException("Cannot construct game builder. Configuration is null.");
    }

    this.configuration = configuration;
    return this;
  }

  @Override
  public IHtwGame build() {
    List<IHtwPlayer> players = new ArrayList<>();
    for (int i = 0; i < this.configuration.numPlayers(); i++) {
      players.add(new HtwMultiPlayer(this.names.get(i), i + 1, this.configuration.arrowCount()));
    }
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    return new HtwGame(players, maze, this.configuration.getLogger());
  }
}
