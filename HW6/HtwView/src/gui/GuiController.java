package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;

public class GuiController implements IGuiController {
  private final IView view;

  public GuiController(IView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiController. View is null.");
    }

    this.view = view;
    this.view.setFeatures(this);
  }

  @Override
  public void start() {
    IHtwConfiguration configuration = new HtwConfigurationBuilder().setLogger(view).build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    List<IHtwPlayer> players = new ArrayList<>(
            Arrays.asList(
                    new HtwMultiPlayer("Joe", 1, 10),
                    new HtwMultiPlayer("Sarah", 2, 10)));
    IHtwGame game = new HtwGame(players, maze, this.view);
    this.view.populate(game);
  }
}
