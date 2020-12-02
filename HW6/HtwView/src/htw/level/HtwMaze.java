package htw.level;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import gui.IHtwMazeVisitor;
import htw.game.IHtwPlayer;
import htw.game.commands.IActionStrategy;
import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.components.Maze;
import maze.Direction;

/**
 * The maze object in the Hunt the Wumpus game.
 */
public class HtwMaze extends Maze implements IHtwMaze {
  private final Appendable logger;
  private final IHtwNode root;
  private final Dimension dimension;
  private final Random random;

  /**
   * Constructor for the maze.
   *
   * @param root the root node of the maze
   * @param logger the logger for the maze
   * @param randomSeed the random seed for the maze
   * @throws IllegalArgumentException if params are null
   */
  public HtwMaze(
          IHtwNode root,
          Appendable logger,
          Dimension dimension,
          int randomSeed) throws IllegalArgumentException {
    super(root, root);
    if (root == null || logger == null || dimension == null) {
      throw new IllegalArgumentException("Root, logger and dimension cannot be null.");
    }
    this.root = root;
    this.logger = logger;
    this.dimension = dimension;
    this.random = new Random(randomSeed);
  }

  @Override
  public String status(IHtwPlayer player, IActionStrategy strategy) {
    return strategy.status(player.number(), (IHtwNode) this.root.get(player.currentPosition()));
  }

  @Override
  public void receive(IHtwPlayer player) throws IOException {
    ((IHtwNode) this.root.get(player.currentPosition())).receive(player);
  }

  @Override
  public boolean move(IHtwPlayer player, Integer id) throws IOException {
    for (Direction dir : Arrays.asList(
            Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
      IHtwNode node = (IHtwNode) this.root.get(player.currentPosition()).getNode(dir);
      if (node.getId().equals(id)) {
        IHtwNode current = node.enter(dir.opposite());
        player.setCurrentPosition(current.getCoordinates());
        return true;
      }
    }

    Direction dir = ((IHtwNode) this.root.get(player.currentPosition())).directionTo(id);
    IHtwNode current = ((IHtwNode) this.root.get(player.currentPosition())
            .getNode(dir)).enter(dir.opposite());
    player.setCurrentPosition(current.getCoordinates());
    return true;
  }

  @Override
  public boolean move(IHtwPlayer player, Direction direction) throws IOException {
    IHtwNode current = ((IHtwNode) this.root.get(player.currentPosition())
            .getNode(direction)).enter(direction.opposite());
    player.setCurrentPosition(current.getCoordinates());
    return true;
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

  @Override
  public <R> R receive(IHtwMazeVisitor<R> visitor) {
    return visitor.visitMaze(this.root, this.dimension);
  }

  @Override
  public ICoordinates randomCoordinates() {
    int x = this.random.nextInt(this.dimension.width);
    int y = this.random.nextInt(this.dimension.height);
    return new Coordinates(x, y);
  }
}
