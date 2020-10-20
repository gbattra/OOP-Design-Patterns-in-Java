package maze.demos;

import java.util.List;
import java.util.Random;

import maze.interfaces.Builder;
import maze.interfaces.Coordinates;
import maze.interfaces.Maze;
import maze.interfaces.Node;
import maze.interfaces.Path;
import maze.interfaces.Player;
import maze.models.Maze2dBuilder;
import maze.models.MazePlayer;

public class WrappingMazeDemo {
  private static final Random random = new Random();

  /**
   * Entry point to the demo.
   *
   * @param args any args passed in (unused)
   */
  public static void main(String[] args) {
    System.out.print("----------------------------------------------------\n");
    System.out.print("This demo is of a WRAPPING ROOM maze.\n");
    System.out.print("----------------------------------------------------\n");
    System.out.print("Building maze...\n");
    Builder builder = new Maze2dBuilder()
            .setColumnCount(7)
            .setRowCount(7)
            .setStart(0, 0)
            .setGoal(6, 6)
            .setGoldFrequency(0.2)
            .setThiefFrequency(0.1)
            .setIsWrappingMaze(true)
            .setIsRoomMaze(true)
            .setTargetEdgeCount(4);
    Maze wrappingMaze = builder.build();
    Player player = new MazePlayer("Joey");

    System.out.print("Finding path to goal...\n");
    Path path = wrappingMaze.pathTo(wrappingMaze.getGoal().getCoordinates());
    List<Coordinates> coordinates = path.getCoordinatesTraversed();

    System.out.print("Executing path...\n");
    while (!coordinates.isEmpty()) {
      System.out.print("----------------------------------------------------\n");
      Coordinates c = coordinates.get(0);
      coordinates.remove(0);
      System.out.printf(
              "Player: location - %s, gold count - %s\n", c.toString(), player.getGold());

      Node current = wrappingMaze.getStart().get(c);
      player = player.loot(current);

      if (current.isThiefRoom()) {
        System.out.print("Player encountered a thief!\n");
        System.out.printf("Gold count is now: %s gold pieces\n", player.getGold());
      }
      if (current.isGoldRoom()) {
        System.out.print("Player entered a room with gold!\n");
        System.out.printf("Gold count is now: %s gold pieces\n", player.getGold());
      }
      if (current.isGoal()) {
        System.out.print("Player reached the goal node of the maze!\n");
      }
    }

    System.out.print("----------------------------------------------------\n");
    System.out.print("GAME OVER\n");
    System.out.printf("Gold pieces collected: %s\n", player.getGold());
  }
}
