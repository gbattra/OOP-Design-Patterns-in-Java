package tests.maze.components;

import org.junit.Test;

import maze.utils.Direction;
import maze.components.ICoordinates;
import maze.components.IEdge;
import maze.components.MazeCoordinates;
import maze.components.MazeEdge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the MazeEdge.
 */
public class MazeEdgeTest {
  @Test
  public void testValidConstructor() {
    try {
      ICoordinates coordinateOne = new MazeCoordinates(1, 1);
      ICoordinates coordinateTwo = new MazeCoordinates(1, 2);
      IEdge mazeEdge = new MazeEdge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    ICoordinates coordinateOne = new MazeCoordinates(1, 1);
    ICoordinates coordinateTwo = new MazeCoordinates(1, 2);
    IEdge mazeEdge = new MazeEdge(coordinateOne, coordinateTwo, Direction.WEST, Direction.EAST);
    assertEquals(coordinateOne, mazeEdge.getTail());
    assertEquals(coordinateTwo, mazeEdge.getHead());
  }
}
