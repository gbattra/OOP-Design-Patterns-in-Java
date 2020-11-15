package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;

public class MoveByIdStrategy implements IMoveStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) {
    int id = in.nextInt();
    game.move(id);
  }
}
