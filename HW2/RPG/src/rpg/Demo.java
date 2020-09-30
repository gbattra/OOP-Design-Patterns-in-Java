package rpg;

import rpg.enums.GearType;
import rpg.interfaces.IBattle;
import rpg.interfaces.IPlayer;
import rpg.models.Battle;
import rpg.models.PlayerBuilder;

/**
 * Driver class to demo the RPG package.
 */
public class Demo {
  /**
   * Entry point to driver.
   *
   * @param args should be empty, takes no args
   */
  public static void main(String[] args) {
    IPlayer playerOne = new PlayerBuilder(1, 10, 5).build();

    System.out.println(playerOne.toString() + "\n");

    IPlayer playerTwo = new PlayerBuilder(2, 5, 10).build();

    System.out.println(playerTwo.toString() + "\n");

    IBattle battle = new Battle(2, 10)
            .addPlayer(playerOne)
            .addPlayer(playerTwo);

    System.out.println("Dressing players...");

    System.out.println("Fight!" + "\n");

    IPlayer victor = battle.fight();

    System.out.println("And the winner is...\n");

    System.out.println(victor.toString() + "\n");
  }
}
