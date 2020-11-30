package tests.gui;

import org.junit.Test;

import gui.Container;
import gui.GuiController;
import gui.IGuiController;

import static org.junit.Assert.fail;

public class ContainerTest {
  @Test
  public void testValidConstructor() {
    IGuiController controller = new GuiController();
    Container container = new Container("Container", controller);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Container container = new Container("", null);
    fail("Invalid constructor should have failed.");
  }
}
