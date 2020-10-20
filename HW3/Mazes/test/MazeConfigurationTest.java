import org.junit.Before;
import org.junit.Test;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Node;
import maze.models.GoldRoomNode;
import maze.models.MazeCoordinates;
import maze.models.MazeEdge;
import maze.models.PerfectMazeConfiguration;
import maze.models.RoomMazeConfiguration;
import maze.models.StandardRoomNode;
import maze.models.ThiefRoomNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MazeConfigurationTest {
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
      Configuration configuration = new PerfectMazeConfiguration(
              5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeRowCount() {
    Configuration configuration = new PerfectMazeConfiguration(
            -5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeColCount() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, -5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeGold() {
    Configuration configuration = new PerfectMazeConfiguration(
            5,-5, start, exit, 0.1, 0.2, 0.3, -10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeThiefFrequency() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, -0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeThiefPenalty() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, -0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeGoldFrequency() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, -0.3, 10, true, 1);
  }

  @Test
  public void testGetters() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
    assertEquals(5, configuration.rowCount());
    assertEquals(5, configuration.columnCount());
    assertEquals(0.1, configuration.thiefPenalty(), .0001);
    assertEquals(0.2, configuration.thiefFrequency(), .0001);
    assertEquals(0.3, configuration.goldFrequency(), .0001);
    assertEquals(10, configuration.goldAmount());
    assertTrue(configuration.isWrappingMaze());
    assertFalse(configuration.isRoomMaze());
    assertEquals(1, configuration.randomSeed());
    assertEquals(0, configuration.exitCount());
    assertFalse(configuration.isPerfect());
  }

  @Test
  public void testAddEdge() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
    configuration.addEdge(
            new MazeCoordinates(0,0),
            new MazeCoordinates(0,1),
            Direction.EAST, Direction.WEST);
    // check that no dupes
    configuration.addEdge(
            new MazeCoordinates(0,0),
            new MazeCoordinates(0,1),
            Direction.EAST,
            Direction.WEST);
    assertEquals(1, configuration.edges().size());
    assertEquals(
            new MazeEdge(
                    new MazeCoordinates(0,0),
                    new MazeCoordinates(0,1),
                    Direction.WEST, Direction.EAST),
            configuration.edges().get(0));
  }

  @Test
  public void testAddVisited() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
    Coordinates coordinates = new MazeCoordinates(0,0);
    Node node = new GoldRoomNode(coordinates, 10);
    configuration.addVisited(node);
    assertEquals(node, configuration.visited()[coordinates.getY()][coordinates.getX()]);
  }


  @Test
  public void testGenerateNode() {
    Configuration thiefConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 1, 0.0, 10, true, 1);
    Node thief = thiefConfig.generateRoom(new MazeCoordinates(0,0));
    assertTrue(thief instanceof ThiefRoomNode);

    Configuration goldConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 1, 10, true, 1);
    Node gold = goldConfig.generateRoom(new MazeCoordinates(0,0));
    assertTrue(gold instanceof GoldRoomNode);

    Configuration standardConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 0, 0, true, 1);
    Node standard = standardConfig.generateRoom(new MazeCoordinates(0,0));
    assertTrue(standard instanceof StandardRoomNode);
  }

  @Test
  public void testIsPerfect() {
    Configuration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 1, 0.0, 10, true, 1);
    Node start = configuration.generateStart();
    configuration = start.grow(configuration);
    assertTrue(configuration.isPerfect());
  }

  @Test
  public void testIsPerfectKruskals() {
    int targetEdgeCount = 1;
    Configuration configuration = new RoomMazeConfiguration(
            3, 2, start, exit, 0.1, 0.1, 0.0, 10, false, targetEdgeCount, 1);
    configuration = configuration.growMaze();
    assertFalse(configuration.isPerfect());
    assertEquals(targetEdgeCount, configuration.edges().size());
  }
}
