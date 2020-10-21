package maze.demos;

import java.util.Random;
import java.util.Scanner;

import maze.enums.Direction;
import maze.interfaces.Builder;
import maze.interfaces.Game;
import maze.interfaces.Maze;
import maze.interfaces.Node;
import maze.interfaces.Player;
import maze.models.Maze2dBuilder;
import maze.models.MazeGame;
import maze.models.MazePlayer;

public class InteractiveDemo {
  private static final Random random = new Random();
  private static Scanner scanner = new Scanner(System.in);

  /**
   * Entry point to the demo.
   *
   * @param args any args passed in (unused)
   */
  public static void main(String[] args) {
    System.out.print("----------------------------------------------------\n");
    System.out.print("This is an INTERACTIVE demo of the maze program.\n");
    System.out.print("----------------------------------------------------\n");

    System.out.print("What is your name?\n");
    String name = scanner.nextLine();
    Player player = new MazePlayer(name);
    System.out.printf("Hello, %s!\n", player.getName());

    System.out.print("Configure your maze...\n");
    Builder builder = new Maze2dBuilder();
    int columnCount = readColumnCount();
    int rowCount = readRowCount();
    builder = builder.setColumnCount(columnCount).setRowCount(rowCount);

    int startRow = readStartRow(rowCount);
    int startColumn = readStartColumn(columnCount);
    builder = builder.setStart(startColumn, startRow);

    int goalColumn = readGoalColumn(columnCount);
    int goalRow = readGoalRow(rowCount);
    builder = builder.setGoal(goalColumn, goalRow);

    System.out.print("Is a room maze? (enter 'true' or 'false')\n");
    boolean isRoom = scanner.nextBoolean();
    builder = builder.setIsRoomMaze(isRoom);

    int targetEdgeCount = 0;
    if (isRoom) {
      targetEdgeCount = readTargetEdgeCount(rowCount, columnCount);
      builder.setTargetEdgeCount(targetEdgeCount);
    }

    System.out.print("Is a wrapping maze? (enter 'true' or 'false')\n");
    boolean isWrapping = scanner.nextBoolean();
    builder.setIsWrappingMaze(isWrapping);

    System.out.print("----------------------------------------------------\n");
    System.out.print("MAZE SUMMARY:\n");
    System.out.printf(
            "%s rows X %s columns\n",
            builder.getRowCount(),
            builder.getColumnCount());
    System.out.printf(
            "Start: row %s, column %s\n",
            builder.getStart().getY(), builder.getStart().getX());
    System.out.printf(
            "Goal: row %s, column %s\n",
            builder.getGoal().getY(), builder.getGoal().getX());
    if (isRoom) {
      System.out.printf("Target edge count: %s\n", builder.getTargetEdgeCount());
    }
    System.out.printf("%s a room maze\n", builder.getIsRoomMaze() ? "Is" : "Is not");
    System.out.printf("%s a wrapping maze\n", builder.getIsWrappingMaze() ? "Is" : "Is not");
    System.out.print("----------------------------------------------------\n");

    System.out.print("Building the maze...\n");
    Maze maze = builder.build();
    System.out.print("Setting up the game...\n");
    Game game = new MazeGame(player, maze);
    System.out.print("Ready to play!\n");
    game.start();

    System.out.print("----------------------------------------------------\n");
    System.out.print(playerState(game));

    while (!game.isOver()) {
      System.out.print("----------------------------------------------------\n");

      System.out.print("Move the player (type 'north', 'south', 'east', or 'west'):\n");
      Direction direction = readDirectionInput();
      System.out.printf("Moving %s\n", direction.toString());

      if (!game.movePlayer(direction)) {
        System.out.printf(
                "Cannot move %s. Wall blocking the player!\n", direction.toString());
        System.out.print(playerState(game));
        continue;
      }

      Node current = game.getMaze().getCurrent();

      if (current.isThiefRoom()) {
        System.out.print("Player encountered a thief!\n");
        System.out.printf("Gold count is now: %s gold pieces\n", game.getPlayer().getGold());
      }
      if (current.isGoldRoom()) {
        System.out.print("Player entered a room with gold!\n");
        System.out.printf("Gold count is now: %s gold pieces\n", game.getPlayer().getGold());
      }
      if (current.isGoal()) {
        System.out.print("Player reached the goal node of the maze!\n");
      }
      System.out.print(playerState(game));
    }

    System.out.print("----------------------------------------------------\n");
    System.out.print("GAME OVER\n");
    System.out.printf("Gold pieces collected: %s\n", game.getPlayer().getGold());
  }

