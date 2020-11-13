package htw.game;

import htw.level.IHtwMaze;
import maze.utils.Direction;

public class HtwGame implements IHtwGame {
  private final IHtwPlayer player;
  private final IHtwMaze maze;

  public HtwGame(
          IHtwPlayer player,
          IHtwMaze maze) throws IllegalArgumentException {
    if (player == null || maze == null) {
      throw new IllegalArgumentException("Player and maze cannot be null.");
    }
    this.player = player;
    this.maze = maze;
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
    return this.maze.shoot(direction, count);
  }
}
