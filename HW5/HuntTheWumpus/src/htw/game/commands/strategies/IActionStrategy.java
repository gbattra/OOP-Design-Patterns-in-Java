package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;
import htw.level.nodes.IHtwNode;

public interface IActionStrategy {
  void shoot(Scanner in, IHtwGame game);
  void move(Scanner in, IHtwGame game);
  String status(IHtwNode curr);
}
