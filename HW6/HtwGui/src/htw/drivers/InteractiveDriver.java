package htw.drivers;

import java.util.Scanner;

import htw.game.ConsoleController;
import htw.game.HtwCommandMapFactory;
import htw.game.commands.DirActionStrategy;
import htw.game.commands.IActionStrategy;
import htw.game.commands.IdActionStrategy;

/**
 * Interactive demo driver.
 */
public class InteractiveDriver {
  /**
   * Main driver method.
   *
   * @param args args for running the program
   */
  public static void main(String[] args) {
    System.out.print("Controller mode ([dir], 'id'): ");
    String type = new Scanner(System.in).nextLine().split(" ")[0];
    IActionStrategy strategy = new DirActionStrategy();
    if (type.equalsIgnoreCase("id")) {
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
