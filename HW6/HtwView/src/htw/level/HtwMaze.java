package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.IRound;
import htw.game.commands.IActionStrategy;
import maze.components.Maze;
import maze.Direction;

/**
 * The maze object in the Hunt the Wumpus game.
 */
public class HtwMaze extends Maze implements IHtwMaze {
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
  public boolean move(Integer id, IRound round) throws IOException {
    Direction dir = this.current.directionTo(id);
    this.current = ((IHtwNode) this.current.getNode(dir)).enter(dir.opposite(), round);
    return true;
  }

  @Override
  public boolean move(Direction direction, IRound round) throws IOException {
    this.current =
            ((IHtwNode) this.current.getNode(direction)).enter(direction.opposite(), round);
    return true;
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
