package htw.level.nodes;

import java.util.List;

import htw.game.IHtwPlayer;
import htw.level.strategies.IHtwNodeStrategy;
import maze.components.ICoordinates;
import maze.components.nodes.DeadEndNode;
import maze.utils.Direction;

/**
 * Equivalent of an empty node in a linked list. Helps with recursive operations when traversing
 * the maze.
 */
public class DeadEnd extends DeadEndNode implements IHtwNode {
  @Override
  public Direction directionTo(int id) throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot find direction to node from dead end.");
  }

  @Override
  public IHtwNode get(int id) throws IllegalArgumentException {
    throw new IllegalArgumentException(String.format("Cannot find target node: %s", id));
  }

  @Override
  public IHtwNode getHelper(List<ICoordinates> traversed, int id) throws IllegalArgumentException {
    throw new IllegalArgumentException(String.format("Cannot find target node: %s", id));
  }

  @Override
  public IHtwNode enter(Direction from) throws IllegalStateException {
    throw new IllegalStateException("Cannot enter a dead end node.");
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return false;
  }

  @Override
  public List<IHtwNode> neighbors() {
    throw new IllegalStateException("Dead end node has no neighbors.");
  }

  @Override
  public void setStrategy(IHtwNodeStrategy strategy) throws IllegalStateException {
    throw new IllegalStateException("Cannot set strategy on a dead end node.");
  }

  @Override
  public void receive(IHtwPlayer player) throws IllegalStateException {
    throw new IllegalStateException("Dead end node cannot receive() a player.");
  }

  @Override
  public Appendable logger() {
    throw new IllegalStateException("Dead end node has no logger.");
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

  @Override
  public Integer id() {
    return 0;
  }

  @Override
  public boolean canEnter() {
    return false;
  }
}
