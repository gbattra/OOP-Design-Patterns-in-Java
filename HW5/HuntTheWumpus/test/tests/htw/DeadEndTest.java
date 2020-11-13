package tests.htw;

import org.junit.Test;

import htw.game.Player;
import htw.nodes.DeadEnd;
import htw.nodes.INode;
import htw.strategies.StandardStrategy;
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
    INode deadend = new DeadEnd();
    deadend.enter(Direction.NORTH);
    fail("Dead end node enter() should have failed.");
  }

  @Test
  public void testShoot() {
    INode deadend = new DeadEnd();
    assertFalse(deadend.shoot(Direction.SOUTH, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void testSetStrategy() {
    INode deadend = new DeadEnd();
    deadend.setStrategy(new StandardStrategy());
    fail("Dead end node setStrategy() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testReceive() {
    INode deadend = new DeadEnd();
    deadend.receive(new Player("Joe", 10));
    fail("Dead end node receive() should have failed.");
  }

  @Test
  public void testToString() {
    INode deadend = new DeadEnd();
    assertEquals("Dead End", deadend.toString());
  }

  @Test
  public void testSmelly() {
    INode deadend = new DeadEnd();
    assertFalse(deadend.smelly());
  }

  @Test
  public void testDrafty() {
    INode deadend = new DeadEnd();
    assertFalse(deadend.drafty());
  }
}
