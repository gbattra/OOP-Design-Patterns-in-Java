package htw.level;

import htw.game.IHtwPlayer;
import maze.utils.Direction;

public interface IHtwMaze {
  boolean move(Direction direction, IHtwPlayer player);
  boolean move(Integer id, IHtwPlayer player);
  boolean shoot(Direction direction, int count);
}
