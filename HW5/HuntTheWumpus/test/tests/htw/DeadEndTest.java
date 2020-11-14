package tests.htw;

import org.junit.Test;

import htw.game.HtwPlayer;
import htw.level.nodes.DeadEnd;
import htw.level.nodes.IHtwNode;
import htw.level.strategies.StandardStrategy;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Tests for the dead end.
 */
public class DeadEndTest {
  @Test(expected = IllegalStateException.class)
  public void testEnter() {
    IHtwNode deadend = new DeadEnd();
    deadend.enter(Direction.NORTH);
    fail("Dead end node enter() should have failed.");
  }

  @Test
  public void testShoot() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.shoot(Direction.SOUTH, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void testSetStrategy() {
    IHtwNode deadend = new DeadEnd();
    deadend.setStrategy(new StandardStrategy());
    fail("Dead end node setStrategy() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testReceive() {
    IHtwNode deadend = new DeadEnd();
    deadend.receive(new HtwPlayer("Joe", 10));
    fail("Dead end node receive() should have failed.");
  }

  @Test
  public void testToString() {
    IHtwNode deadend = new DeadEnd();
    assertEquals("Dead End", deadend.toString());
  }

  @Test
  public void testSmelly() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.smelly());
  }

  @Test
  public void testDrafty() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.drafty());
  }

  @Test
  public void testCanEnter() {
    assertFalse(new DeadEnd().canEnter());
  }
}
