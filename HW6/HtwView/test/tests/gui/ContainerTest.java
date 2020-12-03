package tests.gui;

import org.junit.Test;

import java.util.ArrayList;

import gui.Container;
import gui.GuiController;
import gui.GuiView;
import gui.IGuiController;
import gui.IView;
import tests.gui.mocks.MockGuiView;
import tests.htw.mocks.MockMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContainerTest {
  @Test
  public void testValidConstructor() {
    IView view = new GuiView();
    Container container = new Container(view, new ArrayList<>(), new MockMaze(new StringBuilder()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Container container = new Container(null, null, null);
    fail("Invalid constructor should have failed.");
  }
}
