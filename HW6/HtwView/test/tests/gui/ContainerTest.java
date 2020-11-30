package tests.gui;

import org.junit.Test;

import gui.Container;
import gui.GuiController;
import gui.GuiView;
import gui.IGuiController;
import gui.IView;

import static org.junit.Assert.fail;

public class ContainerTest {
  @Test
  public void testValidConstructor() {
    IGuiController controller = new GuiController();
    IView view = new GuiView(controller);
    Container container = new Container("Container", view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Container container = new Container("", null);
    fail("Invalid constructor should have failed.");
  }
}
