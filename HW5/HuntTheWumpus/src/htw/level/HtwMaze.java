package htw.level;

import htw.game.IPlayer;
import htw.level.nodes.INode;
import maze.components.Maze;
import maze.utils.Direction;

public class HtwMaze extends Maze implements IHtwMaze {
  private final INode root;
  private final Appendable logger;

  private INode current;

  public HtwMaze(INode root, Appendable logger) throws IllegalArgumentException {
    super(root, root);
    if (root == null || logger == null) {
      throw new IllegalArgumentException("Root and logger cannot be null.");
    }
    this.root = root;
    this.current = root;
    this.logger = logger;
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

