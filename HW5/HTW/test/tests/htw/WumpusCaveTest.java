package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.HtwPlayer;
import htw.game.IHtwPlayer;
import htw.level.Cave;
import htw.level.IHtwNode;
import htw.level.IHtwNodeStrategy;
import htw.level.StandardStrategy;
import htw.level.WumpusStrategy;
import maze.components.Coordinates;
import maze.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the wumpus cave.
 */
public class WumpusCaveTest {
  private IHtwNodeStrategy standard = new StandardStrategy();
  private IHtwNode wumpus;
  private Appendable log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.wumpus = new Cave(
            1,
            new Coordinates(1, 1),
            new WumpusStrategy(), log);

    IHtwNode north = new Cave(2, new Coordinates(1, 0), this.standard, log);
    IHtwNode south = new Cave(3, new Coordinates(1, 2), this.standard, log);
    IHtwNode east = new Cave(4, new Coordinates(2, 1), this.standard, log);
    IHtwNode west = new Cave(5, new Coordinates(0, 1), this.standard, log);

    this.wumpus.setNode(north, Direction.NORTH);
    this.wumpus.setNode(south, Direction.SOUTH);
    this.wumpus.setNode(east, Direction.EAST);
    this.wumpus.setNode(west, Direction.WEST);

    north.setNode(this.wumpus, Direction.NORTH.opposite());
    south.setNode(this.wumpus, Direction.SOUTH.opposite());
    east.setNode(this.wumpus, Direction.EAST.opposite());
    west.setNode(this.wumpus, Direction.WEST.opposite());
  }

  @Test
  public void testShoot() {
    assertTrue(this.wumpus.shoot(Direction.SOUTH, 0));
    assertFalse(this.wumpus.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testReceivePlayer() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
      this.wumpus.receive(player);
      assertFalse(player.isAlive());
      assertEquals("Chomp chomp! You are eaten by the Wumpus!\n", this.log.toString());
    } catch (Exception e) {
      fail("Valid receive() should not have failed.");
    }
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Wumpus", this.wumpus.toString());
  }

  @Test
  public void testSmelly() {
    assertTrue(this.wumpus.smelly(Direction.WEST));
  }
}
