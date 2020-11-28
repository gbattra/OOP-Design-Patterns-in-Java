package tests.htw;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import htw.game.Round;
import htw.level.Cave;
import htw.level.DeadEnd;
import htw.level.IHtwNode;
import htw.level.IHtwNodeStrategy;
import htw.level.PitStrategy;
import htw.level.StandardStrategy;
import htw.level.TunnelStrategy;
import htw.level.WumpusStrategy;
import maze.components.Coordinates;
import maze.Direction;

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
    try {
      IHtwNode entered = this.tunnel.enter(Direction.SOUTH, new Round(0));
      assertEquals(this.north, entered);
    } catch (Exception e) {
      fail("Valid enter() should not have failed.");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEnter() {
    try {
      this.tunnel.setNode(new DeadEnd(), Direction.NORTH);
      IHtwNode entered = this.tunnel.enter(Direction.SOUTH, new Round(0));
      fail("Invalid enter() should have failed.");
    } catch (IOException e) {
      // do nothing
    }
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
    this.tunnel.setNode(
            new Cave(
                    1,
                    new Coordinates(2, 1),
                    new PitStrategy(),
                    System.out),
            Direction.EAST);
    assertTrue(this.tunnel.drafty(Direction.WEST));
  }

  @Test
  public void testDratyFalse() {
    assertFalse(this.tunnel.drafty(Direction.SOUTH));
  }

  @Test
  public void testSmellyTrue() {
    this.tunnel.setNode(
            new Cave(
                    1,
                    new Coordinates(2, 1),
                    new WumpusStrategy(),
                    System.out),
            Direction.EAST);
    assertTrue(this.tunnel.smelly(Direction.WEST));
  }

  @Test
  public void testSmellyFalse() {
    assertFalse(this.tunnel.smelly(Direction.SOUTH));
  }
}
