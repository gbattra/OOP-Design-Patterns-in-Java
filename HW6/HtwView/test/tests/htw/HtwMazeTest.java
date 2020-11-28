package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.HtwPlayer;
import htw.game.IHtwPlayer;
import htw.game.DirActionStrategy;
import htw.game.Round;
import htw.game.commands.IdActionStrategy;
import htw.level.HtwMaze;
import htw.level.IHtwMaze;
import htw.level.Cave;
import htw.level.IHtwNode;
import htw.level.StandardStrategy;
import htw.level.WumpusStrategy;
import maze.components.Coordinates;
import maze.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for HtwMaze.
 */
public class HtwMazeTest {
  private IHtwNode root;
  private IHtwMaze maze;
  private StringBuilder log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    IHtwNode north = new Cave(1, new Coordinates(1, 0), new StandardStrategy(), log);
    IHtwNode south = new Cave(2, new Coordinates(1, 2), new StandardStrategy(), log);
    IHtwNode east = new Cave(3, new Coordinates(2, 1), new StandardStrategy(), log);
    IHtwNode west = new Cave(4, new Coordinates(0, 1), new StandardStrategy(), log);

    root = new Cave(5, new Coordinates(1, 1), new StandardStrategy(), log);
    root.setNode(north, Direction.NORTH);
    root.setNode(south, Direction.SOUTH);
    root.setNode(east, Direction.EAST);
    root.setNode(west, Direction.WEST);

    north.setNode(root, Direction.NORTH.opposite());
    south.setNode(root, Direction.SOUTH.opposite());
    east.setNode(root, Direction.EAST.opposite());
    west.setNode(root, Direction.WEST.opposite());

    this.maze = new HtwMaze(root, log);
  }

  @Test
  public void testConstructor() {
    try {
      IHtwNode root = new Cave(1, new Coordinates(0, 0), new StandardStrategy(), log);
      IHtwMaze maze = new HtwMaze(root, log);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwMaze maze = new HtwMaze(null, log);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testInvalidMove() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
      IHtwNode root = new Cave(1, new Coordinates(0, 0), new StandardStrategy(), log);
      IHtwMaze maze = new HtwMaze(root, log);
      assertFalse(maze.move(Direction.EAST, new Round(0)));
    } catch (Exception e) {
      fail("Invalid move should have return false, not thrown.");
    }
  }

  @Test
  public void testMoveByDir() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
      IHtwMaze maze = new HtwMaze(root, log);
      assertTrue(maze.move(Direction.EAST, new Round(0)));
      assertEquals(
              "You are in cave (2, 1) with tunnels to the WEST",
              maze.status(new DirActionStrategy()));
    } catch (Exception e) {
      fail("Invalid move should have return false, not thrown.");
    }
  }

  @Test
  public void testMoveById() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
      IHtwMaze maze = new HtwMaze(root, log);
      assertTrue(maze.move(3, new Round(0)));
      assertEquals(
              "You are in cave 3 with tunnels to node(s) 5",
              maze.status(new IdActionStrategy()));
    } catch (Exception e) {
      fail("Invalid move should have return false, not thrown.");
    }
  }

  @Test
  public void testShootById() {
    assertFalse(this.maze.shoot(Direction.EAST, 1));
    this.root.setNode(
            new Cave(15, new Coordinates(2, 1), new WumpusStrategy(), log), Direction.EAST);
    assertTrue(this.maze.shoot(15, 1));
  }

  @Test
  public void testShootByDir() {
    assertFalse(this.maze.shoot(Direction.EAST, 1));
    this.root.setNode(
            new Cave(15, new Coordinates(2, 1), new WumpusStrategy(), log), Direction.EAST);
    assertTrue(this.maze.shoot(Direction.EAST, 1));
  }
}
