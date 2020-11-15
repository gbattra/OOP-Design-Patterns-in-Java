package htw.game.commands.strategies;

import java.util.Scanner;

import htw.game.IHtwGame;

public interface IMoveStrategy {
  void move(Scanner in, IHtwGame game);
}
