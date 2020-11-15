package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;
import maze.utils.Direction;

public class MoveByDirStrategy implements IMoveStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) {
    Direction dir = directionFromInput(in.next());
    game.move(dir);
  }

  private static Direction directionFromInput(String input) {
    if (input.toLowerCase().equals("n")) {
      return Direction.NORTH;
    }
    if (input.toLowerCase().equals("s")) {
      return Direction.SOUTH;
    }
    if (input.toLowerCase().equals("e")) {
      return Direction.EAST;
    }
    if (input.toLowerCase().equals("w")) {
      return Direction.WEST;
    }
    throw new IllegalArgumentException("Input direction not valid.");
  }
}
