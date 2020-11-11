package htw.nodes;

import htw.strategies.HtwNodeStrategy;
import maze.components.nodes.Node;
import maze.utils.Direction;

public interface HtwNode extends Node {
  HtwNode enter(Direction from);
  boolean shoot(Direction direction, int count);
  void setStrategy(HtwNodeStrategy strategy);
}
