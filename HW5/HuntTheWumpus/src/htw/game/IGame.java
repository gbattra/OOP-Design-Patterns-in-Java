package htw.game;

import java.util.Iterator;

import maze.utils.Direction;

public interface IGame extends Iterator<IRound> {
  boolean move(Direction direction, IRound round);
  boolean move(int id, IRound round);
  boolean shoot(Direction direction, IRound round);
}
