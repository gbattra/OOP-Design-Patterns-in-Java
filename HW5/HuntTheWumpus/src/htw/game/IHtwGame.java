package htw.game;

import java.util.Iterator;
import java.util.List;

import htw.game.commands.strategies.IActionStrategy;
import maze.utils.Direction;

public interface IHtwGame {
  String status(IActionStrategy strategy);
  boolean move(Direction direction);
  boolean move(int id);
  boolean shoot(Direction direction, int count);
  boolean shoot(int id, int count);
}
