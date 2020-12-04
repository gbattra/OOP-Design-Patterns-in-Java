package gui.drivers;

import gui.GuiController;
import gui.GuiView;
import gui.IGuiController;
import gui.IView;

public class HuntTheWumpus {
  public static void main(String[] args) {
//    String mode = args[0];
//    System.out.printf("%s", mode);
    IView view = new GuiView();
    IGuiController controller = new GuiController(view);
    view.setFeatures(controller);
    controller.startNew();
  }
}
