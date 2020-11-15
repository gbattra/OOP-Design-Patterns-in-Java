package tests.maze.utils;

import org.junit.Test;

import maze.utils.Direction;

import static org.junit.Assert.assertEquals;

/**
 * Test for the DirectionHelper.
 */
public class DirectionTest {
  @Test
  public void testOppositeOf() {
    assertEquals(Direction.SOUTH,Direction.NORTH.opposite());
    assertEquals(Direction.NORTH, Direction.SOUTH.opposite());
    assertEquals(Direction.EAST, Direction.WEST.opposite());
    assertEquals(Direction.WEST, Direction.EAST.opposite());
  }

  @Test
  public void testStringToDirection() {
    assertEquals(Direction.NORTH, Direction.stringToDirection("N"));
    assertEquals(Direction.NORTH, Direction.stringToDirection("NORTH"));

    assertEquals(Direction.SOUTH, Direction.stringToDirection("S"));
    assertEquals(Direction.SOUTH, Direction.stringToDirection("SOUTH"));

    assertEquals(Direction.EAST, Direction.stringToDirection("E"));
    assertEquals(Direction.EAST, Direction.stringToDirection("EAST"));

    assertEquals(Direction.WEST, Direction.stringToDirection("W"));
    assertEquals(Direction.WEST, Direction.stringToDirection("WEST"));
  }
}
