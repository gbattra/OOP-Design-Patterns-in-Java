package htw.drivers;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.commands.factories.HtwCommandMapFactory;
import htw.game.commands.strategies.DirActionStrategy;
import htw.game.commands.strategies.IdActionStrategy;
import htw.game.commands.strategies.IActionStrategy;

public class InteractiveDriver {
  public static void main(String[] args) {
    System.out.print("Controller mode ([dir], 'id'): ");
    String type = new Scanner(System.in).nextLine().split(" ")[0];
    IActionStrategy strategy = new DirActionStrategy();
    if (type.equalsIgnoreCase("id")) {
      strategy = new IdActionStrategy();
    }

    Runnable controller = new HtwController(
            new Scanner(System.in),
            System.out,
            strategy,
            new HtwCommandMapFactory());
    controller.run();
  }
}
