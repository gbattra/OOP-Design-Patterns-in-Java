package tests.htw;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.IController;
import htw.game.commands.strategies.ActionByDirStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HtwControllerTest {

  @Test
  public void testConstructor() {
    try {
      StringBuilder log = new StringBuilder();
      IController controller = new HtwController(
              new Scanner(""), System.out, new ActionByDirStrategy());
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IController controller = new HtwController(
            null, null, null);
  }

  @Test
  public void testQuit() {
    StringBuilder log = new StringBuilder();
    IController controller = new HtwController(
            new Scanner("greg 10 standard q\n"), log, new ActionByDirStrategy());
    controller.run();
    assertEquals(
            "Player name (first only):\n"
            + "Starting arrow count:\n"
            + "Configure maze:\n"
            + "Maze type ('standard' or 'custom'):\n"
            + "Starting game...\n"
            + "Quit -> 'q' / 'quit'\n"
            + "Restart -> 'restart'\n"
            + "'shoot' or 'move'?\n"
            + "Quitting...\n",
            log.toString());
  }

  @Test
  public void testStart() {
    StringBuilder log = new StringBuilder();
    IController controller = new HtwController(
            new Scanner("greg 10 standard shoot n 1 q\n"), log, new ActionByDirStrategy());
    controller.run();
    assertEquals(
            "Player name (first only):\n"
          + "Starting arrow count:\n"
          + "Configure maze:\n"
          + "Maze type ('standard' or 'custom'):\n"
          + "Starting game...\n"
          + "Quit -> 'q' / 'quit'\n"
          + "Restart -> 'restart'\n"
          + "'shoot' or 'move'?\n"
          + "Direction and count:\n"
          + "'shoot' or 'move'?\n"
          + "Quitting...\n",
            log.toString());
  }
}
