package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.maze.nodes.Cave;
import htw.maze.nodes.DeadEnd;
import htw.maze.nodes.INode;
import htw.maze.strategies.INodeStrategy;
import htw.maze.strategies.PitStrategy;
import htw.maze.strategies.StandardStrategy;
import htw.maze.strategies.TunnelStrategy;
import htw.maze.strategies.WumpusStrategy;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the tunnel.
 */
public class TunnelTest {
  private INodeStrategy strategy = new TunnelStrategy();
  private INode north;
  private INode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(1, new Coordinates(1, 0), new StandardStrategy(), System.out);
    this.tunnel = new Cave(1, new Coordinates(1, 1), this.strategy, System.out);
    this.tunnel.setNode(this.north, Direction.NORTH);
    this.north.setNode(this.tunnel, Direction.SOUTH);
  }

  @Test
  public void testValidEnter() {
    INode entered = this.tunnel.enter(Direction.SOUTH);
    assertEquals(this.north, entered);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEnter() {
    this.tunnel.setNode(new DeadEnd(), Direction.NORTH);
    INode entered = this.tunnel.enter(Direction.SOUTH);
    fail("Invalid enter() should have failed.");
  }

  @Test
  public void testValidShoot() {
    assertFalse(this.tunnel.shoot(Direction.NORTH, 1));
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Tunnel", this.tunnel.toString());
  }

  @Test
  public void testDratyTrue() {
    this.tunnel.setNode(new Cave(1, new Coordinates(2, 1), new PitStrategy(), System.out), Direction.EAST);
    assertTrue(this.tunnel.drafty());
  }

  @Test
  public void testDratyFalse() {
    assertFalse(this.tunnel.drafty());
  }

  @Test
  public void testSmellyTrue() {
    this.tunnel.setNode(new Cave(1, new Coordinates(2, 1), new WumpusStrategy(), System.out), Direction.EAST);
    assertTrue(this.tunnel.smelly());
  }

  @Test
  public void testSmellyFalse() {
    assertFalse(this.tunnel.smelly());
  }
}
