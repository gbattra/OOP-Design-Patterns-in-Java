package gui.drivers;

import java.util.Scanner;

import gui.GuiController;
import gui.GuiView;
import gui.IGuiController;
import gui.IView;
import htw.game.ConsoleController;
import htw.game.HtwCommandMapFactory;
import htw.game.commands.DirActionStrategy;
import htw.game.commands.IActionStrategy;
import htw.game.commands.IdActionStrategy;

public class HuntTheWumpus {
  public static void main(String[] args) {
    String mode = args[0];
    if (mode.equals("--gui")) {
      IView view = new GuiView();
      IGuiController controller = new GuiController(view);
      view.setFeatures(controller);
      controller.startNew();
    } else if (mode.equals("--text")) {
      IActionStrategy strategy = new DirActionStrategy();
      if (args.length > 1 && args[1].equalsIgnoreCase("--id")) {
        strategy = new IdActionStrategy();
      }

      Runnable controller = new ConsoleController(
              new Scanner(System.in),
              System.out,
              strategy,
              new HtwCommandMapFactory());
      controller.run();
    }
  }
}
