package tests.htw;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import htw.level.nodes.Cave;
import htw.level.nodes.INode;
import htw.level.strategies.BatStrategy;
import htw.level.strategies.INodeStrategy;
import htw.level.strategies.StandardStrategy;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the bat cave.
 */
public class BatCaveTest {
  private INode bat;
  private INodeStrategy standard = new StandardStrategy();
  private INode north;
  private INode south;
  private INode east;
  private INode west;

  @Before
  public void setup() {
    this.bat = new Cave(
            1,
            new Coordinates(1, 1),
            new BatStrategy(2, 2, new Random(1), new StandardStrategy()),
            System.out);

    this.north = new Cave(2, new Coordinates(1, 0), this.standard, System.out);
    this.south = new Cave(3, new Coordinates(1, 2), this.standard, System.out);
    this.east = new Cave(4, new Coordinates(2, 1), this.standard, System.out);
    this.west = new Cave(5, new Coordinates(0, 1), this.standard, System.out);

    this.bat.setNode(this.north, Direction.NORTH);
    this.bat.setNode(this.south, Direction.SOUTH);
    this.bat.setNode(this.east, Direction.EAST);
    this.bat.setNode(this.west, Direction.WEST);

    this.north.setNode(this.bat, Direction.NORTH.opposite());
    this.south.setNode(this.bat, Direction.SOUTH.opposite());
    this.east.setNode(this.bat, Direction.EAST.opposite());
    this.west.setNode(this.bat, Direction.WEST.opposite());
  }

  @Test
  public void testEnter() {
    INode cave = new Cave(6, new Coordinates(0, 0), this.standard, System.out);
    this.north.setNode(cave, Direction.EAST);
    INode node = this.bat.enter(Direction.SOUTH);
    assertEquals(this.bat, node);

    node = this.bat.enter(Direction.SOUTH);
    assertNotEquals(this.bat, node);
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Bat(Standard)", this.bat.toString());
  }
}
