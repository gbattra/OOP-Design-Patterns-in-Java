package htw.nodes;

import maze.components.nodes.Node;
import maze.utils.Direction;

public interface HTWNode extends Node {
  HTWNode enter(Direction from);
  boolean shoot(Direction direction, int count);
}
