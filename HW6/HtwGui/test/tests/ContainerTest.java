package tests;

import org.junit.Test;

import java.util.ArrayList;

import gui.Container;
import gui.GuiView;
import gui.IView;
import htw.IHtwMaze;
import htw.HtwConfigurationBuilder;
import htw.HtwMazeBuilder;

import static org.junit.Assert.fail;

/**
 * Tests for the Container.
 */
public class ContainerTest {
  @Test
  public void testValidConstructor() {
    try {
      IView view = new GuiView();
      IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(new HtwConfigurationBuilder().build()).build();
      Container container = new Container(
              view, new ArrayList<>(), maze, 1);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Container container = new Container(null, null, null, 0);
    fail("Invalid constructor should have failed.");
  }
}
