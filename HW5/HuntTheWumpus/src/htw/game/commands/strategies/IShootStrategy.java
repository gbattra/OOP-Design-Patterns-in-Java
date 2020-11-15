package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;

public interface IShootStrategy {
  void shoot(Scanner in, IHtwGame game);
}
