package htw.tools;

import htw.maze.nodes.Cave;
import htw.maze.nodes.INode;
import htw.maze.strategies.BatStrategy;
import htw.maze.strategies.INodeStrategy;
import htw.maze.strategies.PitStrategy;
import htw.maze.strategies.StandardStrategy;
import htw.maze.strategies.TunnelStrategy;
import htw.maze.strategies.WumpusStrategy;
import maze.MazeBuilder;
import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.components.IMaze;
import maze.components.Maze;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class HtwMazeBuilder extends MazeBuilder {
  private boolean wumpusSet;
  private int currentId = 1;

  public HtwMazeBuilder(IHtwConfiguration configuration) {
    super(configuration);
  }

  @Override
  public IMaze build() {
    INode start = new Cave(this.currentId, this.config.start(), new TunnelStrategy());
    this.grow(start);

    if (this.config.isRoomMaze()) {
      this.tearDownWalls();
    }
    if (!this.wumpusSet) {
      this.setWumpus(start);
    }

    return new Maze(
            this.visited[this.config.start().getY()][this.config.start().getX()],
            this.visited[this.config.goal().getY()][this.config.goal().getX()]);
  }

  @Override
  public Node generateRoom(ICoordinates c) {
    this.currentId++;
    return new Cave(this.currentId, c, new TunnelStrategy());
  }

  @Override
  protected Node upgradeHallway(Node node) {
    ((INode) node).setStrategy(this.generateStrategy((IHtwConfiguration) this.config));
    return node;
  }

  private INodeStrategy generateStrategy(IHtwConfiguration config) {
    double next = config.random().nextDouble();
    boolean isPit = next <= config.getPitFrequency();
    boolean isBat = next <= config.getBatFrequency();
    boolean isWumpus = next <= (double) (1 / (this.config.rowCount() * this.config.columnCount()));

    INodeStrategy strategy = new StandardStrategy();
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

  private void setWumpus(INode node) {
    Coordinates coordinates = new Coordinates(
            this.config.random().nextInt(this.config.columnCount()),
            this.config.random().nextInt(this.config.rowCount()));
    ((INode) node.get(coordinates)).enter(Direction.SOUTH).setStrategy(new WumpusStrategy());
  }
}
