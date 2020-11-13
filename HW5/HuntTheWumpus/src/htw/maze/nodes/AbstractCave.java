package htw.maze.nodes;

import htw.game.IPlayer;
import htw.maze.strategies.INodeStrategy;
import maze.components.ICoordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

/**
 * Abstract cave node in a Hunt the Wumpus maze. Mainly a wrapper around the strategy set
 * on the node instance.
 */
public abstract class AbstractCave extends AbstractRoomNode implements INode {
  protected INodeStrategy strategy;
  protected Integer id;

  /**
   * Main constructor for the abstract node. Takes a coordinate pair and a strategy.
   *
   * @param id the node's id
   * @param coordinates the coordinates where the node resides
   * @param strategy the strategy used by the node
   * @throws IllegalArgumentException if strategy or coordinates are null
   */
  public AbstractCave(Integer id, ICoordinates coordinates, INodeStrategy strategy)
          throws IllegalArgumentException {
    super(coordinates, 0, 0);
    if (strategy == null) {
      throw new IllegalArgumentException("Strategy cannot be null.");
    }
    this.strategy = strategy;
    this.id = id;
    this.north = new DeadEnd();
    this.south = new DeadEnd();
    this.east = new DeadEnd();
    this.west = new DeadEnd();
  }

  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public int loot(int gold) {
    return 0;
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalArgumentException {
    if (!(node instanceof INode)) {
      throw new IllegalArgumentException("Provided node is not an instance of MazeNode.");
    }
    super.setNode(node, dir);
  }

  @Override
  public INode enter(Direction from) {
    return this.strategy.enter(from, this);
  }

  @Override
  public void setStrategy(INodeStrategy strategy) {
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
    return ((INode) this.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public void receive(IPlayer player) throws IllegalArgumentException {
    this.strategy.receive(player);
  }

  @Override
  public boolean smelly() {
    return this.strategy.smelly(this);
  }

  @Override
  public boolean drafty() {
    return this.strategy.drafty(this);
  }

  @Override
  public String toString() {
    return String.format(
            "%s - %s",
            this.coordinates.toString(),
            this.strategy.toString());
  }
}
