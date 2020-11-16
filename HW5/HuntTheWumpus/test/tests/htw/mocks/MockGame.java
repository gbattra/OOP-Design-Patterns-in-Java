package tests.htw.mocks;

import htw.game.IHtwGame;
import htw.game.commands.strategies.IActionStrategy;
import maze.utils.Direction;

public class MockGame implements IHtwGame {
  private final StringBuffer log;

  public MockGame(StringBuffer log) {
    this.log = log;
  }

  @Override
  public String status(IActionStrategy strategy) {
    return "status - status";
  }

  @Override
  public boolean isOver() {
    return false;
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
    this.log.append("shoot - ").append(direction.toString()).append(" - ").append(count);
    return false;
  }

  @Override
  public boolean shoot(int id, int count) {
    this.log.append("shoot - ").append(id).append(" - ").append(count);
    return false;
  }
}
