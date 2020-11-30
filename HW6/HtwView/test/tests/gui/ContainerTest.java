package tests.gui;

import org.junit.Test;

import gui.Container;
import gui.GuiController;
import gui.IContainer;
import gui.IGuiController;

public class ContainerTest {
  @Test
  public void testValidConstructor() {
    IGuiController controller = new GuiController();
    IContainer container = new Container("Container", controller);
  }
}
