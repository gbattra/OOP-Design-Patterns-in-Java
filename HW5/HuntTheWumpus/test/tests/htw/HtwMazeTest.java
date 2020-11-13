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
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class HtwMazeTest {
  private IHtwMaze maze;

  @Before
  public void setup() {
    INode north = new Cave(1, new Coordinates(1, 0), new StandardStrategy());
    INode south = new Cave(2, new Coordinates(1, 2), new StandardStrategy());
    INode east = new Cave(3, new Coordinates(2, 1), new StandardStrategy());
    INode west = new Cave(4, new Coordinates(0, 1), new StandardStrategy());

    INode cave = new Cave(5, new Coordinates(1, 1), new StandardStrategy());
    cave.setNode(north, Direction.NORTH);
    cave.setNode(south, Direction.SOUTH);
    cave.setNode(east, Direction.EAST);
    cave.setNode(west, Direction.WEST);

    north.setNode(cave, Direction.NORTH.opposite());
    south.setNode(cave, Direction.SOUTH.opposite());
    east.setNode(cave, Direction.EAST.opposite());
    west.setNode(cave, Direction.WEST.opposite());

    this.maze = new HtwMaze(cave);
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

  }
}
