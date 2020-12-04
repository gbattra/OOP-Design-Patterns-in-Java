package tests.maze;

import org.junit.Test;

import maze.components.Coordinates;
import maze.components.GoldRoomNode;
import maze.components.Node;
import maze.game.IMazePlayer;
import maze.game.MazePlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the MazePlayer.
 */
public class MazePlayerTest {
  @Test
  public void testValidConstructor() {
    try {
      IMazePlayer player = new MazePlayer("Joe");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IMazePlayer player = new MazePlayer("");
  }

  @Test
  public void testGetters() {
    Node node = new GoldRoomNode(new Coordinates(0,0), 10);
    IMazePlayer player = new MazePlayer("Joe");
    player = player.loot(node);
    assertEquals(10, player.getGold());
    assertEquals("Joe", player.getName());
  }
}
