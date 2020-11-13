package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.IPlayer;
import htw.game.Player;
import htw.maze.nodes.Cave;
import htw.maze.nodes.INode;
import htw.maze.strategies.INodeStrategy;
import htw.maze.strategies.StandardStrategy;
import htw.maze.strategies.WumpusStrategy;
import maze.components.Coordinates;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the wumpus cave.
 */
public class WumpusCaveTest {
  private INodeStrategy standard = new StandardStrategy();
  private INode north;
  private INode south;
  private INode east;
  private INode west;
  private INode wumpus;

  @Before
  public void setup() {
    this.wumpus = new Cave(
            1,
            new Coordinates(1, 1),
            new WumpusStrategy());

    this.north = new Cave(2, new Coordinates(1, 0), this.standard);
    this.south = new Cave(3, new Coordinates(1, 2), this.standard);
    this.east = new Cave(4, new Coordinates(2, 1), this.standard);
    this.west = new Cave(5, new Coordinates(0, 1), this.standard);

    this.wumpus.setNode(this.north, Direction.NORTH);
    this.wumpus.setNode(this.south, Direction.SOUTH);
    this.wumpus.setNode(this.east, Direction.EAST);
    this.wumpus.setNode(this.west, Direction.WEST);

    this.north.setNode(this.wumpus, Direction.NORTH.opposite());
    this.south.setNode(this.wumpus, Direction.SOUTH.opposite());
    this.east.setNode(this.wumpus, Direction.EAST.opposite());
    this.west.setNode(this.wumpus, Direction.WEST.opposite());
  }

  @Test
  public void testShoot() {
    assertTrue(this.wumpus.shoot(Direction.SOUTH, 0));
    assertFalse(this.wumpus.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testReceivePlayer() {
    IPlayer player = new Player("Joe", 10);
    this.wumpus.receive(player);
    assertFalse(player.isAlive());
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Wumpus", this.wumpus.toString());
  }

  @Test
  public void testSmelly() {
    assertTrue(this.wumpus.smelly());
  }
}
