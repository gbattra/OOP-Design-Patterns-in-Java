package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.maze.nodes.Cave;
import htw.maze.nodes.DeadEnd;
import htw.maze.nodes.INode;
import htw.maze.strategies.StandardStrategy;
import htw.maze.strategies.INodeStrategy;
import htw.maze.strategies.TunnelStrategy;
import htw.maze.strategies.WumpusStrategy;
import maze.components.Coordinates;
import maze.components.nodes.DeadEndNode;
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
    this.north = new Cave(1, new Coordinates(1, 0), this.strategy);
    this.south = new Cave(2, new Coordinates(1, 2), this.strategy);
    this.east = new Cave(3, new Coordinates(2, 1), this.strategy);
    this.west = new Cave(4, new Coordinates(0, 1), this.strategy);

    this.cave = new Cave(5, new Coordinates(1, 1), this.strategy);
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
  public void testShootMiss() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testShootHitTunnel() {
    INode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy());
    INode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy());
    INode eastTwo = new Cave(12, new Coordinates(3, 1), new TunnelStrategy());

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    assertTrue(this.cave.shoot(Direction.EAST, 1));
  }

  @Test
  public void testShootHitCaveMiss() {
    INode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy());
    INode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy());
    INode eastTwo = new Cave(12, new Coordinates(3, 1), new StandardStrategy());

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    assertFalse(this.cave.shoot(Direction.EAST, 1));
    assertFalse(this.cave.shoot(Direction.EAST, 2));
  }

  @Test
  public void testShootHitCaveStrike() {
    INode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy());
    INode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy());
    INode eastTwo = new Cave(12, new Coordinates(3, 1), new StandardStrategy());

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.EAST);
    wumpus.setNode(eastTwo, Direction.WEST);

    assertFalse(this.cave.shoot(Direction.EAST, 2));
  }

  @Test
  public void testSetStrategy() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
    INodeStrategy newStrat = new WumpusStrategy();
    ((INode) this.cave.getNode(Direction.SOUTH)).setStrategy(newStrat);
    assertTrue(this.cave.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Standard", this.cave.toString());
  }

  @Test
  public void testSmelly() {
    assertFalse(this.cave.smelly());
  }

  @Test
  public void testDrafty() {
    assertFalse(this.cave.drafty());
  }

  @Test
  public void testGet() {
    INode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy());
    INode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy());
    INode eastTwo = new Cave(12, new Coordinates(3, 1), new StandardStrategy());

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    INode retrieved = this.cave.get(10);
    assertEquals(wumpus, retrieved);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGet() {
    this.cave.get(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotFound() {
    this.cave.get(11);
  }

  @Test
  public void testDirectionTo() {
    assertEquals(Direction.NORTH, this.cave.directionTo(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDirectionTo() {
    this.cave.setNode(new DeadEnd(), Direction.NORTH);
    this.cave.directionTo(1);
    fail("Invalid directionTo() should have failed.");
  }
}
