package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;
import maze.utils.Direction;

public class ShootByDirStrategy implements IShootStrategy {
  @Override
  public void shoot(Scanner in, IHtwGame game) {
    Direction dir = Direction.stringToDirection(in.next());
    int count = in.nextInt();
    game.shoot(dir, count);
  }
}
