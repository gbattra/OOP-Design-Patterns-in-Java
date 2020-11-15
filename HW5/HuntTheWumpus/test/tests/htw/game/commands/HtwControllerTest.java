package tests.htw.game.commands;

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
            "\nPlayer name (first only): "
            + "\nStarting arrow count: "
            + "\nMaze type ('standard' or 'custom'): "
            + "\nStarting game..."
            + "\nQuit -> 'q' / 'quit'"
            + "\nRestart -> 'restart'"
            + "\n'shoot' or 'move'? "
            + "\nQuitting..."));
  }

  @Test
  public void testCustomNonRoomNonWrapping() {
    StringBuilder log = new StringBuilder();
    IController controller = new HtwController(
            new Scanner("greg 10 custom 5 5 false false 0.2 0.3 q "), log, new ActionByDirStrategy());
    controller.run();
    assertTrue(log.toString().startsWith(
            "\nPlayer name (first only): "
            + "\nStarting arrow count: "
            + "\nMaze type ('standard' or 'custom'): "
            + "\nRow count: "
            + "\nColumn count: "
            + "\nIs room maze ('true' / 'false'): "
            + "\nIs wrapping maze ('true' / 'false'): "
            + "\nPit frequency (double): "
            + "\nBat frequency (double): "
            + "\nStarting game..."
            + "\nQuit -> 'q' / 'quit'"
            + "\nRestart -> 'restart'"
            + "\n'shoot' or 'move'? "
            + "\nQuitting..."));
  }

  @Test
  public void testStart() {
    StringBuilder log = new StringBuilder();
    IController controller = new HtwController(
            new Scanner("greg 10 standard q "), log, new ActionByDirStrategy());
    controller.run();
    assertTrue(log.toString().startsWith(
            "\nPlayer name (first only): "
            + "\nStarting arrow count: "
            + "\nMaze type ('standard' or 'custom'): "
            + "\nStarting game..."
            + "\nQuit -> 'q' / 'quit'"
            + "\nRestart -> 'restart'"
            + "\n'shoot' or 'move'? "
            + "\nQuitting..."));
  }

  @Test
  public void testShoot() {
    StringBuilder log = new StringBuilder();
    IController controller = new HtwController(
            new Scanner("greg 10 standard shoot"), log, new ActionByDirStrategy());
    controller.run();
    assertTrue(log.toString().startsWith(
            "\nPlayer name (first only): "
                    + "\nStarting arrow count: "
                    + "\nMaze type ('standard' or 'custom'): "
                    + "\nStarting game..."
                    + "\nQuit -> 'q' / 'quit'"
                    + "\nRestart -> 'restart'"
                    + "\n'shoot' or 'move'? "
                    + "\nDirection and count: "));
  }

  @Test
  public void testMove() {
    StringBuilder log = new StringBuilder();
    IController controller = new HtwController(
            new Scanner("greg 10 standard move"), log, new ActionByDirStrategy());
    controller.run();
    assertTrue(log.toString().startsWith(
            "\nPlayer name (first only): "
                    + "\nStarting arrow count: "
                    + "\nMaze type ('standard' or 'custom'): "
                    + "\nStarting game..."
                    + "\nQuit -> 'q' / 'quit'"
                    + "\nRestart -> 'restart'"
                    + "\n'shoot' or 'move'? "
                    + "\nMove to: "));
  }
}
