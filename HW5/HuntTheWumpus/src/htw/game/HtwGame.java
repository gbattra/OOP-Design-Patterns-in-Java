package htw.game;

import htw.level.IHtwMaze;
import maze.utils.Direction;

public class HtwGame implements IHtwGame {
  private final IHtwPlayer player;
  private final IHtwMaze maze;
  private final Appendable logger;

  public HtwGame(
          IHtwPlayer player,
          IHtwMaze maze,
          Appendable logger) throws IllegalArgumentException {
    if (player == null || maze == null || logger == null) {
      throw new IllegalArgumentException("Player and maze cannot be null.");
    }
    this.player = player;
    this.maze = maze;
    this.logger = logger;
  }

  @Override
  public boolean move(Direction direction) {
    return this.maze.move(direction, this.player);
  }

  @Override
  public boolean move(int id) {
    return this.maze.move(id, this.player);
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    try {
      boolean hit = this.maze.shoot(direction, count);
      this.logger.append("\n").append(hit ? "Nice shot! You've slain the Wumpus!" : "Miss...");
      return hit;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean shoot(int id, int count) {
    try {
      boolean hit = this.maze.shoot(id, count);
      this.logger.append("\n").append(hit ? "Nice shot! You've slain the Wumpus!" : "Miss...");
      return hit;
    } catch (Exception e) {
      return false;
    }
  }
}
