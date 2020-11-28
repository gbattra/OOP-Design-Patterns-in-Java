package tests.htw;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import htw.game.HtwPlayer;
import htw.game.IHtwPlayer;
import htw.game.Round;
import htw.level.Cave;
import htw.level.DeadEnd;
import htw.level.IHtwNode;
import htw.level.IHtwNodeStrategy;
import htw.level.PitStrategy;
import htw.level.StandardStrategy;
import htw.level.TunnelStrategy;
import htw.level.WumpusStrategy;
import maze.components.Coordinates;
import maze.components.Node;
import maze.components.StandardRoomNode;
import maze.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the cave.
 */
public class CaveTest {
  private IHtwNodeStrategy strategy = new StandardStrategy();
  private IHtwNode north;
  private IHtwNode south;
  private IHtwNode east;
  private IHtwNode west;
  private IHtwNode cave;
  private Appendable log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.north = new Cave(1, new Coordinates(1, 0), this.strategy, log);
    this.south = new Cave(2, new Coordinates(1, 2), this.strategy, log);
    this.east = new Cave(3, new Coordinates(2, 1), this.strategy, log);
    this.west = new Cave(4, new Coordinates(0, 1), this.strategy, log);

    this.cave = new Cave(5, new Coordinates(1, 1), this.strategy, log);
    this.cave.setNode(this.north, Direction.NORTH);
    this.cave.setNode(this.south, Direction.SOUTH);
    this.cave.setNode(this.east, Direction.EAST);
    this.cave.setNode(this.west, Direction.WEST);

    this.north.setNode(this.cave, Direction.NORTH.opposite());
    this.south.setNode(this.cave, Direction.SOUTH.opposite());
    this.east.setNode(this.cave, Direction.EAST.opposite());
    this.west.setNode(this.cave, Direction.WEST.opposite());
  }

  @Test
  public void testEnter() {
    try {
      assertEquals(this.cave, this.cave.enter(Direction.NORTH, new Round(0)));
    } catch (IOException e) {
      fail("Valid enter should not have failed.");
    }
  }

  @Test
  public void testValidSetNode() {
    try {
      this.cave.setNode(this.east, Direction.EAST);
    } catch (Exception e) {
      fail("Valid setNode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetNode() {
    Node node = new StandardRoomNode(new Coordinates(0, 0));
    this.cave.setNode(node, Direction.NORTH);
    fail("Invalid setNode() should have failed.");
  }

  @Test
  public void testShootMiss() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testShootHitTunnel() {
    IHtwNode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy(), log);
    IHtwNode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy(), log);
    IHtwNode eastTwo = new Cave(12, new Coordinates(3, 1), new TunnelStrategy(), log);

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    assertTrue(this.cave.shoot(Direction.EAST, 1));
  }

  @Test
  public void testShootHitCaveMiss() {
    IHtwNode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy(), log);
    IHtwNode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy(), log);
    IHtwNode eastTwo = new Cave(12, new Coordinates(3, 1), new StandardStrategy(), log);

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    assertFalse(this.cave.shoot(Direction.EAST, 1));
    assertFalse(this.cave.shoot(Direction.EAST, 2));
  }

  @Test
  public void testShootHitCaveStrike() {
    IHtwNode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy(), log);
    IHtwNode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy(), log);
    IHtwNode eastTwo = new Cave(12, new Coordinates(3, 1), new StandardStrategy(), log);

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.EAST);
    wumpus.setNode(eastTwo, Direction.WEST);

    assertTrue(this.cave.shoot(Direction.EAST, 3));
  }

  @Test
  public void testSetStrategy() {
    assertFalse(this.cave.shoot(Direction.SOUTH, 1));
    IHtwNodeStrategy newStrat = new WumpusStrategy();
    ((IHtwNode) this.cave.getNode(Direction.SOUTH)).setStrategy(newStrat);
    assertTrue(this.cave.shoot(Direction.SOUTH, 1));
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1) - Standard", this.cave.toString());
  }

  @Test
  public void testSmelly() {
    assertFalse(this.cave.smelly(Direction.SOUTH));
  }

  @Test
  public void testDrafty() {
    assertFalse(this.cave.drafty(Direction.SOUTH));
  }

  @Test
  public void testGet() {
    IHtwNode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy(), log);
    IHtwNode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy(), log);
    IHtwNode eastTwo = new Cave(12, new Coordinates(3, 1), new StandardStrategy(), log);

    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    IHtwNode retrieved = this.cave.get(10);
    assertEquals(wumpus, retrieved);
  }

  @Test
  public void testReceive() {
    IHtwNode wumpus = new Cave(10, new Coordinates(3, 0), new WumpusStrategy(), log);
    IHtwNode eastOne = new Cave(11, new Coordinates(2, 1), new TunnelStrategy(), log);
    IHtwNode eastTwo = new Cave(12, new Coordinates(3, 1), new TunnelStrategy(), log);
    IHtwNode pit = new Cave(16, new Coordinates(1, 0), new PitStrategy(), log);
    this.cave.setNode(pit, Direction.WEST);
    pit.setNode(this.cave, Direction.EAST);
    this.cave.setNode(eastOne, Direction.EAST);
    eastOne.setNode(this.cave, Direction.WEST);
    eastTwo.setNode(eastOne, Direction.WEST);
    eastOne.setNode(eastTwo, Direction.EAST);
    eastTwo.setNode(wumpus, Direction.NORTH);
    wumpus.setNode(eastTwo, Direction.SOUTH);

    IHtwPlayer player = new HtwPlayer("Joe", 10);
    try {
      this.cave.receive(player);
      assertEquals("You feel a draft\nYou smell a Wumpus\n", log.toString());
    } catch (Exception e) {
      fail("Valid enter() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGet() {
    this.cave.get(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotFound() {
    this.cave.get(11);
  }

  @Test
  public void testDirectionTo() {
    assertEquals(Direction.NORTH, this.cave.directionTo(1));
    assertEquals(Direction.SOUTH, this.cave.directionTo(2));
    assertEquals(Direction.EAST, this.cave.directionTo(3));
    assertEquals(Direction.WEST, this.cave.directionTo(4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDirectionTo() {
    this.cave.setNode(new DeadEnd(), Direction.NORTH);
    this.cave.directionTo(1);
    fail("Invalid directionTo() should have failed.");
  }

  @Test
  public void testCanEnter() {
    assertTrue(this.cave.canEnter());
  }

  @Test
  public void testNeighbors() {
    Map<Direction, Integer> neighbors = this.cave.neighbors();
    assertEquals(this.north.getId(), neighbors.get(Direction.NORTH));
    assertEquals(this.south.getId(), neighbors.get(Direction.SOUTH));
    assertEquals(this.east.getId(), neighbors.get(Direction.EAST));
    assertEquals(this.west.getId(), neighbors.get(Direction.WEST));
  }
}
