package maze.interfaces;

/**
 * Builder class to help setup a maze. It is recommended to use this class to build a maze.
 */
public interface MazeBuilder {
  /**
   * Builds the maze using the setup configurations.
   *
   * @return the built maze
   */
  Maze build();
}
