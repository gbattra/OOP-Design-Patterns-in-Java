import org.junit.Before;
import org.junit.Test;

import maze.enums.Direction;
import maze.interfaces.Builder;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Maze;
import maze.interfaces.Node;
import maze.interfaces.Path;
import maze.models.DeadEndNode;
import maze.models.GoldRoomNode;
import maze.models.Maze2dBuilder;
import maze.models.MazeCoordinates;
import maze.models.PerfectMazeConfiguration;
import maze.models.StandardRoomNode;
import maze.models.ThiefRoomNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RoomNodeTest {
  private Coordinates start;
  private Coordinates exit;

  @Before
  public void setup() {
    start = new MazeCoordinates(0,0);
    exit = new MazeCoordinates(4,4);
  }

  @Test
  public void testValidConstructor() {
    try {
      Node node = new StandardRoomNode(new MazeCoordinates(0, 0));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegGold() {
    Node node = new GoldRoomNode(new MazeCoordinates(0, 0), -10);
    fail("Invalid constructor should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegThiefPenalty() {
    Node node = new ThiefRoomNode(new MazeCoordinates(0, 0), -.1);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGetters() {
    Node node = new GoldRoomNode(new MazeCoordinates(0, 0), 10);
    assertEquals(10, node.getGoldCount());
    assertEquals(new MazeCoordinates(0, 0), node.getCoordinates());
    assertFalse(node.isThiefRoom());
    assertEquals(0, node.getThiefPenalty(), 0.0001);
    assertEquals(new DeadEndNode(), node.getNode(Direction.EAST));
    assertEquals(new DeadEndNode(), node.getNode(Direction.NORTH));
    assertEquals(new DeadEndNode(), node.getNode(Direction.SOUTH));
    assertEquals(new DeadEndNode(), node.getNode(Direction.WEST));
  }

  @Test
  public void testSetNode() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);
    assertEquals(east, node.getNode(Direction.EAST));
  }

  @Test
  public void testSimpleGet() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);
    Node gotten = node.get(east.getCoordinates());
    assertEquals(east, gotten);
  }

  @Test
  public void testComplexGet() {
    Configuration configuration = new PerfectMazeConfiguration(
            50, 50, start, exit, 0.1, 0.2, 0.3, 10, false, 1);
    Node start = new StandardRoomNode(new MazeCoordinates(0, 0));
    configuration = start.grow(configuration);
    Node gotten = start.get(new MazeCoordinates(49, 49));
    assertEquals(configuration.visited()[49][49], gotten);
  }

  @Test
  public void testSimpleCanReach() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);
    assertTrue(node.canReach(east.getCoordinates()));
    assertFalse(node.canReach(new MazeCoordinates(1, 0)));
  }

  @Test
  public void testComplexCanReach() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);

    Node south = new StandardRoomNode(new MazeCoordinates(1, 1));
    east.setNode(south, Direction.SOUTH);

    Node west = new StandardRoomNode(new MazeCoordinates(1, 0));
    south.setNode(west, Direction.WEST);

    assertTrue(node.canReach(west.getCoordinates()));
  }

  @Test
  public void testSimpleWealthiestPath() {
    Node node = new GoldRoomNode(new MazeCoordinates(0,0), 10);
    Node east = new ThiefRoomNode(new MazeCoordinates(0,1), 0.1);
    node.setNode(east, Direction.EAST);

    Node south = new GoldRoomNode(new MazeCoordinates(1, 1), 10);
    east.setNode(south, Direction.SOUTH);

    Node west = new ThiefRoomNode(new MazeCoordinates(1, 0), 0.1);
    south.setNode(west, Direction.WEST);

    Path wealthiestPath = node.wealthiestPathTo(west.getCoordinates());
    assertEquals(17, wealthiestPath.totalGold());
  }

  @Test
  public void testComplexWealthiestPath() {
    Node node = new GoldRoomNode(new MazeCoordinates(0,0), 10);
    Node east = new ThiefRoomNode(new MazeCoordinates(0,1), 0.1);
    node.setNode(east, Direction.EAST);

    Node south = new ThiefRoomNode(new MazeCoordinates(1, 1), 0.1);
    east.setNode(south, Direction.SOUTH);

    Node west = new GoldRoomNode(new MazeCoordinates(1, 0), 10);
    south.setNode(west, Direction.WEST);
    node.setNode(west, Direction.SOUTH);
    Path wealthiestPath = node.wealthiestPathTo(west.getCoordinates());

    assertEquals(20, wealthiestPath.totalGold());
    assertFalse(wealthiestPath.getCoordinatesTraversed().contains(south.getCoordinates()));
    assertFalse(wealthiestPath.getCoordinatesTraversed().contains(east.getCoordinates()));
  }

  @Test
  public void testSimpleExplore() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);
    Path path = node.exploreTo(east.getCoordinates());
    assertTrue(path.getCoordinatesTraversed().contains(east.getCoordinates()));
  }

  @Test
  public void testComplexExplore() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);

    Node south = new StandardRoomNode(new MazeCoordinates(1, 1));
    east.setNode(south, Direction.SOUTH);

    Node west = new StandardRoomNode(new MazeCoordinates(1, 0));
    south.setNode(west, Direction.WEST);
    node.setNode(west, Direction.SOUTH);

    Path path = node.exploreTo(west.getCoordinates());
    assertTrue(path.getCoordinatesTraversed().contains(node.getCoordinates()));
    assertTrue(path.getCoordinatesTraversed().contains(south.getCoordinates()));
    assertTrue(path.getCoordinatesTraversed().contains(east.getCoordinates()));
    assertTrue(path.getCoordinatesTraversed().contains(west.getCoordinates()));
  }

  @Test
  public void testValidPathTo() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node east = new StandardRoomNode(new MazeCoordinates(0,1));
    node.setNode(east, Direction.EAST);

    Node south = new StandardRoomNode(new MazeCoordinates(1, 1));
    east.setNode(south, Direction.SOUTH);

    Path path = node.pathTo(south.getCoordinates());
    assertTrue(path.reachesTarget());
  }

  @Test
  public void testValidGrowSmall() {
    try {
      Configuration configuration = new PerfectMazeConfiguration(
              2, 2, start, exit, 0.1, 0.2, 0.3, 10, false, 1);
      Node start = new StandardRoomNode(new MazeCoordinates(0, 0));
      configuration = start.grow(configuration);
      assertTrue(start.canReach(new MazeCoordinates(1,1)));
      assertTrue(configuration.isPerfect());
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testValidGrowMedium() {
    try {
      Configuration configuration = new PerfectMazeConfiguration(
              50, 50, start, exit, 0.1, 0.2, 0.3, 10, false, 1);
      Node start = new StandardRoomNode(new MazeCoordinates(0, 0));
      configuration = start.grow(configuration);
      assertTrue(start.canReach(new MazeCoordinates(9,9)));
      assertTrue(configuration.isPerfect());
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testValidGrowSmallWrapping() {
    try {
      Configuration configuration = new PerfectMazeConfiguration(
              2, 2, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
      Node start = new StandardRoomNode(new MazeCoordinates(0, 0));
      configuration = start.grow(configuration);
      assertTrue(start.canReach(new MazeCoordinates(1,1)));
      assertTrue(configuration.isPerfect());
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testValidGrowMediumWrapping() {
    try {
      Builder builder = new Maze2dBuilder()
              .setColumnCount(50)
              .setRowCount(50)
              .setStart(0,0)
              .setGoal(3,3)
              .setGoldFrequency(0.2)
              .setThiefFrequency(0.1)
              .setIsWrappingMaze(true)
              .setIsRoomMaze(true)
              .setTargetEdgeCount(35);
      Maze wrappingMaze = builder.build();
      Node start = wrappingMaze.getStart();
      assertTrue(start.canReach(new MazeCoordinates(3,3)));
      assertTrue(start.exploreTo(new MazeCoordinates(49,49)).reachesTarget());
      assertTrue(start.pathTo(new MazeCoordinates(49,49)).reachesTarget());
    } catch (Exception e) {
      fail("Valid grow should not have failed.");
    }
  }

  @Test
  public void testLoot() {
    Node node = new StandardRoomNode(new MazeCoordinates(0,0));
    Node thief = new ThiefRoomNode(new MazeCoordinates(0,0), 0.1);
    Node gold = new GoldRoomNode(new MazeCoordinates(0,0), 10);

    assertEquals(10, node.loot(10));
    assertEquals(9, thief.loot(10));
    assertEquals(20, gold.loot(10));
  }
}
