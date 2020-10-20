package rpg.models;

import java.util.List;

import rpg.enums.GearType;
import rpg.interfaces.IGear;

/**
 * Concrete class for AbstractGear. Gear has a type belonging to one of the following classes:
 * HEADGEAR, HANDGEAR, FOOTGEAR. Gear can combine itself with another gear of the same GearType,
 * which sums their respective defense and attack values, concatenates the adjective descriptors,
 * and initializes a new Gear instance with those values. The noun of the called gear is passed
 * to the new instance.
 */
public class Gear extends AbstractGear implements IGear {
  /**
   * Constructor for when this gear is not combined.
   *
   * @param type GearType for this gear instance
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty
   */
  public Gear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalArgumentException {
    super(type, attack, defense, adjective, noun);
  }

  /**
   * Constructor for when this gear is combined.
   *
   * @param type GearType for this gear instance
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @param combinedGears List of gears combined to form this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty
   */
  public Gear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IGear> combinedGears) throws IllegalArgumentException {
    super(type, attack, defense, adjective, noun, combinedGears);
  }
}
