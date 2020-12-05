package htw;

/**
 * Interface for the builder for a Hunt the Wumpus game.
 */
public interface IHtwGameBuilder {
  /**
   * Sets the configuration to use for the game.
   *
   * @param configuration the configuration to use
   * @return updated game builder instance
   */
  IHtwGameBuilder setConfiguration(IHtwConfiguration configuration);

  /**
   * Builds the game object.
   *
   * @return the built game object
   */
  IHtwGame build();
}
