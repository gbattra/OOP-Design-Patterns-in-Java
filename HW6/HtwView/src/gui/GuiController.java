package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import htw.HtwMultiPlayer;
import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwConfigurationBuilder;

public class GuiController implements IGuiController {
  private final IView view;
  private final Random random = new Random();

  public GuiController(IView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiController. View is null.");
    }

    this.view = view;
    this.view.setFeatures(this);
  }

  @Override
  public void start() {
    IHtwConfiguration configuration = ((IHtwConfigurationBuilder) new HtwConfigurationBuilder()
            .setLogger(view)
            .setRandomSeed(this.random.nextInt()))
            .build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    List<IHtwPlayer> players = new ArrayList<>(
            Arrays.asList(
                    new HtwMultiPlayer("Joe", 1, 10),
                    new HtwMultiPlayer("Sarah", 2, 10)));
    IHtwGame game = new HtwGame(players, maze, view);
    game.start();
    this.view.populate(game);
  }
}
