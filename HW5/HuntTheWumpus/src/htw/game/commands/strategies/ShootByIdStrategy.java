package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;

public class ShootByIdStrategy implements IShootStrategy {
  @Override
  public void shoot(Scanner in, IHtwGame game) {
    int id = in.nextInt();
    int count = in.nextInt();
    game.shoot(id, count);
  }
}
