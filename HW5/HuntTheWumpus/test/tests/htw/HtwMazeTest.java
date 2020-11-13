package tests.htw;

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
}
