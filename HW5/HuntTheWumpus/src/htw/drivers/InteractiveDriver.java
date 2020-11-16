package htw.drivers;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.commands.factories.HtwCommandMapFactory;
import htw.game.commands.strategies.ActionByDirStrategy;
import htw.game.commands.strategies.ActionByIdStrategy;
import htw.game.commands.strategies.IActionStrategy;

public class InteractiveDriver {
  public static void main(String[] args) {
    System.out.print("Controller type ([dir], 'id'): ");
    String type = new Scanner(System.in).nextLine().split(" ")[0];
    IActionStrategy strategy = new ActionByDirStrategy();
    if (type.equalsIgnoreCase("id")) {
      strategy = new ActionByIdStrategy();
    }

    Runnable controller = new HtwController(
            new Scanner(System.in),
            System.out,
            strategy,
            new HtwCommandMapFactory());
    controller.run();
  }
}
