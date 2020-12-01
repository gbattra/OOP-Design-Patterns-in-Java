package tests.gui;

import org.junit.Test;

import gui.Container;
import gui.GuiController;
import gui.GuiView;
import gui.IGuiController;
import gui.IView;
import tests.gui.mocks.MockGuiView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContainerTest {
  @Test
  public void testValidConstructor() {
    IView view = new GuiView();
    Container container = new Container("Container", view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Container container = new Container("", null);
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testRestart() {
    StringBuilder log = new StringBuilder();
    IView mock = new MockGuiView(log);
    Container container = new Container("", mock);
    container.onRestart();
    assertEquals("false - false - 10 - 10 - 10 - false - 99 - 0.2 - 0.3", log.toString());
  }
}
