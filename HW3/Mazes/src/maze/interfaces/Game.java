package maze.interfaces;

import maze.enums.Direction;

/**
 * Interface representing a game where a player traverses a maze and collects gold as they
 * reach the exit.
 */
public interface Game {
  /**
   * Returns the player instance in the game.
   *
   * @return the player
   */
  Player getPlayer();

  /**
   * Return the maze instance which the player is navigating.
   *
   * @return the maze instance
   */
  Maze getMaze();

  /**
   * Gets path taken so far.
   *
   * @return the path object
   */
  Path getPath();

  /**
   * Get the current score of the game (the player gold count).
   *
   * @return the score of the game
   */
  int getScore();

  /**
   * Is the game over? (has the player reached the goal?).
   *
   * @return true if game over
   */
  boolean isOver();

  /**
   * Attempts to move the player in the specified direction. Will updated the player's gold count
   * and score.
   *
   * @param direction the direction to move
   * @return true if move was successful, false if dead end direction
   */
  boolean movePlayer(Direction direction);

  /**
   * Starts the game by placing the player in the starting node of the maze.
   */
  void start();
}
