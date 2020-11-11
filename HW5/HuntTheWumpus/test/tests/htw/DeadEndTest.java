package tests.htw;

import org.junit.Test;

import htw.nodes.DeadEnd;
import htw.nodes.HTWNode;
import maze.utils.Direction;

import static org.junit.Assert.fail;

public class DeadEndTest {
  @Test(expected = IllegalStateException.class)
  public void testEnter() {
    HTWNode deadend = new DeadEnd();
    deadend.enter(Direction.NORTH);
    fail("Dead end node enter() should have failed.");
  }
}
