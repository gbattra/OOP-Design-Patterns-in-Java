package tests.htw.events;

import org.junit.Test;

import htw.game.events.AlertEvent;
import htw.game.events.IGameEvent;

import static org.junit.Assert.fail;

public class AlertEventTest {
  @Test
  public void testValidConstructor() {
    try {
      IGameEvent alert = new AlertEvent("This is an alert.");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNull() {
    IGameEvent alert = new AlertEvent(null);
    fail("Invalid constructor should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEmpty() {
    IGameEvent alert = new AlertEvent("");
    fail("Invalid constructor should have failed.");
  }
}
