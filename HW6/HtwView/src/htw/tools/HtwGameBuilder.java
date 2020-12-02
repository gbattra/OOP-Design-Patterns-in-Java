package htw.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htw.HtwMultiPlayer;
import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

public class HtwGameBuilder implements IHtwGameBuilder {
  private final IHtwConfiguration configuration;
  private final List<String> names = new ArrayList<>(Arrays.asList("Joe", "Sally"));

  public HtwGameBuilder(IHtwConfiguration configuration) {
    if (configuration == null) {
      throw new IllegalArgumentException("Cannot construct game builder. Configuration is null.");
    }

    this.configuration = configuration;
  }

  @Override
  public IHtwGame build() {
    List<IHtwPlayer> players = new ArrayList<>();
    for (int i = 0; i < this.configuration.numPlayers(); i++) {
      players.add(new HtwMultiPlayer(this.names.get(i), i, this.configuration.arrowCount()));
    }
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    return new HtwGame(players, maze, this.configuration.getLogger());
  }
}
