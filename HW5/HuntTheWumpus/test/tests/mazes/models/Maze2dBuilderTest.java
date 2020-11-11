package tests.mazes.models;

import org.junit.Before;
import org.junit.Test;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.MazeBuilder;
import maze.interfaces.Node;
import maze.models.GoldRoomNode;
import maze.models.Maze2dBuilder;
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
}
