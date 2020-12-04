package tests.gui;

import org.junit.Test;

import java.util.ArrayList;

import gui.Container;
import gui.GuiView;
import gui.IView;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;

import static org.junit.Assert.fail;

public class ContainerTest {
  @Test
  public void testValidConstructor() {
    IView view = new GuiView();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(new HtwConfigurationBuilder().build()).build();
    Container container = new Container(
            view, new ArrayList<>(), maze, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Container container = new Container(null, null, null, 0);
    fail("Invalid constructor should have failed.");
  }
}
