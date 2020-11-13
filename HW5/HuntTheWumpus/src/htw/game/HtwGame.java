package htw.game;

import htw.maze.IHtwMaze;
import maze.utils.Direction;

public class HtwGame implements IGame {
  private final IPlayer player;
  private final IHtwMaze maze;

  public HtwGame(
          IPlayer player,
          IHtwMaze maze) throws IllegalArgumentException {
    if (player == null || maze == null) {
      throw new IllegalArgumentException("Player and maze cannot be null.");
    }
    this.player = player;
    this.maze = maze;
  }

  @Override
  public boolean hasNext() {
    return this.player.isAlive() && this.player.arrowCount() > 0;
  }

  @Override
  public IRound next() {
    return new Round();
  }

  @Override
  public boolean move(Direction direction, IRound round) {
    return false;
  }

  @Override
  public boolean move(int id, IRound round) {
    return false;
  }

  @Override
  public boolean shoot(Direction direction, IRound round) {
    return false;
  }
}
