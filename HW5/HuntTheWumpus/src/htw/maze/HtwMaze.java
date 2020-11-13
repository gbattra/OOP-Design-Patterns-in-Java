package htw.maze;

import htw.game.IPlayer;
import htw.maze.nodes.INode;
import maze.utils.Direction;

public class HtwMaze implements IHtwMaze {
  private final INode root;
  private INode current;

  public HtwMaze(INode root) throws IllegalArgumentException {
    if (root == null) {
      throw new IllegalArgumentException("Root cannot be null.");
    }
    this.root = root;
    this.current = root;
  }

  @Override
  public boolean move(Integer id, IPlayer player) {
    try {
      this.current = this.current.get(id).enter(this.current.directionTo(id).opposite());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean move(Direction direction, IPlayer player) {
    try {
      this.current = ((INode) this.current.getNode(direction)).enter(direction.opposite());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return this.root.shoot(direction, count);
  }
}

