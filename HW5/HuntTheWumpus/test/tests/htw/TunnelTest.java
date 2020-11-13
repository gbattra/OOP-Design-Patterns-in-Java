package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.level.nodes.Cave;
import htw.level.nodes.DeadEnd;
import htw.level.nodes.IHtwNode;
import htw.level.strategies.IHtwNodeStrategy;
import htw.level.strategies.PitStrategy;
import htw.level.strategies.StandardStrategy;
import htw.level.strategies.TunnelStrategy;
import htw.level.strategies.WumpusStrategy;
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
  private IHtwNodeStrategy strategy = new TunnelStrategy();
  private IHtwNode north;
  private IHtwNode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(1, new Coordinates(1, 0), new StandardStrategy(), System.out);
    this.tunnel = new Cave(1, new Coordinates(1, 1), this.strategy, System.out);
    this.tunnel.setNode(this.north, Direction.NORTH);
    this.north.setNode(this.tunnel, Direction.SOUTH);
  }

  @Test
  public void testValidEnter() {
    IHtwNode entered = this.tunnel.enter(Direction.SOUTH);
    assertEquals(this.north, entered);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEnter() {
    this.tunnel.setNode(new DeadEnd(), Direction.NORTH);
    IHtwNode entered = this.tunnel.enter(Direction.SOUTH);
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
