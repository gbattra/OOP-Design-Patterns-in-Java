package tests.htw;

import org.junit.Test;

import htw.nodes.Cave;
import htw.nodes.INode;
import htw.strategies.StandardStrategy;
import htw.strategies.TunnelStrategy;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwConfigurationBuilder;
import maze.IMazeBuilder;
import maze.MazeBuilder;
import maze.components.Coordinates;
import maze.components.IMaze;
import maze.utils.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the maze builder.
 */
public class HtwMazeBuilderTest {
  private IHtwConfigurationBuilder configBuilder = (IHtwConfigurationBuilder)
          new HtwConfigurationBuilder()
                  .setBatFrequency(0.2)
                  .setPitFrequency(0.3)
                  .setRowCount(5)
                  .setColumnCount(5)
                  .setStart(0, 0);
  @Test
  public void testConstructor() {
    try {
      IMazeBuilder builder = new HtwMazeBuilder(configBuilder.build());
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IMazeBuilder builder = new MazeBuilder(null);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGenerateRoom() {
    Coordinates coordinates = new Coordinates(1, 1);
    IMazeBuilder builder = new MazeBuilder(this.configBuilder.build());
    assertEquals(new Cave(coordinates, new TunnelStrategy()), builder.generateRoom(coordinates));
  }
}
