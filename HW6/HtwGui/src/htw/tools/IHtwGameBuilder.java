package htw.tools;

import htw.game.IHtwGame;

/**
 * Interface for the builder for a Hunt the Wumpus game.
 */
public interface IHtwGameBuilder {
  /**
   * Builds the game object.
   *
   * @return the built game object
   */
  IHtwGame build();
}
