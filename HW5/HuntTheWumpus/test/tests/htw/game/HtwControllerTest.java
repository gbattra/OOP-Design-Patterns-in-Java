package tests.htw.game;

import org.junit.Test;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.commands.strategies.ActionByDirStrategy;
import tests.htw.mocks.MockHtwCommandMapFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HtwControllerTest {

  @Test
  public void testConstructor() {
    try {
      StringBuffer log = new StringBuffer();
      Runnable controller = new HtwController(
              new Scanner(""),
              log,
              new ActionByDirStrategy(),
              new MockHtwCommandMapFactory(new Scanner(""), log));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Runnable controller = new HtwController(
            null, null, null, null);
  }

  @Test
  public void testQuit() {
    try {
      Scanner scanner = new Scanner("greg 10 standard q");
      StringBuffer log = new StringBuffer();
      Runnable controller = new HtwController(
              scanner,
              log,
              new ActionByDirStrategy(),
              new MockHtwCommandMapFactory(scanner, log));
      controller.run();
      assertEquals(
              "execute - start - greg - 10 - standard"
              + "\nStarting game..."
              + "\nQuit -> 'q' / 'quit'"
              + "\nRestart -> 'restart'"
              + "\n"
              + "\nstatus - status"
              + "\n'shoot' or 'move'? "
              + "Quitting...",
              log.toString());
    } catch (Exception e) {
      fail("Valid quit() should not have failed.");
    }
  }

  @Test
  public void testMoveAndShoot() {
    try {
      Scanner scanner = new Scanner("greg 10 standard move e shoot e 1 q");
      StringBuffer log = new StringBuffer();
      Runnable controller = new HtwController(
              scanner,
              log,
              new ActionByDirStrategy(),
              new MockHtwCommandMapFactory(scanner, log));
      controller.run();
      assertEquals(
              "execute - start - greg - 10 - standard"
                      + "\nStarting game..."
                      + "\nQuit -> 'q' / 'quit'"
                      + "\nRestart -> 'restart'"
                      + "\n"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "execute - move - e"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "execute - shoot - e - 1"
                      + "\nstatus - status"
                      + "\n'shoot' or 'move'? "
                      + "Quitting...",
              log.toString());
    } catch (Exception e) {
      fail("Valid quit() should not have failed.");
    }
  }
}
