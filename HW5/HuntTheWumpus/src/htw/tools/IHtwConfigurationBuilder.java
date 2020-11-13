package htw.tools;

import maze.config.IConfigurationBuilder;

public interface IHtwConfigurationBuilder extends IConfigurationBuilder {
  IHtwConfigurationBuilder setPitFrequency(double pitFrequency);
  IHtwConfigurationBuilder setBatFrequency(double batFrequency);
  IHtwConfiguration build();
}
