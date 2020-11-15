package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;
import maze.utils.Direction;

public class MoveByDirStrategy implements IMoveStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) {
    Direction dir = Direction.stringToDirection(in.next());
    game.move(dir);
  }
}
