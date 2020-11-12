package htw.nodes;

import htw.game.IPlayer;
import htw.strategies.INodeStrategy;
import maze.components.ICoordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public abstract class AbstractCave extends AbstractRoomNode implements INode {
  protected INodeStrategy strategy;

  public AbstractCave(ICoordinates coordinates, INodeStrategy strategy)
          throws IllegalArgumentException {
    super(coordinates, 0, 0);
    if (strategy == null) {
      throw new IllegalArgumentException("Strategy cannot be null.");
    }
    this.strategy = strategy;
    this.north = new DeadEnd();
    this.south = new DeadEnd();
    this.east = new DeadEnd();
    this.west = new DeadEnd();
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
}
