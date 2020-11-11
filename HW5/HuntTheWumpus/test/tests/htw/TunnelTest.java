package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.Cave;
import htw.DeadEnd;
import htw.HTWNode;
import htw.Tunnel;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TunnelTest {
  private HTWNode north;
  private HTWNode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(new MazeCoordinates(1, 0));
    this.tunnel = new Tunnel(new MazeCoordinates(1, 1));
    this.tunnel.setNode(this.north, Direction.NORTH);
    this.north.setNode(this.tunnel, Direction.SOUTH);
  }

  @Test
  public void testPromote() {
    HTWNode promoted = this.tunnel.promote();
    assertEquals(this.north, promoted.getNode(Direction.NORTH));
    assertEquals(this.north.getNode(Direction.SOUTH), promoted);
  }

  @Test
  public void testValidEnter() {
    HTWNode entered = this.tunnel.enter(Direction.SOUTH);
    assertEquals(this.north, entered);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEnter() {
    this.tunnel.setNode(new DeadEnd(), Direction.NORTH);
    this.tunnel.enter(Direction.SOUTH);
    fail("Invalid enter() should have failed.");
  }
}
