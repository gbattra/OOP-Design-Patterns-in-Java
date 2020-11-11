package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.DeadEnd;
import htw.nodes.HTWNode;
import htw.strategies.StandardStrategy;
import htw.strategies.Strategy;
import htw.strategies.TunnelStrategy;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TunnelStrategyTest {
  private Strategy strategy = new TunnelStrategy();
  private HTWNode north;
  private HTWNode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(new MazeCoordinates(1, 0), new StandardStrategy());
    this.tunnel = new Cave(new MazeCoordinates(1, 1), this.strategy);
    this.tunnel.setNode(this.north, Direction.NORTH);
    this.north.setNode(this.tunnel, Direction.SOUTH);
  }

  @Test
  public void testValidEnter() {
    HTWNode entered = this.strategy.enter(Direction.SOUTH, this.tunnel);
    assertEquals(this.north, entered);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEnter() {
    this.tunnel.setNode(new DeadEnd(), Direction.NORTH);
    HTWNode entered = this.strategy.enter(Direction.SOUTH, this.tunnel);
    fail("Invalid enter() should have failed.");
  }
}
