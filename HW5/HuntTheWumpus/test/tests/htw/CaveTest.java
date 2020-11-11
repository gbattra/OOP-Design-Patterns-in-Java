package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.HTWNode;
import htw.strategies.StandardStrategy;
import htw.strategies.Strategy;
import maze.components.MazeCoordinates;
import maze.components.nodes.Node;
import maze.components.nodes.StandardRoomNode;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class CaveTest {
  private Strategy strategy = new StandardStrategy();
  private HTWNode north;
  private HTWNode south;
  private HTWNode east;
  private HTWNode west;
  private HTWNode cave;

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
