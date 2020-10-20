package rpg;

import rpg.enums.GearType;
import rpg.interfaces.IBattle;
import rpg.interfaces.IGear;
import rpg.interfaces.IPlayer;
import rpg.models.Battle;
import rpg.models.PlayerBuilder;

/**
 * Driver class to demo the RPG package. Sets up a battle, adds two players and adds gear
 * to the battlefield. Then dresses the players and fights them, declaring a victor.
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

    System.out.println("Adding gear to the battlefield...");

    battle = battle.addGear(GearType.HELMET, 0, 10, "steel", "helmet")
            .addGear(GearType.HELMET, 0, 5, "weak", "helmet")
            .addGear(GearType.HELMET, 0, 7, "kevlar", "hat")
            .addGear(GearType.SWORD, 15, 5, "steel", "sword")
            .addGear(GearType.SHIELD, 10, 15, "steel", "shield")
            .addGear(GearType.SHIELD, 10, 15, "steel", "shield")
            .addGear(GearType.GLOVE, 0, 3, "soft", "glove")
            .addGear(GearType.SHIELD, 2, 10, "light", "shield")
            .addGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
            .addGear(GearType.BOOT, 7, 5, "steel-toed", "boot");

    for (IGear gear : battle.getGears()) {
      System.out.println(gear.toString() + "\n");
    }

    System.out.println("Dressing players...");

    battle = battle.dressPlayers();

    System.out.println(battle.getPlayers().get(0).toString() + "\n");
    System.out.println(battle.getPlayers().get(1).toString() + "\n");

    System.out.println("Fight!" + "\n");

    IPlayer victor = battle.fight();

    System.out.println("And the winner is...\n");

    System.out.println(victor.toString() + "\n");
  }
}
