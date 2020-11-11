package tests.maze.builders;

import org.junit.Before;
import org.junit.Test;

import maze.config.IConfiguration;
import maze.components.ICoordinates;
import maze.components.IMaze;
import maze.builders.IMazeBuilder;
import maze.components.nodes.Node;
import maze.components.nodes.GoldRoomNode;
import maze.builders.Maze2dBuilder;
import maze.components.MazeCoordinates;
import maze.config.PerfectMazeConfiguration;
import maze.config.RoomMazeConfiguration;
import maze.components.nodes.StandardRoomNode;
import maze.components.nodes.ThiefRoomNode;

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
    this.start = new MazeCoordinates(0,0);
    this.exit = new MazeCoordinates(4,4);

    this.configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test
  public void testAddVisited() {
    IMazeBuilder builder = new Maze2dBuilder(this.configuration);
    ICoordinates coordinates = new MazeCoordinates(0,0);
    Node node = new GoldRoomNode(coordinates, 10);
    builder.addVisited(node);
    assertEquals(node, builder.visited()[coordinates.getY()][coordinates.getX()]);
  }

  @Test
  public void testGenerateNode() {
    IConfiguration thiefConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 1, 0.0, 10, true, 1);
    IMazeBuilder builder = new Maze2dBuilder(thiefConfig);
    Node thief = builder.generateRoom(new MazeCoordinates(0,0));
    assertTrue(thief instanceof ThiefRoomNode);

    IConfiguration goldConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 1, 10, true, 1);
    builder = new Maze2dBuilder(goldConfig);
    Node gold = builder.generateRoom(new MazeCoordinates(0,0));
    assertTrue(gold instanceof GoldRoomNode);

    IConfiguration standardConfig = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.0, 0, 0, true, 1);
    builder = new Maze2dBuilder(standardConfig);
    Node standard = builder.generateRoom(new MazeCoordinates(0,0));
    assertTrue(standard instanceof StandardRoomNode);
  }

  @Test
  public void testIsPerfect() {
    IMazeBuilder builder = new Maze2dBuilder(this.configuration);
    IMaze maze = builder.build();
    assertTrue(builder.isPerfect());
  }

  @Test
  public void testIsPerfectTeardownWalls() {
    int targetEdgeCount = 1;
    IConfiguration config = new RoomMazeConfiguration(
            5, 5, start, exit, 0.1, 0.1, 0.0, 10, false, targetEdgeCount, 1);
    IMazeBuilder builder = new Maze2dBuilder(config);
    builder.build();
    assertFalse(builder.isPerfect());
    assertEquals(targetEdgeCount, builder.edges().size());
  }
}
