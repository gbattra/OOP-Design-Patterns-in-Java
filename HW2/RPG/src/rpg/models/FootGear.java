package rpg.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rpg.enums.GearClass;
import rpg.enums.GearType;
import rpg.interfaces.IFootGear;

/**
 * Type of IGear specifically for head gear. Has both attack and defense values.
 */
public class FootGear extends AbstractGear<IFootGear> implements IFootGear {
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
  public FootGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalArgumentException {
    super(type, attack, defense, adjective, noun);

    if (type.gearClass != GearClass.FOOTGEAR) {
      throw new IllegalArgumentException(
              String.format("GearType provided not of class %s", GearClass.FOOTGEAR.toString()));
    }
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
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty,
   * combinedGears != 2
   */
  public FootGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IFootGear> combinedGears) throws IllegalArgumentException {
    super(type, attack, defense, adjective, noun, combinedGears);

    if (type.gearClass != GearClass.FOOTGEAR) {
      throw new IllegalArgumentException(
              String.format("GearType provided not of class %s", GearClass.FOOTGEAR.toString()));
    }
  }

  /**
   * Combines this gear with another and returns a new instance with the merged values.
   *
   * @param gear IFootGear instance to merge with this
   * @return the new combined gear instance
   * @throws IllegalStateException when either this or the gear provided in is already combined
   */
  public IFootGear combine(IFootGear gear) throws IllegalArgumentException, IllegalStateException {
    if (this.isCombined) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Self is already combined with another gear.");
    }

    if (gear.isCombined()) {
      throw new IllegalArgumentException(
              "Cannot combine self to gear. Gear is already combined with another gear.");
    }

    if (this.type != gear.getType()) {
      throw new IllegalArgumentException("Cannot combine gears of two different types.");
    }

    IFootGear newGear = new FootGear(
            this.type,
            this.attack + gear.getAttack(),
            this.defense + gear.getDefense(),
            String.format("%s, %s", gear.getAdjective(), this.adjective),
            this.noun,
            new ArrayList<>(Arrays.asList(this, gear)));

    return newGear;
  }

  /**
   * Override equals() method.
   *
   * @param other the object to compare
   * @return boolean is this object equal to the other
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (other instanceof IFootGear) {
      IFootGear gear = (IFootGear) other;
      return gear.hashCode() == this.hashCode();
    }

    return false;
  }
}
