package rpg.models;

import rpg.enums.GearType;
import rpg.interfaces.IGear;
import rpg.interfaces.IPlayer;
import rpg.interfaces.IPlayerBuilder;

/**
 * Class for building an IPlayer instance. Initialized with player number, base attack and defense.
 * Adds gear to the player when possible, otherwise throws.
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
   * Adds a gear item to the player being built.
   *
   * @param type GearType enum for the gear
   * @param defense defensive power of the gear
   * @param adjective adjective describing the gear
   * @param noun noun describing the gear
   * @return the updated IPlayerBuilder instance
   * @throws IllegalStateException when gear constructor inputs are invalid
   * @throws IllegalArgumentException when player state does not permit this action
   */
  public IPlayerBuilder addGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalStateException, IllegalArgumentException {
    try {
      IGear gear = new Gear(type, attack, defense, adjective, noun);
      this.player = this.player.addGear(gear);
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
