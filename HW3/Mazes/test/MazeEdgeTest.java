import org.junit.Test;

import maze.enums.Direction;
import maze.interfaces.Coordinates;
import maze.interfaces.Edge;
import maze.models.MazeCoordinates;
import maze.models.MazeEdge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MazeEdgeTest {
  @Test
  public void testValidConstructor() {
    try {
      Coordinates coordinateOne = new MazeCoordinates(1, 1);
      Coordinates coordinateTwo = new MazeCoordinates(1, 2);
      Edge mazeEdge = new MazeEdge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    Coordinates coordinateOne = new MazeCoordinates(1, 1);
    Coordinates coordinateTwo = new MazeCoordinates(1, 2);
    Edge mazeEdge = new MazeEdge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    assertEquals(coordinateOne, mazeEdge.getTail());
    assertEquals(coordinateTwo, mazeEdge.getHead());
  }
}
