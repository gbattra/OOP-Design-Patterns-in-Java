package tests.htw.mocks;

import htw.game.IPlayer;
import htw.maze.IHtwMaze;
import maze.utils.Direction;

public class MockMaze implements IHtwMaze {
  private final StringBuilder log;

  public MockMaze() {
    this.log = new StringBuilder();
  }

  @Override
  public boolean move(Direction direction, IPlayer player) {
    this.log.append(
            String.format("moved - %s - %s", direction.toString(), player.toString()));
    return true;
  }

  @Override
  public boolean move(Integer id, IPlayer player) {
    this.log.append(
            String.format("moved - %s - %s", id, player.toString()));
    return true;
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    this.log.append(String.format("shoot - %s - %s", direction.toString(), count));
    return false;
  }
}
