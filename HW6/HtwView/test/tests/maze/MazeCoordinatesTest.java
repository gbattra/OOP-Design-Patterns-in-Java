package tests.maze;

import org.junit.Test;

import maze.components.Coordinates;
import maze.components.ICoordinates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the MazeCoordinates.
 */
public class MazeCoordinatesTest {
  @Test
  public void testValidConstructor() {
    try {
      ICoordinates coordinates = new Coordinates(1, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorX() {
    ICoordinates coordinates = new Coordinates(-1, 0);
    fail("Invalid constructor should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorY() {
    ICoordinates coordinates = new Coordinates(0, -1);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGetters() {
    ICoordinates coordinates = new Coordinates(1, 2);
    assertEquals(1, coordinates.getX());
    assertEquals(2, coordinates.getY());
  }

  @Test
  public void testToString() {
    ICoordinates coordinates = new Coordinates(2, 3);
    assertEquals("(2, 3)", coordinates.toString());
  }

  @Test
  public void testEquals() {
    ICoordinates coordinatesOne = new Coordinates(1, 1);
    ICoordinates coordinatesTwo = new Coordinates(1, 1);
    assertEquals(coordinatesOne, coordinatesTwo);
  }
}
