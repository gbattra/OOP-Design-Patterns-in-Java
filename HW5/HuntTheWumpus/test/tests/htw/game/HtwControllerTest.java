package tests.htw.game;

import org.junit.Test;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.IController;
import htw.game.commands.strategies.ActionByDirStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
            new Scanner("greg 10 standard q "), log, new ActionByDirStrategy());
    controller.run();
    assertTrue(log.toString().startsWith(
            "Player name (first only): "
            + "Starting arrow count: "
            + "Maze type ('standard' or 'custom'): "
            + "\nStarting game..."
            + "\nQuit -> 'q' / 'quit'"
            + "\nRestart -> 'restart'"
            + "\n\n'shoot' or 'move'? "
            + "Quitting..."));
  }
}
