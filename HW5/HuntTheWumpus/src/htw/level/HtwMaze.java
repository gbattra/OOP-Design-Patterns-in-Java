package htw.level;

import java.util.List;
import java.util.stream.Collectors;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;
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
      List<IHtwNode> neighbors = this.current.neighbors();
      this.logger.append("\n").append(
              String.format(
                      "You are in cave %s with tunnels to the %s",
                      this.current.getCoordinates().toString(),
                      neighbors
                              .stream()
                              .map(n -> n.directionTo(this.current.id()).opposite().toString())
                              .collect(Collectors.joining(", "))));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    return this.root.shoot(direction, count);
  }

  @Override
  public boolean shoot(int id, int count) {
    return this.root.shoot(this.root.directionTo(id), count);
  }
}

