package tests.maze;

import org.junit.Before;
import org.junit.Test;

import maze.Direction;
import maze.components.Coordinates;
import maze.components.IMaze;
import maze.components.Maze;
import maze.components.Node;
import maze.components.StandardRoomNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the Maze2d.
 */
public class Maze2dTest {
  private Node start;
  private Node node;
  private Node goal;

  @Before
  public void setup() {
    this.start = new StandardRoomNode(new Coordinates(0,0));
    this.node = new StandardRoomNode(new Coordinates(1,0));
    this.goal = new StandardRoomNode(new Coordinates(1,1));

    start.setNode(node, Direction.SOUTH);
    node.setNode(goal, Direction.EAST);
  }

  @Test
  public void testConstructor() {
    try {
      IMaze maze = new Maze(this.start, this.goal);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    IMaze maze = new Maze(this.start, this.goal);
    assertEquals(this.start, maze.getStart());
    assertEquals(this.goal, maze.getGoal());
    assertEquals(this.start, maze.getCurrent());
  }

  @Test
  public void testMove() {
    try {
      IMaze maze = new Maze(this.start, this.goal);
      maze.move(Direction.SOUTH);
      assertEquals(this.node, maze.getCurrent());
    } catch (Exception e) {
      fail("Valid move() should not have failed.");
    }
  }
}
