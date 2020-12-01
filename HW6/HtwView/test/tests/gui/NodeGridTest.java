package tests.gui;

import org.junit.Test;

import gui.HtwMultiPlayer;
import gui.NodeGrid;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import htw.tools.IHtwConfiguration;
import maze.components.Coordinates;

import static org.junit.Assert.fail;

public class NodeGridTest {
  @Test
  public void testValidPopulate() {
    try {
      IHtwConfiguration config = new HtwConfigurationBuilder().build();
      IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(config).build();
      NodeGrid nodeGrid = new NodeGrid();
      IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 10);
      maze.receive(nodeGrid);
      player.receive(nodeGrid);
    } catch (Exception e) {
      fail("Valid populate should not have failed.");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidPopulate() {
    NodeGrid nodeGrid = new NodeGrid();
    IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 10);
    player.receive(nodeGrid);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidPopulateOOB() {
    IHtwConfiguration config = new HtwConfigurationBuilder().build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(config).build();
    NodeGrid nodeGrid = new NodeGrid();
    IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 10);
    player.setCurrentPosition(new Coordinates(1000, 1000));
    maze.receive(nodeGrid);
    player.receive(nodeGrid);
  }
}
