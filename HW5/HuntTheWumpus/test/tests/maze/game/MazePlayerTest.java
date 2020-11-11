package tests.maze.game;

import org.junit.Test;

import maze.components.nodes.Node;
import maze.game.IMazePlayer;
import maze.components.nodes.GoldRoomNode;
import maze.components.MazeCoordinates;
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
    Node node = new GoldRoomNode(new MazeCoordinates(0,0), 10);
    IMazePlayer player = new MazePlayer("Joe");
    player = player.loot(node);
    assertEquals(10, player.getGold());
    assertEquals("Joe", player.getName());
  }
}
