package tests.htw.level.nodes;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import htw.level.nodes.Cave;
import htw.level.nodes.IHtwNode;
import htw.level.strategies.BatStrategy;
import htw.level.strategies.IHtwNodeStrategy;
import htw.level.strategies.StandardStrategy;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the bat cave.
 */
public class BatCaveTest {
  private IHtwNode bat;
  private IHtwNodeStrategy standard = new StandardStrategy();
  private IHtwNode north;
  private IHtwNode south;
  private IHtwNode east;
  private IHtwNode west;

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
    try {
      IHtwNode cave = new Cave(6, new Coordinates(0, 0), this.standard, System.out);
      this.north.setNode(cave, Direction.EAST);
      IHtwNode node = this.bat.enter(Direction.SOUTH);
      assertEquals(this.bat, node);

      node = this.bat.enter(Direction.SOUTH);
      assertNotEquals(this.bat, node);
    } catch (Exception e) {
      fail("Valid enter() should not have failed.");
    }
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Bat(Standard)", this.bat.toString());
  }
}