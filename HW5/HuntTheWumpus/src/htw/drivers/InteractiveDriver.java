package htw.drivers;

import java.util.Scanner;

import htw.game.HtwController;
import htw.game.IController;
import htw.game.commands.strategies.ActionByDirStrategy;

public class InteractiveDriver {
  public static void main(String[] args) {
    IController controller =
            new HtwController(new Scanner(System.in), System.out, new ActionByDirStrategy());
    controller.run();
  }
}
