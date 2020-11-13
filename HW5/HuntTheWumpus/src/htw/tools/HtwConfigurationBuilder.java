package htw.tools;

import maze.config.MazeConfigurationBuilder;

public class HtwConfigurationBuilder
        extends MazeConfigurationBuilder implements IHtwConfigurationBuilder {
  protected double pitFrequency;
  protected double batFrequency;

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
            this.randomSeed);
  }
}
