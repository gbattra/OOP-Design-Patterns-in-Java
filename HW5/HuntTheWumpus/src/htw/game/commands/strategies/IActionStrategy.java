package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;

public interface IActionStrategy {
  void shoot(Scanner in, IHtwGame game);
  void move(Scanner in, IHtwGame game);
}
