package tests.htw;

import org.junit.Test;

import htw.game.IGameEvent;
import htw.game.IRound;
import htw.game.RestartEvent;
import htw.game.Round;

import static org.junit.Assert.fail;

public class RoundTest {
  @Test
  public void testValidAddEvent() {
    try {
      IRound round = new Round(0);
      IGameEvent event = new RestartEvent();
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
