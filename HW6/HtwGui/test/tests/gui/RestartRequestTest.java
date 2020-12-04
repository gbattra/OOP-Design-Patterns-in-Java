package tests.gui;

import org.junit.Test;

import gui.RestartRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for RestartRequest.
 */
public class RestartRequestTest {
  @Test
  public void testPojo() {
    RestartRequest restartRequest = new RestartRequest(false, true, true, 10, 99, 5, 5, 0.5, 0.3);
    assertFalse(restartRequest.useSameMaze);
    assertTrue(restartRequest.isMultiplayer);
    assertTrue(restartRequest.isRoomMaze);
    assertEquals(10, restartRequest.arrowCount);
    assertEquals(99, restartRequest.finalEdgeCount);
    assertEquals(5, restartRequest.rowCount);
    assertEquals(5, restartRequest.columnCount);
    assertEquals(0.5, restartRequest.pitFrequency, 0.0001);
    assertEquals(0.3, restartRequest.batFrequency, 0.0001);
  }
}
