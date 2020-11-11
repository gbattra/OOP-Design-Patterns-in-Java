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
}
