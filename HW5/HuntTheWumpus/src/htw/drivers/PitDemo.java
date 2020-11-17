package htw.drivers;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.HtwGame;
import htw.game.HtwPlayer;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.game.commands.factories.HtwCommandMapFactory;
import htw.game.commands.strategies.DirActionStrategy;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;

public class PitDemo {
  public static void main(String[] args) {
    IHtwConfiguration configuration = (IHtwConfiguration) new HtwConfigurationBuilder()
            .setBatFrequency(0.5)
            .setPitFrequency(0.95)
            .setRowCount(5).setColumnCount(5)
            .setRandomSeed(2).build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(configuration).build();
    IHtwPlayer player = new HtwPlayer("Joe", 10);
    IHtwGame game = new HtwGame(player, maze, System.out);
    Runnable controller = new HtwController(
            new Scanner("move s move e move n q"),
            System.out,
            new DirActionStrategy(),
            new HtwCommandMapFactory(),
            game);
    controller.run();
  }
}
