package tests;

import org.junit.Test;

import java.util.Arrays;

import gui.Container;
import gui.GuiView;
import gui.IView;
import htw.HtwMultiPlayer;
import htw.IHtwPlayer;
import htw.IHtwMaze;
import htw.HtwConfigurationBuilder;
import htw.HtwMazeBuilder;
import htw.IHtwConfiguration;
import maze.Coordinates;

import static org.junit.Assert.fail;

/**
 * Tests for MazeView.
 */
public class MazeViewTests {
  @Test
  public void testValidPopulate() {
    try {
      IView view = new GuiView();
      IHtwConfiguration config = new HtwConfigurationBuilder().build();
      IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(config).build();
      IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 10);
      Container container = new Container(view, Arrays.asList(player), maze, 1);
    } catch (Exception e) {
      fail("Valid populate should not have failed.");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidPopulateOOB() {
    IView view = new GuiView();
    IHtwConfiguration config = new HtwConfigurationBuilder().build();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(config).build();
    IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 10);
    player.setCurrentPosition(new Coordinates(1000, 1000));
    Container container = new Container(view, Arrays.asList(player), maze, 1);
  }
}
