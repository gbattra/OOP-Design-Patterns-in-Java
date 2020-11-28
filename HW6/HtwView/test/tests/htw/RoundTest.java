package tests.htw;

import org.junit.Test;

import htw.game.events.IGameEvent;
import htw.game.IRound;
import htw.game.events.RestartEvent;
import htw.game.Round;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.fail;

public class RoundTest {
  @Test
  public void testValidAddEvent() {
    try {
      IRound round = new Round(0);
      IGameEvent event = new RestartEvent(new MockGame(new StringBuffer()));
      round.addEvent(event);
    } catch (Exception e) {
      fail("Valid addEvent() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddEvent() {
    IRound round = new Round(0);
    round.addEvent(null);
  }
}
