package tests.htw.mocks;

import htw.game.IPlayer;
import htw.level.IHtwMaze;
import maze.utils.Direction;

public class MockMaze implements IHtwMaze {
  private final StringBuilder log;

  public MockMaze(StringBuilder log) {
    this.log = log;
  }

  @Override
  public boolean move(Direction direction, IPlayer player) {
    this.log.append(
            String.format("moved - %s - %s", direction.toString(), player.getName()));
    return true;
  }

  @Override
  public boolean move(Integer id, IPlayer player) {
    this.log.append(
            String.format("moved - %s - %s", id, player.getName()));
    return true;
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    this.log.append(String.format("shoot - %s - %s", direction.toString(), count));
    return false;
  }
}
