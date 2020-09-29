package rpg.models;

import rpg.enums.GearType;
import rpg.interfaces.IFootGear;
import rpg.interfaces.IHandGear;
import rpg.interfaces.IHeadGear;
import rpg.interfaces.IPlayer;
import rpg.interfaces.IPlayerBuilder;

/**
 * Class for building an IPlayer instance.
 */
public class PlayerBuilder implements IPlayerBuilder  {
  private IPlayer player;

  /**
   * Constructor for PlayerBuilder.
   *
   * @param number the number of the player
   * @param attack the attack power of the player
   * @param defense the defense power of the player
   * @throws IllegalArgumentException if any provided argument is negative
   */
  public PlayerBuilder(
          int number,
          int attack,
          int defense) throws IllegalArgumentException {
    if (number < 0 || attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Number, attack and defense must all be non-negative.");
    }

    this.player = new Player(number, attack, defense);
  }

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
  public IPlayerBuilder addHeadGear(
          GearType type,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException {
    try {
      IHeadGear gear = new HeadGear(type, defense, adjective, noun);
      this.player = this.player.addHeadGear(gear);
      return this;
    } catch (Exception e) {
      throw e;
    }
  }

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
  public IPlayerBuilder addHandGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException {
    try {
      IHandGear gear = new HandGear(type, attack, defense, adjective, noun);
      this.player = this.player.addHandGear(gear);
      return this;
    } catch (Exception e) {
      throw e;
    }
  }

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
  public IPlayerBuilder addFootGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException {
    try {
      IFootGear gear = new FootGear(type, attack, defense, adjective, noun);
      this.player = this.player.addFootGear(gear);
      return this;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Builds the player object and returns it.
   *
   * @return the built player object
   */
  public IPlayer build() {
    return this.player;
  }
}
