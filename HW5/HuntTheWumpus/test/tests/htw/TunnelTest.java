package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.Cave;
import htw.MazeNode;
import htw.Tunnel;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;

public class TunnelTest {
  private MazeNode north;
  private MazeNode south;
  private MazeNode east;
  private MazeNode west;
  private MazeNode tunnel;

  @Before
  public void setup() {
    this.north = new Cave(new MazeCoordinates(1, 0));
    this.south = new Cave(new MazeCoordinates(1, 2));
    this.east = new Cave(new MazeCoordinates(2, 1));
    this.west = new Cave(new MazeCoordinates(0, 1));
    this.tunnel = new Tunnel(new MazeCoordinates(1, 1));
    this.tunnel.setNode(this.north, Direction.NORTH);
    this.tunnel.setNode(this.south, Direction.SOUTH);
    this.tunnel.setNode(this.east, Direction.EAST);
    this.tunnel.setNode(this.west, Direction.WEST);
  }

  @Test
  public void testPromote() {
    MazeNode promoted = this.tunnel.promote();
    assertEquals(this.north, promoted.getNode(Direction.NORTH));
    assertEquals(this.south, promoted.getNode(Direction.SOUTH));
    assertEquals(this.west, promoted.getNode(Direction.WEST));
    assertEquals(this.east, promoted.getNode(Direction.EAST));

    assertEquals(this.north.getNode(Direction.SOUTH), promoted);
    assertEquals(this.south.getNode(Direction.NORTH), promoted);
    assertEquals(this.east.getNode(Direction.WEST), promoted);
    assertEquals(this.west.getNode(Direction.EAST), promoted);
  }
}
