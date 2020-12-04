package tests.maze;

import org.junit.Before;
import org.junit.Test;

import maze.Direction;
import maze.MazeBuilder;
import maze.components.IMaze;
import maze.components.Path;
import maze.config.IConfiguration;
import maze.config.MazeConfigurationBuilder;
import maze.game.IMazeGame;
import maze.game.IMazePlayer;
import maze.game.MazeGame;
import maze.game.MazePlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the MazeGame.
 */
public class MazeGameTest {
  private IMazePlayer player;
  private IMaze maze;
  private IMazeGame game;

  @Before
  public void setup() {
    IConfiguration configuration = new MazeConfigurationBuilder()
            .setColumnCount(2)
            .setRowCount(2)
            .setStart(0,0)
            .setGoal(1,1)
            .setGoldFrequency(1)
            .setThiefFrequency(0)
            .setRandomSeed(1)
            .build();
    this.maze = new MazeBuilder(configuration).build();
    this.player = new MazePlayer("Joey");
    this.game = new MazeGame(this.player, this.maze);
  }

  @Test
  public void testGetters() {
    assertEquals(this.maze, this.game.getMaze());
    assertEquals(this.player, this.game.getPlayer());
    assertEquals(new Path(this.maze.getGoal().getCoordinates()), this.game.getPath());
    assertEquals(0, game.getScore());
  }

  @Test
  public void testMovePlayer() {
    assertTrue(this.game.move(Direction.SOUTH));
    assertFalse(this.game.move(Direction.WEST));
    assertEquals(10, game.getScore());
  }

  @Test
  public void testGameOver() {
    this.game.move(Direction.SOUTH);
    this.game.move(Direction.EAST);
    assertTrue(this.game.isOver());
  }
}
