package tests.htw;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import htw.nodes.Cave;
import htw.nodes.HtwNode;
import htw.strategies.BatStrategy;
import htw.strategies.HtwNodeStrategy;
import htw.strategies.StandardStrategy;
import maze.components.MazeCoordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BatCaveTest {
  private HtwNode bat;
  private HtwNodeStrategy standard = new StandardStrategy();
  private HtwNode north;
  private HtwNode south;
  private HtwNode east;
  private HtwNode west;

  @Before
  public void setup() {
    this.bat = new Cave(
            new MazeCoordinates(1, 1),
            new BatStrategy(2, 2, new Random(1), new StandardStrategy()));

    this.north = new Cave(new MazeCoordinates(1, 0), this.standard);
    this.south = new Cave(new MazeCoordinates(1, 2), this.standard);
    this.east = new Cave(new MazeCoordinates(2, 1), this.standard);
    this.west = new Cave(new MazeCoordinates(0, 1), this.standard);

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
    HtwNode cave = new Cave(new MazeCoordinates(0, 0), this.standard);
    this.north.setNode(cave, Direction.EAST);
    HtwNode node = this.bat.enter(Direction.SOUTH);
    assertEquals(this.bat, node);

    node = this.bat.enter(Direction.SOUTH);
    assertNotEquals(this.bat, node);
  }
}
