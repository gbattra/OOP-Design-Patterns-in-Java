package htw.strategies;

import htw.nodes.HtwNode;
import maze.utils.Direction;

public interface HtwNodeStrategy {
  HtwNode enter(Direction from, HtwNode curr);
  boolean shoot(Direction direction, int count, HtwNode curr);
}
