import org.junit.Test;

import maze.interfaces.Node;
import maze.interfaces.Player;
import maze.models.GoldRoomNode;
import maze.models.MazeCoordinates;
import maze.models.MazePlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the MazePlayer.
 */
public class MazePlayerTest {
  @Test
  public void testValidConstructor() {
    try {
      Player player = new MazePlayer("Joe");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Player player = new MazePlayer("");
  }

  @Test
  public void testGetters() {
    Node node = new GoldRoomNode(new MazeCoordinates(0,0), 10);
    Player player = new MazePlayer("Joe");
    player = player.loot(node);
    assertEquals(10, player.getGold());
    assertEquals("Joe", player.getName());
  }
}
