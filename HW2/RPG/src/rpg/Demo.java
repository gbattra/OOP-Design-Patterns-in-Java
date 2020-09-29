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
    IPlayer playerOne = new PlayerBuilder(1, 10, 5)
            .addHeadGear(GearType.VISOR, 10, "strong", "visor")
            .addHeadGear(GearType.VISOR, 5, "weak", "visor")
            .addHandGear(GearType.SWORD, 20, 5, "steel", "sword")
            .addHandGear(GearType.SHIELD, 0, 15, "wooden", "shield")
            .addHandGear(GearType.SWORD, 10, 5, "long", "sword")
            .addFootGear(GearType.BOOT, 5, 10, "steel", "boot")
            .addFootGear(GearType.BOOT, 5, 10, "steel", "boot")
            .build();

    System.out.println(playerOne.toString() + "\n");

    IPlayer playerTwo = new PlayerBuilder(2, 5, 10)
            .addHeadGear(GearType.HAT, 3, "baseball", "hat")
            .addHandGear(GearType.SHIELD, 0, 20, "steel", "shield")
            .addHandGear(GearType.GLOVE, 0, 5, "armored", "glove")
            .addFootGear(GearType.HOVERBOARD, 20, 5, "electric", "hoverboard")
            .build();

    System.out.println(playerTwo.toString() + "\n");

    IBattle battle = new Battle(2).addPlayer(playerOne).addPlayer(playerTwo);

    System.out.println("Fight!" + "\n");

    IPlayer victor = battle.fight();

    System.out.println("And the winner is...\n");

    System.out.println(victor.toString() + "\n");
  }
}
