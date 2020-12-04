package tests.gui;

import org.junit.Test;

import java.util.ArrayList;

import gui.ButtonBar;
import gui.Container;
import gui.GuiController;
import gui.GuiView;
import gui.IView;
import gui.IGuiController;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import tests.htw.mocks.MockMaze;

import static org.junit.Assert.fail;

public class ButtonBarTest {
  @Test
  public void testValidConstructor() {
    IView view = new GuiView();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(new HtwConfigurationBuilder().build()).build();
    Container container = new Container(
            view, new ArrayList<>(), maze, 1);
    ButtonBar buttonBar = new ButtonBar(container);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    ButtonBar buttonBar = new ButtonBar(null);
    fail("Invalid constructor should have failed.");
  }
}
