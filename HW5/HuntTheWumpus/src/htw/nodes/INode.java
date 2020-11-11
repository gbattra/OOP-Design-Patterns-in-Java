package htw.nodes;

import htw.strategies.INodeStrategy;
import maze.components.nodes.Node;
import maze.utils.Direction;

public interface INode extends Node {
  INode enter(Direction from);
  boolean shoot(Direction direction, int count);
  void setStrategy(INodeStrategy strategy);
}
