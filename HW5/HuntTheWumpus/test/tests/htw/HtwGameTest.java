package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.HtwGame;
import htw.game.IGame;
import htw.game.IPlayer;
import htw.game.Player;
import htw.level.IHtwMaze;
import maze.utils.Direction;
import tests.htw.mocks.MockMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HtwGameTest {
  private IHtwMaze maze;
  private IPlayer player;
  private IGame game;
  private StringBuilder log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.player = new Player("Joe", 10);
    this.maze = new MockMaze(this.log);
    this.game = new HtwGame(this.player, this.maze);
  }

  @Test
  public void testConstructor() {
    try {
      IGame game = new HtwGame(this.player, this.maze);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IGame game = new HtwGame(null, null);
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
  public void testShoot() {
    this.game.shoot(Direction.EAST, 1);
    assertEquals("shoot - EAST - 1", this.log.toString());
  }
}