package tests.htw.events;

import org.junit.Test;

import htw.game.events.IGameEvent;
import htw.game.events.MoveEvent;
import maze.components.Coordinates;

import static org.junit.Assert.fail;

public class MoveEventTest {
  @Test
  public void testValidConstructor() {
    try {
      IGameEvent move = new MoveEvent(new Coordinates(0, 0));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IGameEvent move = new MoveEvent(null);
    fail("Invalid constructor should have failed.");
  }
}
