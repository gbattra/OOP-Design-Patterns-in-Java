package htw.tools;

/**
 * Configuration object for building a hunt the wumpus maze.
 */
public interface IHtwConfiguration {
  /**
   * Getter for the bat frequency.
   *
   * @return the bat frequency
   */
  double getBatFrequency();

  /**
   * Getter for the pit frequency.
   *
   * @return the pit frequency
   */
  double getPitFrequency();
}
