package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.INode;
import htw.strategies.StandardStrategy;
import htw.strategies.INodeStrategy;
import htw.strategies.WumpusStrategy;
import maze.components.Coordinates;
import maze.components.nodes.Node;
import maze.components.nodes.StandardRoomNode;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the cave.
 */
public class CaveTest {
  private INodeStrategy strategy = new StandardStrategy();
  private INode north;
  private INode south;
  private INode east;
  private INode west;
  private INode cave;

  @Before
  public void setup() {
    this.north = new Cave(new Coordinates(1, 0), this.strategy);
    this.south = new Cave(new Coordinates(1, 2), this.strategy);
    this.east = new Cave(new Coordinates(2, 1), this.strategy);
    this.west = new Cave(new Coordinates(0, 1), this.strategy);

    this.cave = new Cave(new Coordinates(1, 1), this.strategy);
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
    Node node = new StandardRoomNode(new Coordinates(0, 0));
    this.cave.setNode(node, Direction.NORTH);
    fail("Invalid setNode() should have failed.");
  }

  @Test
  public void testShoot() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testSetStrategy() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
    INodeStrategy newStrat = new WumpusStrategy();
    ((INode) this.cave.getNode(Direction.SOUTH)).setStrategy(newStrat);
    assertTrue(this.cave.shoot(Direction.SOUTH, 1));
  }
}