  private static String playerState(Game game) {
    return String.format(
            "Player (%s): location - %s, gold count - %s\n",
            game.getPlayer().getName(),
            game.getMaze().getCurrent().getCoordinates().toString(),
            game.getPlayer().getGold());
  }

  private static Direction readDirectionInput() {
    Direction direction;
    while (true) {
      String input = scanner.nextLine();
      if (input.isEmpty()) {
        continue;
      }

      try {
        direction = directionFromInput(input);
        break;
      } catch (Exception e) {
        System.out.print(e.getMessage() + "\n");
        System.out.print("Try again:\n");
      }
    }

    return direction;
  }

  private static Direction directionFromInput(String input) {
    if (input.toLowerCase().equals("north")) {
      return Direction.NORTH;
    }
    if (input.toLowerCase().equals("south")) {
      return Direction.SOUTH;
    }
    if (input.toLowerCase().equals("east")) {
      return Direction.EAST;
    }
    if (input.toLowerCase().equals("west")) {
      return Direction.WEST;
    }
    throw new IllegalArgumentException("Input direction not valid.");
  }

  private static int readColumnCount() {
    int columnCount = 0;
    while (true) {
      System.out.print("Enter the number of columns:\n");
      columnCount = scanner.nextInt();
      if (columnCount <= 0 || columnCount > 50) {
        System.out.print(
                "!! Column count must be greater 0, less than or equal to 50. Try again:\n");
        continue;
      }

      break;
    }

    return columnCount;
  }

  private static int readRowCount() {
    int rowCount = 0;
    while (true) {
      System.out.print("Enter the number of rows:\n");
      rowCount = scanner.nextInt();
      if (rowCount <= 0 || rowCount > 50) {
        System.out.print(
                "!! Row count must be greater 0, less than or equal to 50. Try again:\n"
        );
        continue;
      }

      break;
    }

    return rowCount;
  }

  private static int readStartColumn(int columnCount) {
    int startCol = 0;
    while (true) {
      System.out.print("Enter the start column index (starting at zero):\n");
      startCol = scanner.nextInt();
      if (startCol < 0 || startCol > columnCount - 1) {
        System.out.printf(
                "!! Start column must be non-negative and less than %s. Try again:\n", columnCount);
        continue;
      }

      break;
    }

    return startCol;
  }

  private static int readStartRow(int rowCount) {
    int startRow = 0;
    while (true) {
      System.out.print("Enter the start row index (starting at 0):\n");
      startRow = scanner.nextInt();
      if (startRow < 0 || startRow > rowCount - 1) {
        System.out.printf(
                "!! Start row must be non-negative and less than %s. Try again:\n", rowCount);
        continue;
      }

      break;
    }

    return startRow;
  }

  private static int readGoalColumn(int columnCount) {
    int goalCol = 0;
    while (true) {
      System.out.print("Enter the goal column index (starting at zero):\n");
      goalCol = scanner.nextInt();
      if (goalCol < 0 || goalCol > columnCount - 1) {
        System.out.printf(
                "!! Goal column must be non-negative and less than %s. Try again:\n", columnCount);
        continue;
      }

      break;
    }

    return goalCol;
  }

  private static int readGoalRow(int rowCount) {
    int goalRow = 0;
    while (true) {
      System.out.print("Enter the goal row index (starting at 0):\n");
      goalRow = scanner.nextInt();
      if (goalRow < 0 || goalRow > rowCount - 1) {
        System.out.printf(
                "!! Goal row must be non-negative and less than %s. Try again:\n", rowCount);
        continue;
      }

      break;
    }

    return goalRow;
  }

  private static int readTargetEdgeCount(int rowCount, int columnCount) {
    int targetEdgeCount = 0;
    while (true) {
      System.out.print("Enter the desired number of remaining edges:\n");
      targetEdgeCount = scanner.nextInt();
      if (targetEdgeCount < 0) {
        System.out.print("!! Target edge count must be non-negative. Try again:\n");
        continue;
      }
      if (targetEdgeCount > (rowCount - 1) * (columnCount - 1)) {
        System.out.printf(
                "!! Target edge count must be less than or equal to %s. Try again:\n",
                (rowCount - 1) * (columnCount - 1));
        continue;
      }

      break;
    }

    return targetEdgeCount;
  }
}
