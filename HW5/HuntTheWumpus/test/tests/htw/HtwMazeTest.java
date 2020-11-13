package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.IPlayer;
import htw.game.Player;
import htw.maze.HtwMaze;
import htw.maze.IHtwMaze;
import htw.maze.nodes.Cave;
import htw.maze.nodes.INode;
import htw.maze.strategies.StandardStrategy;
import htw.maze.strategies.WumpusStrategy;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HtwMazeTest {
  private INode north;
  private INode south;
  private INode east;
  private INode west;
  private INode root;
  private IHtwMaze maze;

  @Before
  public void setup() {
    this.north = new Cave(1, new Coordinates(1, 0), new StandardStrategy());
    this.south = new Cave(2, new Coordinates(1, 2), new StandardStrategy());
    this.east = new Cave(3, new Coordinates(2, 1), new StandardStrategy());
    this.west = new Cave(4, new Coordinates(0, 1), new StandardStrategy());

    this.root = new Cave(5, new Coordinates(1, 1), new StandardStrategy());
    this.root.setNode(this.north, Direction.NORTH);
    this.root.setNode(this.south, Direction.SOUTH);
    this.root.setNode(this.east, Direction.EAST);
    this.root.setNode(this.west, Direction.WEST);

    this.north.setNode(this.root, Direction.NORTH.opposite());
    this.south.setNode(this.root, Direction.SOUTH.opposite());
    this.east.setNode(this.root, Direction.EAST.opposite());
    this.west.setNode(this.root, Direction.WEST.opposite());

    this.maze = new HtwMaze(root);
  }

  @Test
  public void testConstructor() {
    try {
      INode root = new Cave(1, new Coordinates(0, 0), new StandardStrategy());
      IHtwMaze maze = new HtwMaze(root);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwMaze maze = new HtwMaze(null);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testInvalidMove() {
    IPlayer player = new Player("Joe", 10);
    INode root = new Cave(1, new Coordinates(0, 0), new StandardStrategy());
    IHtwMaze maze = new HtwMaze(root);
    assertFalse(maze.move(Direction.EAST, player));
  }

  @Test
  public void testMoveById() {
    assertFalse(this.maze.shoot(Direction.EAST, 1));
    this.root.setNode(new Cave(15, new Coordinates(2, 1), new WumpusStrategy()), Direction.EAST);
    assertTrue(this.maze.shoot(Direction.EAST, 1));
  }
}
