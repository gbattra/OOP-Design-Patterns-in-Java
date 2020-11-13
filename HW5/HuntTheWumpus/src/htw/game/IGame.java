package htw.game;

import java.util.Iterator;

import maze.utils.Direction;

public interface IGame {
  boolean move(Direction direction);
  boolean move(int id);
  boolean shoot(Direction direction, int count);
}
