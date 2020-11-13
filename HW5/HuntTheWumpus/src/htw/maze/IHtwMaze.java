package htw.maze;

import htw.game.IPlayer;
import maze.utils.Direction;

public interface IHtwMaze {
  boolean move(Direction direction, IPlayer player);
  boolean move(Integer id, IPlayer player);
  boolean shoot(Direction direction, int count);
}
