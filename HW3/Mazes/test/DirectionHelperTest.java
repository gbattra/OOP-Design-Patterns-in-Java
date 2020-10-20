import org.junit.Test;

import maze.enums.Direction;
import maze.helpers.DirectionHelper;

import static org.junit.Assert.assertEquals;

public class DirectionHelperTest {
  @Test
  public void testOppositeOf() {
    assertEquals(Direction.SOUTH, DirectionHelper.oppositeOf(Direction.NORTH));
    assertEquals(Direction.NORTH, DirectionHelper.oppositeOf(Direction.SOUTH));
    assertEquals(Direction.EAST, DirectionHelper.oppositeOf(Direction.WEST));
    assertEquals(Direction.WEST, DirectionHelper.oppositeOf(Direction.EAST));
  }
}
