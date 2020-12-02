package htw.tools;

import maze.config.MazeConfigurationBuilder;

/**
 * Builder for a config object for an HtwMaze.
 */
public class HtwConfigurationBuilder
        extends MazeConfigurationBuilder implements IHtwConfigurationBuilder {
  protected double pitFrequency = 0.2;
  protected double batFrequency = 0.3;
  protected int arrowCount = 10;
  protected int numPlayers = 1;
  protected Appendable logger = System.out;

  @Override
  public IHtwConfigurationBuilder setPitFrequency(double pitFrequency) {
    this.pitFrequency = pitFrequency;
    return this;
  }

  @Override
  public IHtwConfigurationBuilder setBatFrequency(double batFrequency) {
    this.batFrequency = batFrequency;
    return this;
  }

  @Override
  public IHtwConfigurationBuilder setLogger(Appendable logger) {
    this.logger = logger;
    return this;
  }

  @Override
  public IHtwConfigurationBuilder setNumPlayers(int numplayers) {
    this.numPlayers = numplayers;
    return this;
  }

  @Override
  public IHtwConfigurationBuilder setArrowCount(int arrowCount) {
    this.arrowCount = arrowCount;
    return this;
  }

  @Override
  public IHtwConfiguration build() {
    return new HtwConfiguration(
            this.rowCount,
            this.columnCount,
            this.start,
            this.pitFrequency,
            this.batFrequency,
            this.isRoomMaze,
            this.isWrappingMaze,
            this.targetEdgeCount,
            this.randomSeed,
            this.logger,
            this.arrowCount,
            this.numPlayers);
  }
}
