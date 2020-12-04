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

/**
 * Driver for the Hunt the Wumpus game.
 */
public class HuntTheWumpus {
  /**
   * Entrypoint for running the program.
   *
   * @param args pass in on execution (mode: --text / --gui)
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.print("Args missing: please provide either --text or --gui arguments.\n");
      System.exit(1);
    }

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
