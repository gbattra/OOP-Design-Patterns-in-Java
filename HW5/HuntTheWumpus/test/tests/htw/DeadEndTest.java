package tests.htw;

import org.junit.Test;

import htw.DeadEnd;
import htw.MazeNode;
import maze.utils.Direction;

import static org.junit.Assert.fail;

public class DeadEndTest {
  @Test(expected = IllegalStateException.class)
  public void testEnter() {
    MazeNode deadend = new DeadEnd();
    deadend.enter(Direction.NORTH);
    fail("Dead end node enter() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testPromote() {
    MazeNode deadend = new DeadEnd();
    deadend.promote();
    fail("Dead end node promote() should have failed.");
  }
}
