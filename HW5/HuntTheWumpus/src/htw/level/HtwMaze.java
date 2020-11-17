package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.commands.strategies.IActionStrategy;
import htw.level.nodes.IHtwNode;
import maze.components.Maze;
import maze.utils.Direction;

/**
 * The maze object in the Hunt the Wumpus game.
 */
public class HtwMaze extends Maze implements IHtwMaze {
  private final IHtwNode root;
  private final Appendable logger;

  private IHtwNode current;

  /**
   * Constructor for the maze.
   *
   * @param root the root node of the maze
   * @param logger the logger for the maze
   * @throws IllegalArgumentException if params are null
   */
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
  public void receive(IHtwPlayer player) throws IOException {
    this.current.receive(player);
  }

  @Override
  public boolean move(Integer id) throws IOException {
    try {
      Direction dir = this.current.directionTo(id);
      this.current = ((IHtwNode) this.current.getNode(dir)).enter(dir.opposite());
      return true;
    } catch (Exception e) {
      this.logger.append("Cannot move to ").append(id.toString()).append(".");
      return false;
    }
  }

  @Override
  public boolean move(Direction direction) throws IOException {
    try {
      this.current = ((IHtwNode) this.current.getNode(direction)).enter(direction.opposite());
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
