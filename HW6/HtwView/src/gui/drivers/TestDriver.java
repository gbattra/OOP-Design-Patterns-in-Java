package gui.drivers;

import gui.Container;
import gui.GuiController;
import gui.IContainer;
import gui.IGuiController;

public class TestDriver {
  public static void main(String[] args) {
    IGuiController controller = new GuiController();
    IContainer container = new Container("Container", controller);
  }
}
