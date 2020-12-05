package tests;

import org.junit.Test;

import gui.GuiController;
import gui.IGuiController;
import gui.RestartRequest;
import maze.Direction;
import tests.MockGameBuilder;
import tests.MockGuiView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the GuiController.
 */
public class GuiControllerTest {
  private StringBuffer log = new StringBuffer();

  @Test
  public void testValidConstructor() {
    try {
      IGuiController controller =
              new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IGuiController controller = new GuiController(null, null);
    fail();
  }

  @Test
  public void testStartNew() {
    IGuiController controller =
            new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    controller.startNew();
    assertEquals(
            "Added configuration\n"
            + "Starting\n"
            + "populating the game\n",
            log.toString());
  }

  @Test
  public void testRestartSameMaze() {
    IGuiController controller =
            new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    RestartRequest restartRequest =
            new RestartRequest(true, false, false, 10, 80, 10, 10, 0.3, 0.3);
    controller.restart(restartRequest);
    assertEquals(
            "Added configuration\n"
            + "Starting\n"
            + "populating the game\n",
            log.toString());
  }

  @Test
  public void testRestartNewMaze() {
    IGuiController controller =
            new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    RestartRequest restartRequest =
            new RestartRequest(false, false, false, 10, 80, 10, 10, 0.3, 0.3);
    controller.restart(restartRequest);
    assertEquals(
            "Added configuration\n"
            + "Starting\n"
            + "populating the game\n",
            log.toString());
  }

  @Test
  public void testMoveDirection() {
    IGuiController controller =
            new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    controller.startNew();
    controller.onMove(Direction.WEST);
    assertEquals(
            "Added configuration\n"
            + "Starting\n"
            + "populating the game\n"
            + "move - WESTpopulating the game\n",
            log.toString());
  }

  @Test
  public void testMoveId() {
    IGuiController controller =
            new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    controller.startNew();
    controller.onMove(2);
    assertEquals(
            "Added configuration\n"
            + "Starting\n"
            + "populating the game\n"
            + "move - 2populating the game\n",
            log.toString());
  }

  @Test
  public void testShoot() {
    IGuiController controller =
            new GuiController(new MockGuiView(log), new MockGameBuilder(log));
    controller.startNew();
    controller.onShoot(2, 1);
    assertEquals(
            "Added configuration\n"
            + "Starting\n"
            + "populating the game\n"
            + "shoot - 2 - 1populating the game\n",
            log.toString());
  }
}
