package tests.htw.mocks;

import gui.IHtwGameVisitor;
import htw.game.IHtwGame;
import htw.game.commands.IActionStrategy;
import maze.Direction;

/**
 * Mock class for HtwGame.
 */
public class MockGame implements IHtwGame {
  private final StringBuffer log;

  /**
   * Constructor for mock.
   *
   * @param log log used for testing assertions
   */
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

  @Override
  public <R> R receive(IHtwGameVisitor<R> visitor) {
    return null;
  }

  @Override
  public void start() {
    this.log.append("Starting\n");
  }
}
