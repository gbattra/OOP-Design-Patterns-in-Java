package htw.nodes;

import htw.game.IPlayer;
import htw.strategies.INodeStrategy;
import maze.components.nodes.DeadEndNode;
import maze.utils.Direction;

/**
 * Equivalent of an empty node in a linked list. Helps with recursive operations when traversing
 * the maze.
 */
public class DeadEnd extends DeadEndNode implements INode {
  @Override
  public INode enter(Direction from) throws IllegalStateException {
    throw new IllegalStateException("Cannot enter a dead end node.");
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return false;
  }

  @Override
  public void setStrategy(INodeStrategy strategy) throws IllegalStateException {
    throw new IllegalStateException("Cannot set strategy on a dead end node.");
  }

  @Override
  public void receive(IPlayer player) throws IllegalStateException {
    throw new IllegalStateException("Dead end node cannot receive() a player.");
  }

  @Override
  public String toString() {
    return "Dead End";
  }

  @Override
  public boolean drafty() {
    return false;
  }

  @Override
  public boolean smelly() {
    return false;
  }
}
