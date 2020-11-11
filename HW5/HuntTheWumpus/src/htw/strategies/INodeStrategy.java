package htw.strategies;

import htw.game.IPlayer;
import htw.nodes.INode;
import maze.utils.Direction;

public interface INodeStrategy {
  INode enter(Direction from, INode curr);
  boolean shoot(Direction direction, int count, INode curr);
  void receive(IPlayer player);
}
