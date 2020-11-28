package tests.htw.mocks;

import java.io.IOException;

import htw.game.IHtwPlayer;
import htw.game.IRound;
import htw.game.commands.IActionStrategy;
import htw.level.IHtwMaze;
import maze.Direction;

/**
 * Mock class for HtwMaze.
 */
public class MockMaze implements IHtwMaze {
  private final StringBuilder log;

  /**
   * Constructor for mock.
   *
   * @param log log used for testing assertions
   */
  public MockMaze(StringBuilder log) {
    this.log = log;
  }

  @Override
  public String status(IActionStrategy strategy) {
    return "status";
  }

  @Override
  public boolean move(Direction direction, IRound round) {
    this.log.append(
            String.format("moved - %s", direction.toString()));
    return true;
  }

  @Override
  public boolean move(Integer id, IRound round) {
    this.log.append(
            String.format("moved - %s", id));
    return true;
  }

  @Override
  public void receive(IHtwPlayer player) throws IOException {
    this.log.append(String.format("receive - %s", player.getName()));
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    this.log.append(String.format("shoot - %s - %s", direction.toString(), count));
    return false;
  }

  @Override
  public boolean shoot(int id, int count) {
    this.log.append(String.format("shoot - %s - %s", id, count));
    return false;
  }
}
