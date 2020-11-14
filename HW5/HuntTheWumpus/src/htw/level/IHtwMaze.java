package htw.level;

import htw.game.IHtwPlayer;
import htw.tools.IHtwConfiguration;
import maze.components.IMaze;
import maze.utils.Direction;

public interface IHtwMaze {
  boolean move(Direction direction, IHtwPlayer player);
  boolean move(Integer id, IHtwPlayer player);
  boolean shoot(Direction direction, int count);
}
