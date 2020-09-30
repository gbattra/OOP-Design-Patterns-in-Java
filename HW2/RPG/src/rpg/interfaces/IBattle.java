package rpg.interfaces;

import java.util.List;

import rpg.enums.GearType;

/**
 * Interface for a battle object.
 */
public interface IBattle {
  /**
   * Get the players in the battle.
   *
   * @return the list of players
   */
  List<IPlayer> getPlayers();

  /**
   * Returns the list of gear items set on this battle instance.
   *
   * @return the list of IGear instances
   */
  List<IGear> getGears();

  /**
   * Adds a gear to the battle's gear list. Will be used to dress players before
   * the fight.
   *
   * @param type GearType enum for the gear
   * @param attack attack power of the gear
   * @param defense defensive power of the gear
   * @param adjective adjective describing the gear
   * @param noun noun describing the gear
   * @return a new updated IBattle instance with new gear list
   * @throws IllegalStateException when the max number of gears has already been reached
   * @throws IllegalArgumentException when attack, def, adj or noun are negative or empty
   */
  IBattle addGear(GearType type,
                  int attack,
                  int defense,
                  String adjective,
                  String noun) throws IllegalStateException, IllegalArgumentException;

  /**
   * Fight the players against one another and return the victor.
   *
   * @return the winning player
   */
  IPlayer fight() throws IllegalStateException;

  /**
   * Add a player to the battle.
   *
   * @return new battle instance with updated player list
   */
  IBattle addPlayer(IPlayer player) throws IllegalStateException;

  /**
   * Dresses players from the list of gears provided to give each the best shot at winning.
   *
   * @return a new IBattle instance with dressed players
   */
  IBattle dressPlayers();
}
