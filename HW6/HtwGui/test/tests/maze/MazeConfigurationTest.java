package tests.maze;

import org.junit.Before;
import org.junit.Test;

import maze.components.Coordinates;
import maze.components.ICoordinates;
import maze.config.IConfiguration;
import maze.config.PerfectMazeConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test for the MazeConfiguration.
 */
public class MazeConfigurationTest {
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
      IConfiguration configuration = new PerfectMazeConfiguration(
              5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeRowCount() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            -5, 5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeColCount() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            5, -5, start, exit, 0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeGold() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            5,-5, start, exit, 0.1, 0.2, 0.3, -10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeThiefFrequency() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, -0.1, 0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeThiefPenalty() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, -0.2, 0.3, 10, true, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeGoldFrequency() {
    IConfiguration configuration = new PerfectMazeConfiguration(
            5, 5, start, exit, 0.1, 0.2, -0.3, 10, true, 1);
  }

  @Test
  public void testGetters() {
    IConfiguration configuration = new PerfectMazeConfiguration(
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
  }
}
