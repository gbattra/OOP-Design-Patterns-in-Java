package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.DeadEnd;
import htw.nodes.INode;
import htw.strategies.StandardStrategy;
import htw.strategies.INodeStrategy;
import htw.strategies.TunnelStrategy;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TunnelTest {
  private INodeStrategy strategy = new TunnelStrategy();
  private INode north;
  private INode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(new Coordinates(1, 0), new StandardStrategy());
    this.tunnel = new Cave(new Coordinates(1, 1), this.strategy);
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
}
