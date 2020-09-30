package rpg.interfaces;

/**
 * Interface for an RPG player object.
 */
public interface IPlayer {
  /**
   * Getter for this player's number.
   *
   * @return int the player number
   */
  int getNumber();

  /**
   * Aggregates the attack values of player attack plus all its gears' attack and returns the
   * total.
   *
   * @return the aggregated attack value
   */
  int getAttack();

  /**
   * Aggregates the defense values of player defense plus all its gears' defense and returns the
   * total.
   *
   * @return the aggregated defense value
   */
  int getDefense();

  /**
   * Factory method which takes any IGear instance and adds it to the appropriate list. Returns
   * updated player instance.
   *
   * @param gear the IGear instance to add
   * @return the updated player with gear added
   * @throws IllegalStateException when player state does not permit adding the gear
   * @throws IllegalArgumentException when provided gear has invalid sub-interface of IGear
   */
  IPlayer addGear(IGear gear) throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a headgear item to this player.
   *
   * @param gear the gear to add
   * @return a new player instance with updated attire
   * @throws IllegalStateException when player has no more room or cannot combine any more gears
   */
  IPlayer addHeadGear(IHeadGear gear) throws IllegalStateException;

  /**
   * Adds a handgear item to this player.
   *
   * @param gear the gear to add
   * @return a new player instance with updated attire
   * @throws IllegalStateException when player has no more room or cannot combine any more gears
   */
  IPlayer addHandGear(IHandGear gear) throws IllegalStateException;

  /**
   * Adds a footgear item to this player.
   *
   * @param gear the gear to add
   * @return a new player instance with updated attire
   * @throws IllegalStateException when player has no more room or cannot combine any more gears
   */
  IPlayer addFootGear(IFootGear gear) throws IllegalStateException;
}
