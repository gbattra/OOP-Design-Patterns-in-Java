package tests.mazes.models;

import org.junit.Before;
import org.junit.Test;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Maze;
import maze.interfaces.MazeBuilder;
import maze.interfaces.Node;
import maze.models.GoldRoomNode;
import maze.models.Maze2dBuilder;
import maze.models.MazeCoordinates;
import maze.models.PerfectMazeConfiguration;
import maze.models.RoomMazeConfiguration;
import maze.models.StandardRoomNode;
import maze.models.ThiefRoomNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the Maze2dBuilder.
 */
public class Maze2dBuilderTest {
  private Configuration configuration;
  private Coordinates start;
  private Coordinates exit;

  @Before
  public void setup() {
    this.start = new MazeCoordinates(0,0);
    this.exit = new MazeCoordinates(4,4);

    this.configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test
  public void testAddVisited() {
    MazeBuilder builder = new Maze2dBuilder(this.configuration);
    Coordinates coordinates = new MazeCoordinates(0,0);
    Node node = new GoldRoomNode(coordinates, 10);
    builder.addVisited(node);
    assertEquals(node, builder.visited()[coordinates.getY()][coordinates.getX()]);
  }

  @Test
  public void testGenerateNode() {
    Configuration thiefConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 1, 0.0, 10, true, 1);
    MazeBuilder builder = new Maze2dBuilder(thiefConfig);
    Node thief = builder.generateRoom(new MazeCoordinates(0,0));
    assertTrue(thief instanceof ThiefRoomNode);

    Configuration goldConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 1, 10, true, 1);
    builder = new Maze2dBuilder(goldConfig);
    Node gold = builder.generateRoom(new MazeCoordinates(0,0));
    assertTrue(gold instanceof GoldRoomNode);

    Configuration standardConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 0, 0, true, 1);
    builder = new Maze2dBuilder(standardConfig);
    Node standard = builder.generateRoom(new MazeCoordinates(0,0));
    assertTrue(standard instanceof StandardRoomNode);
  }

  @Test
  public void testIsPerfect() {
    MazeBuilder builder = new Maze2dBuilder(this.configuration);
    Maze maze = builder.build();
    assertTrue(builder.isPerfect());
  }

  @Test
  public void testIsPerfectTeardownWalls() {
    int targetEdgeCount = 1;
    Configuration config = new RoomMazeConfiguration(
            5, 5, start, exit, 0.1, 0.1, 0.0, 10, false, targetEdgeCount, 1);
    MazeBuilder builder = new Maze2dBuilder(config);
    builder.build();
    assertFalse(builder.isPerfect());
    assertEquals(targetEdgeCount, builder.edges().size());
  }
}
