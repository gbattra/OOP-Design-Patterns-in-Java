package htw.level;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;
import maze.components.Maze;
import maze.utils.Direction;

public class HtwMaze extends Maze implements IHtwMaze {
  private final IHtwNode root;
  private final Appendable logger;

  private IHtwNode current;

  public HtwMaze(IHtwNode root, Appendable logger) throws IllegalArgumentException {
    super(root, root);
    if (root == null || logger == null) {
      throw new IllegalArgumentException("Root and logger cannot be null.");
    }
    this.root = root;
    this.current = root;
    this.logger = logger;
  }

  @Override
  public boolean move(Integer id, IHtwPlayer player) {
    try {
      this.current = this.current.get(id).enter(this.current.directionTo(id).opposite());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean move(Direction direction, IHtwPlayer player) {
    try {
      this.current = ((IHtwNode) this.current.getNode(direction)).enter(direction.opposite());
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

