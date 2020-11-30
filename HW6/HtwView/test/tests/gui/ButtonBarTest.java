package tests.gui;

import org.junit.Test;

import gui.ButtonBar;
import gui.Container;
import gui.GuiController;
import gui.IView;
import gui.IGuiController;

import static org.junit.Assert.fail;

public class ButtonBarTest {
  @Test
  public void testValidConstructor() {
    IGuiController controller = new GuiController();
    Container container = new Container("Container", controller);
    ButtonBar buttonBar = new ButtonBar("ButtonBar", container);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    ButtonBar buttonBar = new ButtonBar("", null);
    fail("Invalid constructor should have failed.");
  }
}
