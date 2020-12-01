package gui.drivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.GuiController;
import gui.GuiView;
import gui.HtwMultiPlayer;
import gui.IView;
import gui.IGuiController;
import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;

public class TestDriver {
  public static void main(String[] args) {
    IView view = new GuiView();
    IHtwConfiguration configuration = new HtwConfigurationBuilder().setLogger(view).build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    List<IHtwPlayer> players = new ArrayList<>(
            Arrays.asList(
                    new HtwMultiPlayer("Joe", 1, 10),
                    new HtwMultiPlayer("Sarah", 2, 10)));
    IHtwGame game = new HtwGame(players, maze, view);
  }
}
