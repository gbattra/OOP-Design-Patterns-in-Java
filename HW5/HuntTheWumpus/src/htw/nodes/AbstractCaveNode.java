package htw.nodes;

import htw.strategies.Strategy;
import maze.components.Coordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public abstract class AbstractCaveNode extends AbstractRoomNode implements HTWNode {
  protected final Strategy strategy;

  public AbstractCaveNode(Coordinates coordinates, Strategy strategy)
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
    if (!(node instanceof HTWNode)) {
      throw new IllegalArgumentException("Provided node is not an instance of MazeNode.");
    }
    super.setNode(node, dir);
  }

  @Override
  public HTWNode enter(Direction from) {
    return this.strategy.enter(from, this);
  }

  @Override
  public boolean shoot(Direction direction, int count) throws IllegalArgumentException {
    if (count < 0) {
      throw new IllegalArgumentException("Count cannot be negative.");
    }
    if (count == 0) {
      return this.strategy.shoot(direction, count, this);
    }
    return ((HTWNode) this.getNode(direction)).shoot(direction, count - 1);
  }
}
