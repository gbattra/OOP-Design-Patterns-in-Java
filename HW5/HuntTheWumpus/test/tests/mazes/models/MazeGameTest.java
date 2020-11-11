package tests.mazes.models;

import org.junit.Before;
import org.junit.Test;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.ConfigurationBuilder;
import maze.interfaces.MazeBuilder;
import maze.interfaces.Game;
import maze.interfaces.Maze;
import maze.interfaces.Player;
import maze.models.Maze2dBuilder;
import maze.models.MazeConfigurationBuilder;
import maze.models.MazeGame;
import maze.models.MazePath;
import maze.models.MazePlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the MazeGame.
 */
public class MazeGameTest {
  private Player player;
  private Maze maze;
  private Game game;

  @Before
  public void setup() {
    Configuration configuration = new MazeConfigurationBuilder()
            .setColumnCount(2)
            .setRowCount(2)
            .setStart(0,0)
            .setGoal(1,1)
            .setGoldFrequency(1)
            .setThiefFrequency(0)
            .setRandomSeed(1)
            .build();
    this.maze = new Maze2dBuilder(configuration).build();
    this.player = new MazePlayer("Joey");
    this.game = new MazeGame(this.player, this.maze);
  }

  @Test
  public void testGetters() {
    assertEquals(this.maze, this.game.getMaze());
    assertEquals(this.player, this.game.getPlayer());
    assertEquals(new MazePath(this.maze.getGoal().getCoordinates()), this.game.getPath());
    assertEquals(0, game.getScore());
  }

  @Test
  public void testMovePlayer() {
    assertTrue(this.game.movePlayer(Direction.SOUTH));
    assertFalse(this.game.movePlayer(Direction.WEST));
    assertEquals(10, game.getScore());
  }

  @Test
  public void testGameOver() {
    this.game.movePlayer(Direction.SOUTH);
    this.game.movePlayer(Direction.EAST);
    assertTrue(this.game.isOver());
  }
}
