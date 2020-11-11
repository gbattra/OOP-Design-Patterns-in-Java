package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.Cave;
import htw.MazeNode;
import maze.components.MazeCoordinates;
import maze.components.nodes.Node;
import maze.components.nodes.StandardRoomNode;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CaveTest {
  private MazeNode north;
  private MazeNode south;
  private MazeNode east;
  private MazeNode west;
  private MazeNode cave;

  @Before
  public void setup() {
    this.north = new Cave(new MazeCoordinates(1, 0));
    this.south = new Cave(new MazeCoordinates(1, 2));
    this.east = new Cave(new MazeCoordinates(2, 1));
    this.west = new Cave(new MazeCoordinates(0, 1));
    this.cave = new Cave(new MazeCoordinates(1, 1));
    this.cave.setNode(this.north, Direction.NORTH);
    this.cave.setNode(this.south, Direction.SOUTH);
    this.cave.setNode(this.east, Direction.EAST);
    this.cave.setNode(this.west, Direction.WEST);
  }

  @Test
  public void testPromote() {
    MazeNode promoted = this.cave.promote();
    assertEquals(this.cave, promoted);
  }

  @Test
  public void testEnter() {
    assertEquals(this.cave, this.cave.enter(Direction.NORTH));
  }

  @Test
  public void testValidSetNode() {
    try {
      this.cave.setNode(this.east, Direction.EAST);
    } catch (Exception e) {
      fail("Valid setNode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetNode() {
    Node node = new StandardRoomNode(new MazeCoordinates(0, 0));
    this.cave.setNode(node, Direction.NORTH);
    fail("Invalid setNode() should have failed.");
  }
}
