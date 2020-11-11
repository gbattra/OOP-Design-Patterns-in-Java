package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.DeadEnd;
import htw.nodes.HtwNode;
import htw.strategies.StandardStrategy;
import htw.strategies.HtwNodeStrategy;
import htw.strategies.TunnelStrategy;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TunnelTest {
  private HtwNodeStrategy strategy = new TunnelStrategy();
  private HtwNode north;
  private HtwNode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(new MazeCoordinates(1, 0), new StandardStrategy());
    this.tunnel = new Cave(new MazeCoordinates(1, 1), this.strategy);
    this.tunnel.setNode(this.north, Direction.NORTH);
    this.north.setNode(this.tunnel, Direction.SOUTH);
  }

  @Test
  public void testValidEnter() {
    HtwNode entered = this.tunnel.enter(Direction.SOUTH);
    assertEquals(this.north, entered);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEnter() {
    this.tunnel.setNode(new DeadEnd(), Direction.NORTH);
    HtwNode entered = this.tunnel.enter(Direction.SOUTH);
    fail("Invalid enter() should have failed.");
  }

  @Test
  public void testValidShoot() {
    assertFalse(this.tunnel.shoot(Direction.NORTH, 1));
  }
}
