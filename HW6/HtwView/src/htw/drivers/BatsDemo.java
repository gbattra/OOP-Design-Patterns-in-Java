package htw.drivers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import htw.game.HtwController;
import htw.game.HtwGame;
import htw.game.HtwPlayer;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.game.HtwCommandMapFactory;
import htw.game.commands.DirActionStrategy;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;

/**
 * Bats demo driver.
 */
public class BatsDemo {
  /**
   * Main driver method.
   *
   * @param args args for running the program
   */
  public static void main(String[] args) {
    IHtwConfiguration configuration = (IHtwConfiguration) new HtwConfigurationBuilder()
                                                              .setBatFrequency(0.5)
                                                              .setPitFrequency(0.5)
                                                              .setRowCount(5).setColumnCount(5)
                                                              .setRandomSeed(1).build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    IHtwPlayer player = new HtwPlayer("Joe", 10);
    List<IHtwPlayer> players = new ArrayList<>(Collections.singletonList(player));
    IHtwGame game = new HtwGame(players, maze, System.out);
    Runnable controller = new HtwController(
            new Scanner("move s q"),
            System.out,
            new DirActionStrategy(),
            new HtwCommandMapFactory(),
            game);
    controller.run();
  }
}
