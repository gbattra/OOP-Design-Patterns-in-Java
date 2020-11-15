package htw.tools;

import htw.level.HtwMaze;
import htw.level.nodes.Cave;
import htw.level.nodes.IHtwNode;
import htw.level.strategies.BatStrategy;
import htw.level.strategies.IHtwNodeStrategy;
import htw.level.strategies.PitStrategy;
import htw.level.strategies.StandardStrategy;
import htw.level.strategies.TunnelStrategy;
import htw.level.strategies.WumpusStrategy;
import maze.MazeBuilder;
import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.components.IMaze;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class HtwMazeBuilder
        extends MazeBuilder implements IHtwMazeBuilder {
  private boolean wumpusSet;
  private int currentId = 1;

  public HtwMazeBuilder(IHtwConfiguration configuration) {
    super(configuration);
  }

  @Override
  public IMaze build() {
    IHtwNode start = new Cave(
            this.currentId,
            this.config.start(),
            new TunnelStrategy(),
            ((IHtwConfiguration) this.config).getLogger());
    this.grow(start);

    if (this.config.isRoomMaze()) {
      this.tearDownWalls();
    }
    if (!this.wumpusSet) {
      this.setWumpus(start);
    }

    return new HtwMaze(start, System.out);
  }

  @Override
  public Node generateRoom(ICoordinates c) {
    this.currentId++;
    this.totalExitCount++;
    return new Cave(this.currentId, c, new TunnelStrategy(), System.out);
  }

  @Override
  protected Node upgradeHallway(Node node) {
    ((IHtwNode) node).setStrategy(this.generateStrategy((IHtwConfiguration) this.config));
    return node;
  }

  private IHtwNodeStrategy generateStrategy(IHtwConfiguration config) {
    double next = config.random().nextDouble();
    boolean isPit = next <= config.getPitFrequency();
    boolean isBat = next <= config.getBatFrequency();
    boolean isWumpus = next <= (double) (1 / (this.config.rowCount() * this.config.columnCount()));

    IHtwNodeStrategy strategy = new StandardStrategy();
    if (isPit) {
      strategy = new PitStrategy();
    }
    if (isWumpus) {
      this.wumpusSet = true;
      strategy = new WumpusStrategy();
    }
    if (isBat) {
      strategy = new BatStrategy(
              this.config.rowCount(),
              this.config.columnCount(),
              this.config.random(),
              strategy);
    }

    return strategy;
  }

  private void setWumpus(IHtwNode node) {
    Coordinates coordinates = new Coordinates(
            this.config.random().nextInt(this.config.columnCount()),
            this.config.random().nextInt(this.config.rowCount()));
    ((IHtwNode) node.get(coordinates)).enter(Direction.SOUTH).setStrategy(new WumpusStrategy());
  }
}
