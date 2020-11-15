package htw.game;

import java.util.Iterator;

import maze.utils.Direction;

public interface IHtwGame {
  boolean move(Direction direction);
  boolean move(int id);
  boolean shoot(Direction direction, int count);
  boolean shoot(int id, int count);
}
