package tests.maze.components.nodes;

import org.junit.Before;
import org.junit.Test;

import maze.MazeBuilder;
import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.components.IMaze;
import maze.components.IPath;
import maze.components.nodes.DeadEndNode;
import maze.components.nodes.GoldRoomNode;
import maze.components.nodes.Node;
import maze.components.nodes.StandardRoomNode;
import maze.components.nodes.ThiefRoomNode;
import maze.config.IConfiguration;
import maze.config.MazeConfigurationBuilder;
import maze.config.PerfectMazeConfiguration;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test for the room node.
 */
public class RoomNodeTest {
  private ICoordinates start;
  private ICoordinates exit;

  @Before
  public void setup() {
    start = new Coordinates(0,0);
    exit = new Coordinates(4,4);
  }

  @Test
  public void testValidConstructor() {
    try {
      Node node = new StandardRoomNode(new Coordinates(0, 0));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegGold() {
    Node node = new GoldRoomNode(new Coordinates(0, 0), -10);
    fail("Invalid constructor should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegThiefPenalty() {
    Node node = new ThiefRoomNode(new Coordinates(0, 0), -.1);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGetters() {
    Node node = new GoldRoomNode(new Coordinates(0, 0), 10);
    assertEquals(10, node.getGoldCount());
    assertEquals(new Coordinates(0, 0), node.getCoordinates());
    assertEquals(0, node.getThiefPenalty(), 0.0001);
    assertEquals(new DeadEndNode(), node.getNode(Direction.EAST));
    assertEquals(new DeadEndNode(), node.getNode(Direction.NORTH));
    assertEquals(new DeadEndNode(), node.getNode(Direction.SOUTH));
    assertEquals(new DeadEndNode(), node.getNode(Direction.WEST));
  }

  @Test
  public void testSetNode() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);
    assertEquals(east, node.getNode(Direction.EAST));
  }

  @Test
  public void testSimpleGet() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);
    Node gotten = node.get(east.getCoordinates());
    assertEquals(east, gotten);
  }

  @Test
  public void testComplexGet() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            50, 50, start, exit, 0.1, 0.2, 0.3, 10, false, 1);
    IMaze maze = new MazeBuilder(configuration).build();
    ICoordinates coordinates = new Coordinates(49, 49);
    Node gotten = maze.get(coordinates);
    assertEquals(coordinates, gotten.getCoordinates());
  }

  @Test
  public void testSimpleCanReach() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);
    assertTrue(node.canReach(east.getCoordinates()));
    assertFalse(node.canReach(new Coordinates(1, 0)));
  }

  @Test
  public void testComplexCanReach() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);

    Node south = new StandardRoomNode(new Coordinates(1, 1));
    east.setNode(south, Direction.SOUTH);

    Node west = new StandardRoomNode(new Coordinates(1, 0));
    south.setNode(west, Direction.WEST);

    assertTrue(node.canReach(west.getCoordinates()));
  }

  @Test
  public void testSimpleWealthiestPath() {
    Node node = new GoldRoomNode(new Coordinates(0,0), 10);
    Node east = new ThiefRoomNode(new Coordinates(0,1), 0.1);
    node.setNode(east, Direction.EAST);

    Node south = new GoldRoomNode(new Coordinates(1, 1), 10);
    east.setNode(south, Direction.SOUTH);

    Node west = new ThiefRoomNode(new Coordinates(1, 0), 0.1);
    south.setNode(west, Direction.WEST);

    IPath wealthiestPath = node.wealthiestPathTo(west.getCoordinates());
    assertEquals(17, wealthiestPath.totalGold());
  }

  @Test
  public void testComplexWealthiestPath() {
    Node node = new GoldRoomNode(new Coordinates(0,0), 10);
    Node east = new ThiefRoomNode(new Coordinates(0,1), 0.1);
    node.setNode(east, Direction.EAST);

    Node south = new ThiefRoomNode(new Coordinates(1, 1), 0.1);
    east.setNode(south, Direction.SOUTH);

    Node west = new GoldRoomNode(new Coordinates(1, 0), 10);
    south.setNode(west, Direction.WEST);
    node.setNode(west, Direction.SOUTH);
    IPath wealthiestPath = node.wealthiestPathTo(west.getCoordinates());

    assertEquals(20, wealthiestPath.totalGold());
    assertFalse(wealthiestPath.getCoordinatesTraversed().contains(south.getCoordinates()));
    assertFalse(wealthiestPath.getCoordinatesTraversed().contains(east.getCoordinates()));
  }

  @Test
  public void testSimpleExplore() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);
    IPath path = node.exploreTo(east.getCoordinates());
    assertTrue(path.getCoordinatesTraversed().contains(east.getCoordinates()));
  }

  @Test
  public void testComplexExplore() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);

    Node south = new StandardRoomNode(new Coordinates(1, 1));
    east.setNode(south, Direction.SOUTH);

    Node west = new StandardRoomNode(new Coordinates(1, 0));
    south.setNode(west, Direction.WEST);
    node.setNode(west, Direction.SOUTH);

    IPath path = node.exploreTo(west.getCoordinates());
    assertTrue(path.getCoordinatesTraversed().contains(node.getCoordinates()));
    assertTrue(path.getCoordinatesTraversed().contains(south.getCoordinates()));
    assertTrue(path.getCoordinatesTraversed().contains(east.getCoordinates()));
    assertTrue(path.getCoordinatesTraversed().contains(west.getCoordinates()));
  }

  @Test
  public void testValidPathTo() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node east = new StandardRoomNode(new Coordinates(0,1));
    node.setNode(east, Direction.EAST);

    Node south = new StandardRoomNode(new Coordinates(1, 1));
    east.setNode(south, Direction.SOUTH);

    IPath path = node.pathTo(south.getCoordinates());
    assertTrue(path.reachesTarget());
  }

  @Test
  public void testValidGrowSmall() {
    try {
      IConfiguration configuration = new PerfectMazeConfiguration(
              5, 5, start, exit, 0.1, 0.2, 0.3, 10, false, 1);
      IMaze maze = new MazeBuilder(configuration).build();
      assertTrue(maze.canReach(new Coordinates(1,1)));
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testValidGrowMedium() {
    try {
      IConfiguration configuration = new PerfectMazeConfiguration(
              50, 50, start, exit, 0.1, 0.2, 0.3, 10, false, 1);
      IMaze maze = new MazeBuilder(configuration).build();
      assertTrue(maze.canReach(new Coordinates(9,9)));
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testValidGrowSmallWrapping() {
    try {
      IConfiguration configuration = new PerfectMazeConfiguration(
              5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
      IMaze maze = new MazeBuilder(configuration).build();
      assertTrue(maze.canReach(new Coordinates(1,1)));
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testLoot() {
    Node node = new StandardRoomNode(new Coordinates(0,0));
    Node thief = new ThiefRoomNode(new Coordinates(0,0), 0.1);
    Node gold = new GoldRoomNode(new Coordinates(0,0), 10);

    assertEquals(10, node.loot(10));
    assertEquals(9, thief.loot(10));
    assertEquals(20, gold.loot(10));
  }
}
