package tests.htw.mocks;

import htw.game.IHtwGame;
import maze.utils.Direction;

public class MockGame implements IHtwGame {
  private final StringBuilder log;

  public MockGame(StringBuilder log) {
    this.log = log;
  }

  @Override
  public boolean move(Direction direction) {
    this.log.append("move - ").append(direction);
    return false;
  }

  @Override
  public boolean move(int id) {
    this.log.append("move - ").append(id);
    return false;
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    this.log.append("shoot - ").append(direction.toString()).append(count);
    return false;
  }
}
