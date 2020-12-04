package tests.maze;

import org.junit.Before;
import org.junit.Test;

import maze.IMazeBuilder;
import maze.MazeBuilder;
import maze.components.Coordinates;
import maze.components.GoldRoomNode;
import maze.components.ICoordinates;
import maze.components.IMaze;
import maze.components.Node;
import maze.components.StandardRoomNode;
import maze.components.ThiefRoomNode;
import maze.config.IConfiguration;
import maze.config.PerfectMazeConfiguration;
import maze.config.RoomMazeConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the Maze2dBuilder.
 */
public class Maze2dBuilderTest {
  private IConfiguration configuration;
  private ICoordinates start;
  private ICoordinates exit;

  @Before
  public void setup() {
    this.start = new Coordinates(0,0);
    this.exit = new Coordinates(4,4);

    this.configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test
  public void testAddVisited() {
    IMazeBuilder builder = new MazeBuilder(this.configuration);
    ICoordinates coordinates = new Coordinates(0,0);
    Node node = new GoldRoomNode(coordinates, 10);
    builder.addVisited(node);
    assertEquals(node, builder.visited()[coordinates.getY()][coordinates.getX()]);
  }

  @Test
  public void testGenerateNode() {
    IConfiguration thiefConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 1, 0.0, 10, true, 1);
    IMazeBuilder builder = new MazeBuilder(thiefConfig);
    Node thief = builder.generateRoom(new Coordinates(0,0));
    assertTrue(thief instanceof ThiefRoomNode);

    IConfiguration goldConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 1, 10, true, 1);
    builder = new MazeBuilder(goldConfig);
    Node gold = builder.generateRoom(new Coordinates(0,0));
    assertTrue(gold instanceof GoldRoomNode);

    IConfiguration standardConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 0, 0, true, 1);
    builder = new MazeBuilder(standardConfig);
    Node standard = builder.generateRoom(new Coordinates(0,0));
    assertTrue(standard instanceof StandardRoomNode);
  }

  @Test
  public void testIsPerfect() {
    IMazeBuilder builder = new MazeBuilder(this.configuration);
    IMaze maze = builder.build();
    assertTrue(builder.isPerfect());
  }

  @Test
  public void testIsPerfectTeardownWalls() {
    int targetEdgeCount = 1;
    IConfiguration config = new RoomMazeConfiguration(
            5, 5, start, exit, 0.1, 0.1, 0.0, 10, false, targetEdgeCount, 1);
    IMazeBuilder builder = new MazeBuilder(config);
    builder.build();
    assertFalse(builder.isPerfect());
    assertEquals(targetEdgeCount, builder.edges().size());
  }
}
