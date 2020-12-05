package htw;

import java.awt.Dimension;
import java.util.ArrayList;

import maze.Direction;
import maze.MazeBuilder;
import maze.Coordinates;
import maze.ICoordinates;
import maze.IMaze;
import maze.Node;

/**
 * Builder for an HtwMaze.
 */
public class HtwMazeBuilder extends MazeBuilder {
  private boolean wumpusSet;
  private int currentId = 1;

  /**
   * Constructor for the builder.
   *
   * @param configuration the configuration to use when building the maze.
   */
  public HtwMazeBuilder(IHtwConfiguration configuration) {
    super(configuration);
  }

  @Override
  public IMaze build() {
    IHtwNode start = new Cave(
            this.currentId,
            this.config.start(),
            new StandardStrategy(),
            ((IHtwConfiguration) this.config).getLogger());
    this.grow(start);

    if (this.config.isRoomMaze()) {
      this.tearDownWalls();
    }
    if (!this.wumpusSet) {
      this.setWumpus(start);
    }

    return new HtwMaze(
            start,
            new Dimension(this.config.columnCount(), this.config.rowCount()),
            this.config.randomSeed());
  }

  @Override
  public Node generateRoom(ICoordinates c) {
    this.currentId++;
    this.totalExitCount++;
    return new Cave(
            this.currentId,
            c,
            new TunnelStrategy(),
            ((IHtwConfiguration) this.config).getLogger());
  }

  @Override
  protected void spawnAndGrow(Node node, Direction exit, int currExitCount) {
    if (currExitCount > 1) {
      ((IHtwNode) node).setStrategy(this.generateStrategy((IHtwConfiguration) this.config));
    }

    super.spawnAndGrow(node, exit, currExitCount);
  }

  /**
   * Generates a cave strategy using the provided configuration.
   *
   * @param config the config to guide strategy instantiation
   * @return the new strategy instance
   */
  private IHtwNodeStrategy generateStrategy(IHtwConfiguration config) {
    double next = config.random().nextDouble();
    boolean isPit = next <= config.pitFrequency();
    boolean isBat = next <= config.batFrequency();
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

  /**
   * Updates the node's strategy to Wumpus, making it the node where lives the beast.
   *
   * @param node the node to set as Wumpus
   */
  private void setWumpus(IHtwNode node) {
    Coordinates coordinates = new Coordinates(
            this.config.random().nextInt(this.config.columnCount()),
            this.config.random().nextInt(this.config.rowCount()));
    ((IHtwNode) node.get(coordinates)).adjacent(new ArrayList<>())
                                      .setStrategy(new WumpusStrategy());
  }
}