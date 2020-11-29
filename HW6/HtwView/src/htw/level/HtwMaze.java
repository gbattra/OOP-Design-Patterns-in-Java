package htw.level;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.commands.IActionStrategy;
import maze.components.Maze;
import maze.Direction;

/**
 * The maze object in the Hunt the Wumpus game.
 */
public class HtwMaze extends Maze implements IHtwMaze {
  private final Appendable logger;
  private final IHtwNode root;

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
    this.logger = logger;
  }

  @Override
  public String status(IHtwPlayer player, IActionStrategy strategy) {
    return strategy.status((IHtwNode) this.root.get(player.currentPosition()));
  }

  @Override
  public void receive(IHtwPlayer player) throws IOException {
    ((IHtwNode) this.root.get(player.currentPosition())).receive(player);
  }

  @Override
  public boolean move(IHtwPlayer player, Integer id) throws IOException {
    try {
      Direction dir = ((IHtwNode) this.root.get(player.currentPosition())).directionTo(id);
      IHtwNode current = ((IHtwNode) this.root.get(player.currentPosition())
              .getNode(dir)).enter(dir.opposite());
      player.setCurrentPosition(current.getCoordinates());
      return true;
    } catch (Exception e) {
      this.logger.append("Cannot move to ").append(id.toString()).append(".");
      return false;
    }
  }

  @Override
  public boolean move(IHtwPlayer player, Direction direction) throws IOException {
    try {
      IHtwNode current = ((IHtwNode) this.root.get(player.currentPosition())
              .getNode(direction)).enter(direction.opposite());
      player.setCurrentPosition(current.getCoordinates());
      return true;
    } catch (Exception e) {
      this.logger.append("Cannot move to the ").append(direction.toString()).append(".");
      return false;
    }
  }

  @Override
  public boolean shoot(IHtwPlayer player, Direction direction, int count) {
    return ((IHtwNode) this.root.get(player.currentPosition())).shoot(direction, count);
  }

  @Override
  public boolean shoot(IHtwPlayer player, int id, int count) {
    IHtwNode current = ((IHtwNode) this.root.get(player.currentPosition()));
    return current.shoot(current.directionTo(id), count);
  }
}
