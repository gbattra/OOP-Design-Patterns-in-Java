package tests.maze;

import org.junit.Test;

import maze.components.Coordinates;
import maze.components.Edge;
import maze.components.ICoordinates;
import maze.components.IEdge;
import maze.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the MazeEdge.
 */
public class MazeEdgeTest {
  @Test
  public void testValidConstructor() {
    try {
      ICoordinates coordinateOne = new Coordinates(1, 1);
      ICoordinates coordinateTwo = new Coordinates(1, 2);
      IEdge mazeEdge = new Edge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    ICoordinates coordinateOne = new Coordinates(1, 1);
    ICoordinates coordinateTwo = new Coordinates(1, 2);
    IEdge mazeEdge = new Edge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    assertEquals(coordinateOne, mazeEdge.getTail());
    assertEquals(coordinateTwo, mazeEdge.getHead());
  }
}
