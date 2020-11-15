package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.HtwGame;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.game.HtwPlayer;
import htw.level.IHtwMaze;
import maze.utils.Direction;
import tests.htw.mocks.MockMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HtwGameTest {
  private IHtwMaze maze;
  private IHtwPlayer player;
  private IHtwGame game;
  private StringBuilder log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.player = new HtwPlayer("Joe", 10);
    this.maze = new MockMaze(this.log);
    this.game = new HtwGame(this.player, this.maze, this.log);
  }

  @Test
  public void testConstructor() {
    try {
      IHtwGame game = new HtwGame(this.player, this.maze, this.log);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwGame game = new HtwGame(null, null, null);
  }

  @Test
  public void testMoveId() {
    this.game.move(1);
    assertEquals("moved - 1 - Joe", this.log.toString());
  }

  @Test
  public void testMoveDir() {
    this.game.move(Direction.EAST);
    assertEquals("moved - EAST - Joe", this.log.toString());
  }

  @Test
  public void testShootDir() {
    this.game.shoot(Direction.EAST, 1);
    assertEquals("shoot - EAST - 1\nMiss...", this.log.toString());
  }
  @Test
  public void testShootId() {
    this.game.shoot(12, 1);
    assertEquals("shoot - 12 - 1\nMiss...", this.log.toString());
  }

}
