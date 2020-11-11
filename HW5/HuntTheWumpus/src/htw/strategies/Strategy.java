package htw.strategies;

import htw.nodes.HTWNode;
import maze.utils.Direction;

public interface Strategy {
  HTWNode enter(Direction from, HTWNode curr);
  boolean shoot(Direction direction, int count, HTWNode curr);
}
