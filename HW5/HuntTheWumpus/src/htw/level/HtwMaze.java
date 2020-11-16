package htw.level;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import htw.game.IHtwPlayer;
import htw.game.commands.strategies.IActionStrategy;
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
  public String status(IActionStrategy strategy) {
    return strategy.status(this.current);
  }

  @Override
  public boolean move(Integer id, IHtwPlayer player) throws IOException {
    try {
      Direction dir = this.current.directionTo(id);
      this.current = ((IHtwNode) this.current.getNode(dir)).enter(dir.opposite());
      this.current.receive(player);
      return true;
    } catch (Exception e) {
      this.logger.append("Cannot move to ").append(id.toString()).append(".");
      return false;
    }
  }

  @Override
  public boolean move(Direction direction, IHtwPlayer player) throws IOException {
    try {
      this.current = ((IHtwNode) this.current.getNode(direction)).enter(direction.opposite());
      this.current.receive(player);
      return true;
    } catch (Exception e) {
      this.logger.append("Cannot move to the ").append(direction.toString()).append(".");
      return false;
    }
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return this.current.shoot(direction, count);
  }

  @Override
  public boolean shoot(int id, int count) {
    return this.current.shoot(this.current.directionTo(id), count);
  }
}
