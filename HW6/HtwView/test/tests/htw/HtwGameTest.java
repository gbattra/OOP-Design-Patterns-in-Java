package tests.htw;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import htw.game.HtwGame;
import htw.game.HtwPlayer;
import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import maze.Direction;
import tests.htw.mocks.MockMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for HtwGame.
 */
public class HtwGameTest {
  private IHtwMaze maze;
  private IHtwPlayer player;
  private IHtwGame game;
  private StringBuilder log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.player = new HtwPlayer("Joe", 2);
    this.maze = new MockMaze(this.log);
    List<IHtwPlayer> players = new ArrayList<>(Collections.singletonList(player));
    this.game = new HtwGame(players, this.maze, this.log);
  }

  @Test
  public void testConstructor() {
    try {
      List<IHtwPlayer> players = new ArrayList<>(Collections.singletonList(player));
      IHtwGame game = new HtwGame(players, this.maze, this.log);
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
    assertEquals("moved - 1receive - Joe", this.log.toString());
  }

  @Test
  public void testMoveDir() {
    this.game.move(Direction.EAST);
    assertEquals("moved - EASTreceive - Joe", this.log.toString());
  }

  @Test
  public void testShootDir() {
    this.game.shoot(Direction.EAST, 1);
    assertEquals("shoot - EAST - 1Miss... You have 1 remaining arrows.", this.log.toString());
  }

  @Test
  public void testShootId() {
    this.game.shoot(12, 1);
    assertEquals("shoot - 12 - 1Miss... You have 1 remaining arrows.", this.log.toString());
  }

  @Test
  public void voidTestShootGameOver() {
    this.game.shoot(12, 1);
    this.game.shoot(12, 1);
    assertTrue(this.game.isOver());
  }

  @Test
  public void testNext() {
    assertEquals(0, (int) this.game.next());
    assertEquals(1, (int) this.game.next());
  }
}
