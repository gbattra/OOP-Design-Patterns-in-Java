package htw.level.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import htw.game.IHtwPlayer;
import htw.level.strategies.IHtwNodeStrategy;
import maze.components.ICoordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

/**
 * Abstract cave node in a Hunt the Wumpus maze. Mainly a wrapper around the strategy set
 * on the node instance.
 */
public abstract class AbstractCave extends AbstractRoomNode implements IHtwNode {
  protected IHtwNodeStrategy strategy;
  protected Integer id;
  protected Appendable logger;

  /**
   * Main constructor for the abstract node. Takes a coordinate pair and a strategy.
   *
   * @param id the node's id
   * @param coordinates the coordinates where the node resides
   * @param strategy the strategy used by the node
   * @param logger the logger for event output
   * @throws IllegalArgumentException if strategy or coordinates are null
   */
  public AbstractCave(
          Integer id,
          ICoordinates coordinates,
          IHtwNodeStrategy strategy,
          Appendable logger)
          throws IllegalArgumentException {
    super(coordinates, 0, 0);
    if (strategy == null || logger == null) {
      throw new IllegalArgumentException("Strategy and logger cannot be null.");
    }
    this.strategy = strategy;
    this.logger = logger;
    this.id = id;
    this.north = new DeadEnd();
    this.south = new DeadEnd();
    this.east = new DeadEnd();
    this.west = new DeadEnd();
  }

  @Override
  public Integer id() {
    return this.id;
  }

  @Override
  public boolean canEnter() {
    return true;
  }

  @Override
  public Direction directionTo(int id) {
    Map<Direction, Integer> neighbors = this.neighbors();
    List<Map.Entry<Direction, Integer>> target =
            neighbors.entrySet()
                    .stream().filter(e -> e.getValue() == id)
                    .collect(Collectors.toList());
    if (target.isEmpty()) {
      throw new IllegalArgumentException(
              String.format("Current node has no neighbor with id: %s", id));
    }

    return target.get(0).getKey();
  }

  @Override
  public IHtwNode get(int id) throws IllegalArgumentException {
    if (id <= 0) {
      throw new IllegalArgumentException("Id must be greater than zero.");
    }
    if (this.id == id) {
      return this;
    }
    
    List<ICoordinates> traversed = new ArrayList<>();
    return this.getHelper(traversed, id);
  }

  @Override
  public IHtwNode getHelper(List<ICoordinates> traversed, int id) throws IllegalArgumentException {
    if (traversed.contains(this.coordinates)) {
      throw new IllegalArgumentException(String.format("Cannot find target node: %s", id));
    }

    traversed.add(this.coordinates);

    if (this.id == id) {
      return this;
    }

    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0) {
      try {
        IHtwNode node = (IHtwNode) this.getNode(exits.get(0));
        exits.remove(0);
        return node.getHelper(traversed, id);
      } catch (Exception ignored) {
      }
    }

    throw new IllegalArgumentException(String.format("Cannot find target node: %s", id));
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalArgumentException {
    if (!(node instanceof IHtwNode)) {
      throw new IllegalArgumentException("Provided node is not an instance of MazeNode.");
    }
    super.setNode(node, dir);
  }

  @Override
  public IHtwNode enter(Direction from) throws IOException {
    return this.strategy.enter(from, this);
  }

  @Override
  public void setStrategy(IHtwNodeStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public boolean shoot(Direction direction, int count) throws IllegalArgumentException {
    if (count < 0) {
      throw new IllegalArgumentException("Count cannot be negative.");
    }
    if (count == 0) {
      return this.strategy.shoot(direction, count, this);
    }
    return ((IHtwNode) this.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public void receive(IHtwPlayer player) throws IllegalArgumentException, IOException {
    this.strategy.receive(player, this);
  }

  @Override
  public boolean smelly(Direction from) {
    return this.strategy.smelly(from, this);
  }

  @Override
  public boolean drafty(Direction from) {
    return this.strategy.drafty(from, this);
  }

  @Override
  public Appendable logger() {
    return this.logger;
  }

  @Override
  public Map<Direction, Integer> neighbors() {
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    Map<Direction, Integer> neighbors = new HashMap<>();
    for (Direction exit: exits) {
      try {
        List<ICoordinates> traversed = new ArrayList<>();
        traversed.add(this.coordinates);
        IHtwNode node = ((IHtwNode) this.getNode(exit)).getNext(traversed);
        neighbors.put(exit, node.id());
      } catch (Exception ignored) {
        // do nothing
      }
    }

    return neighbors;
  }

  @Override
  public IHtwNode getNext(List<ICoordinates> traversed) {
    return this.strategy.getNext(traversed, this);
  }

  @Override
  public String toString() {
    return String.format(
            "%s - %s",
            this.coordinates.toString(),
            this.strategy.toString());
  }

  @Override
  public int loot(int gold) {
    return 0;
  }
}
