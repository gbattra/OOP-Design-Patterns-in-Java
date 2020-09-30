package rpg.interfaces;

import rpg.enums.GearType;

/**
 * Interface for an IPlayer builder object.
 */
public interface IPlayerBuilder {
  /**
   * Adds a headgear item to the player being built.
   *
   * @param type GearType enum for the gear
   * @param defense defensive power of the gear
   * @param adjective adjective describing the gear
   * @param noun noun describing the gear
   * @return the updated IPlayerBuilder instance
   * @throws IllegalStateException when gear constructor inputs are invalid
   * @throws IllegalArgumentException when player state does not permit this action
   */
  IPlayerBuilder addHeadGear(
          GearType type,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a handgear item to the player being built.
   *
   * @param type GearType enum for the gear
   * @param attack attack power of the gear
   * @param defense defensive power of the gear
   * @param adjective adjective describing the gear
   * @param noun noun describing the gear
   * @return the updated IPlayerBuilder instance
   * @throws IllegalStateException when gear constructor inputs are invalid
   * @throws IllegalArgumentException when player state does not permit this action
   */
  IPlayerBuilder addHandGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a footgear item to the player being built.
   *
   * @param type GearType enum for the gear
   * @param attack attack power of the gear
   * @param defense defensive power of the gear
   * @param adjective adjective describing the gear
   * @param noun noun describing the gear
   * @return the updated IPlayerBuilder instance
   * @throws IllegalStateException when gear constructor inputs are invalid
   * @throws IllegalArgumentException when player state does not permit this action
   */
  IPlayerBuilder addFootGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException;

  /**
   * Builds the player object and returns it.
   *
   * @return the built player object
   */
  IPlayer build();
}
