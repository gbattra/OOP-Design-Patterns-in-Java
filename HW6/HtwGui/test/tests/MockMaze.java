package tests;

import java.io.IOException;

import gui.IHtwMazeVisitor;
import htw.IHtwPlayer;
import htw.IActionStrategy;
import htw.IHtwMaze;
import maze.Direction;
import maze.ICoordinates;

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
  public String status(IHtwPlayer player, IActionStrategy strategy) {
    return "status";
  }

  @Override
  public boolean move(IHtwPlayer player, Direction direction) {
    this.log.append(
            String.format("moved - %s", direction.toString()));
    return true;
  }

  @Override
  public boolean move(IHtwPlayer player, Integer id) {
    this.log.append(
            String.format("moved - %s", id));
    return true;
  }

  @Override
  public void receive(IHtwPlayer player) throws IOException {
    this.log.append(String.format("receive - %s", player.getName()));
  }

  @Override
  public boolean shoot(IHtwPlayer player, Direction direction, int count) {
    this.log.append(String.format("shoot - %s - %s", direction.toString(), count));
    return false;
  }

  @Override
  public boolean shoot(IHtwPlayer player, int id, int count) {
    this.log.append(String.format("shoot - %s - %s", id, count));
    return false;
  }

  @Override
  public <R> R receive(IHtwMazeVisitor<R> visitor) {
    return null;
  }

  @Override
  public ICoordinates randomCoordinates() {
    return null;
  }
}
