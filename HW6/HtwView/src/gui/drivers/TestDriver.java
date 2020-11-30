package gui.drivers;

import gui.GuiController;
import gui.GuiView;
import gui.IView;
import gui.IGuiController;

public class TestDriver {
  public static void main(String[] args) {
    IGuiController controller = new GuiController();
    IView view = new GuiView(controller);
  }
}
