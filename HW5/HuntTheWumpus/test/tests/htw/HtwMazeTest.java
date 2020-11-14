package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.IHtwPlayer;
import htw.game.HtwPlayer;
import htw.level.HtwMaze;
import htw.level.IHtwMaze;
import htw.level.nodes.Cave;
import htw.level.nodes.IHtwNode;
import htw.level.strategies.StandardStrategy;
import htw.level.strategies.WumpusStrategy;
import htw.tools.HtwConfiguration;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HtwMazeTest {
  private IHtwNode north;
  private IHtwNode south;
  private IHtwNode east;
  private IHtwNode west;
  private IHtwNode root;
  private IHtwMaze maze;

  @Before
  public void setup() {
    this.north = new Cave(1, new Coordinates(1, 0), new StandardStrategy(), System.out);
    this.south = new Cave(2, new Coordinates(1, 2), new StandardStrategy(), System.out);
    this.east = new Cave(3, new Coordinates(2, 1), new StandardStrategy(), System.out);
    this.west = new Cave(4, new Coordinates(0, 1), new StandardStrategy(), System.out);

    this.root = new Cave(5, new Coordinates(1, 1), new StandardStrategy(), System.out);
    this.root.setNode(this.north, Direction.NORTH);
    this.root.setNode(this.south, Direction.SOUTH);
    this.root.setNode(this.east, Direction.EAST);
    this.root.setNode(this.west, Direction.WEST);

    this.north.setNode(this.root, Direction.NORTH.opposite());
    this.south.setNode(this.root, Direction.SOUTH.opposite());
    this.east.setNode(this.root, Direction.EAST.opposite());
    this.west.setNode(this.root, Direction.WEST.opposite());

    this.maze = new HtwMaze(root, System.out);
  }

  @Test
  public void testConstructor() {
    try {
      IHtwNode root = new Cave(1, new Coordinates(0, 0), new StandardStrategy(), System.out);
      IHtwMaze maze = new HtwMaze(root, System.out);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwMaze maze = new HtwMaze(null, System.out);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testInvalidMove() {
    IHtwPlayer player = new HtwPlayer("Joe", 10);
    IHtwNode root = new Cave(1, new Coordinates(0, 0), new StandardStrategy(), System.out);
    IHtwMaze maze = new HtwMaze(root, System.out);
    assertFalse(maze.move(Direction.EAST, player));
  }

  @Test
  public void testMoveById() {
    assertFalse(this.maze.shoot(Direction.EAST, 1));
    this.root.setNode(new Cave(15, new Coordinates(2, 1), new WumpusStrategy(), System.out), Direction.EAST);
    assertTrue(this.maze.shoot(Direction.EAST, 1));
  }
}
