package tests.htw;

import org.junit.Test;

import java.util.Scanner;

import htw.game.ConsoleController;
import htw.game.commands.DirActionStrategy;
import tests.htw.mocks.MockHtwCommandMapFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for HtwController.
 */
public class HtwControllerTest {
  @Test
  public void testConstructor() {
    try {
      StringBuffer log = new StringBuffer();
      Runnable controller = new ConsoleController(
              new Scanner(""),
              log,
              new DirActionStrategy(),
              new MockHtwCommandMapFactory(new Scanner(""), log));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Runnable controller = new ConsoleController(
            null, null, null, null);
  }

  @Test
  public void testQuit() {
    try {
      Scanner scanner = new Scanner("greg 10 standard q");
      StringBuffer log = new StringBuffer();
      Runnable controller = new ConsoleController(
              scanner,
              log,
              new DirActionStrategy(),
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
      Runnable controller = new ConsoleController(
              scanner,
              log,
              new DirActionStrategy(),
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
