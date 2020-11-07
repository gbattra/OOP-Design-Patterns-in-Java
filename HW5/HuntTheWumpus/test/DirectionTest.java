import org.junit.Test;

import maze.enums.Direction;

import static org.junit.Assert.assertEquals;

/**
 * Test for the DirectionHelper.
 */
public class DirectionTest {
  @Test
  public void testOppositeOf() {
    assertEquals(Direction.SOUTH, Direction.oppositeOf(Direction.NORTH));
    assertEquals(Direction.NORTH, Direction.oppositeOf(Direction.SOUTH));
    assertEquals(Direction.EAST, Direction.oppositeOf(Direction.WEST));
    assertEquals(Direction.WEST, Direction.oppositeOf(Direction.EAST));
  }
}
