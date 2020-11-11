package htw;

import maze.components.nodes.Node;
import maze.utils.Direction;

public interface HTWNode extends Node {
  HTWNode enter(Direction from);
  HTWNode promote();
  boolean shoot(Direction direction, int count);
}
