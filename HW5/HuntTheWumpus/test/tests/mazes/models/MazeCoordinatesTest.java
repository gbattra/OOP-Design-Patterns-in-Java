package tests.mazes.models;

import org.junit.Test;

import maze.interfaces.Coordinates;
import maze.models.MazeCoordinates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the MazeCoordinates.
 */
public class MazeCoordinatesTest {
  @Test
  public void testValidConstructor() {
    try {
      Coordinates coordinates = new MazeCoordinates(1, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorX() {
    Coordinates coordinates = new MazeCoordinates(-1, 0);
    fail("Invalid constructor should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorY() {
    Coordinates coordinates = new MazeCoordinates(0, -1);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGetters() {
    Coordinates coordinates = new MazeCoordinates(1, 2);
    assertEquals(1, coordinates.getX());
    assertEquals(2, coordinates.getY());
  }

  @Test
  public void testToString() {
    Coordinates coordinates = new MazeCoordinates(2, 3);
    assertEquals("(3, 2)", coordinates.toString());
  }

  @Test
  public void testEquals() {
    Coordinates coordinatesOne = new MazeCoordinates(1, 1);
    Coordinates coordinatesTwo = new MazeCoordinates(1, 1);
    assertEquals(coordinatesOne, coordinatesTwo);
  }
}
