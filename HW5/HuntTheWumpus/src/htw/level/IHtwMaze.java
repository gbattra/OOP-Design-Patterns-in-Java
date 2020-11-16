package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.commands.strategies.IActionStrategy;
import maze.utils.Direction;

public interface IHtwMaze {
  String status(IActionStrategy strategy);
  void receive(IHtwPlayer player) throws IOException;
  boolean move(Direction direction) throws IOException;
  boolean move(Integer id) throws IOException;
  boolean shoot(Direction direction, int count);
  boolean shoot(int id, int count);
}
