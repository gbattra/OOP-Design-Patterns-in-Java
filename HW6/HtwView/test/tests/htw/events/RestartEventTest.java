package tests.htw.events;

import org.junit.Test;

import htw.game.events.IGameEvent;
import htw.game.events.RestartEvent;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.fail;

public class RestartEventTest {
  @Test
  public void testValidConstructor() {
    try {
      IGameEvent restart = new RestartEvent(new MockGame(new StringBuffer()));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IGameEvent restart = new RestartEvent(null);
    fail("Invalid constructor should have failed.");
  }
}
