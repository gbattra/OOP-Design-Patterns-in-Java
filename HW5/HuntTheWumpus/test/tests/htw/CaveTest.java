package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.HtwNode;
import htw.strategies.StandardStrategy;
import htw.strategies.HtwNodeStrategy;
import maze.components.MazeCoordinates;
import maze.components.nodes.Node;
import maze.components.nodes.StandardRoomNode;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class CaveTest {
  private HtwNodeStrategy strategy = new StandardStrategy();
  private HtwNode north;
  private HtwNode south;
  private HtwNode east;
  private HtwNode west;
  private HtwNode cave;

  @Before
  public void setup() {
    this.north = new Cave(new MazeCoordinates(1, 0), this.strategy);
    this.south = new Cave(new MazeCoordinates(1, 2), this.strategy);
    this.east = new Cave(new MazeCoordinates(2, 1), this.strategy);
    this.west = new Cave(new MazeCoordinates(0, 1), this.strategy);

    this.cave = new Cave(new MazeCoordinates(1, 1), this.strategy);
    this.cave.setNode(this.north, Direction.NORTH);
    this.cave.setNode(this.south, Direction.SOUTH);
    this.cave.setNode(this.east, Direction.EAST);
    this.cave.setNode(this.west, Direction.WEST);

    this.north.setNode(this.cave, Direction.NORTH.opposite());
    this.south.setNode(this.cave, Direction.SOUTH.opposite());
    this.east.setNode(this.cave, Direction.EAST.opposite());
    this.west.setNode(this.cave, Direction.WEST.opposite());
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

  @Test
  public void testShoot() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
  }
}
