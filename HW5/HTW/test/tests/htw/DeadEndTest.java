package tests.htw;

import org.junit.Test;

import java.io.IOException;

import htw.game.HtwPlayer;
import htw.level.DeadEnd;
import htw.level.IHtwNode;
import htw.level.StandardStrategy;
import maze.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Tests for the dead end.
 */
public class DeadEndTest {
  @Test(expected = IllegalStateException.class)
  public void testEnter() {
    try {
      IHtwNode deadend = new DeadEnd();
      deadend.enter(Direction.NORTH);
      fail("Dead end node enter() should have failed.");
    } catch (IOException e) {
      // do nothing
    }
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

  @Test
  public void testReceive() {
    try {
      IHtwNode deadend = new DeadEnd();
      deadend.receive(new HtwPlayer("Joe", 10));
      fail("Dead end node receive() should have failed.");
    } catch (Exception e) {
      // do nothing
    }
  }

  @Test
  public void testToString() {
    IHtwNode deadend = new DeadEnd();
    assertEquals("Dead End", deadend.toString());
  }

  @Test
  public void testSmelly() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.smelly(Direction.SOUTH));
  }

  @Test
  public void testDrafty() {
    IHtwNode deadend = new DeadEnd();
    assertFalse(deadend.drafty(Direction.SOUTH));
  }

  @Test
  public void testCanEnter() {
    assertFalse(new DeadEnd().canEnter());
  }

  @Test(expected = IllegalStateException.class)
  public void testNeighbors() {
    new DeadEnd().neighbors();
  }
}
