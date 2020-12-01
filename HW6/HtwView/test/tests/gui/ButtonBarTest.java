package tests.gui;

import org.junit.Test;

import gui.ButtonBar;
import gui.Container;
import gui.GuiController;
import gui.GuiView;
import gui.IView;
import gui.IGuiController;

import static org.junit.Assert.fail;

public class ButtonBarTest {
  @Test
  public void testValidConstructor() {
    IView view = new GuiView();
    Container container = new Container("Container", view);
    ButtonBar buttonBar = new ButtonBar("ButtonBar", container);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    ButtonBar buttonBar = new ButtonBar("", null);
    fail("Invalid constructor should have failed.");
  }
}
