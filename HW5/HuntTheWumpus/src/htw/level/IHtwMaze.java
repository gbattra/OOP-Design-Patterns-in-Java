package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.commands.strategies.IActionStrategy;
import maze.utils.Direction;

public interface IHtwMaze {
  String status(IActionStrategy strategy);
  boolean move(Direction direction, IHtwPlayer player) throws IOException;
  boolean move(Integer id, IHtwPlayer player) throws IOException;
  boolean shoot(Direction direction, int count);
  boolean shoot(int id, int count);
}
