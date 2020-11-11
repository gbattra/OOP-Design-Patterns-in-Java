package tests.maze.components;

import org.junit.Before;
import org.junit.Test;

import maze.utils.Direction;
import maze.components.IMaze;
import maze.components.nodes.Node;
import maze.components.Maze2d;
import maze.components.MazeCoordinates;
import maze.components.nodes.StandardRoomNode;

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
    this.start = new StandardRoomNode(new MazeCoordinates(0,0));
    this.node = new StandardRoomNode(new MazeCoordinates(1,0));
    this.goal = new StandardRoomNode(new MazeCoordinates(1,1));

    start.setNode(node, Direction.SOUTH);
    node.setNode(goal, Direction.EAST);
  }

  @Test
  public void testConstructor() {
    try {
      IMaze maze = new Maze2d(this.start, this.goal);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    IMaze maze = new Maze2d(this.start, this.goal);
    assertEquals(this.start, maze.getStart());
    assertEquals(this.goal, maze.getGoal());
    assertEquals(this.start, maze.getCurrent());
  }

  @Test
  public void testMove() {
    IMaze maze = new Maze2d(this.start, this.goal);
    maze.move(Direction.SOUTH);
    assertEquals(this.node, maze.getCurrent());
  }
}
