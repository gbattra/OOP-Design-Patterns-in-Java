package rpg.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rpg.enums.GearClass;
import rpg.enums.GearType;
import rpg.interfaces.IFootGear;
import rpg.interfaces.IHeadGear;

/**
 * Type of IGear specifically for head gear. Has defense value but no attack value.
 */
public class HeadGear extends AbstractGear<IHeadGear> implements IHeadGear {
  /**
   * Constructor for when this gear is not combined.
   *
   * @param type GearType for this gear instance
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty
   */
  public HeadGear(
          GearType type,
          int defense,
          String adjective,
          String noun) throws IllegalArgumentException {
    super(type, 0, defense, adjective, noun);

    if (type.gearClass != GearClass.HEADGEAR) {
      throw new IllegalArgumentException(
              String.format("GearType provided not of class %s", GearClass.HEADGEAR.toString()));
    }
  }

  /**
   * Constructor for when this gear is combined.
   *
   * @param type GearType for this gear instance
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @param combinedGears List of gears combined to form this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty,
   * combinedGears != 2
   */
  public HeadGear(
          GearType type,
          int defense,
          String adjective,
          String noun,
          List<IHeadGear> combinedGears) throws IllegalArgumentException {
    super(type, 0, defense, adjective, noun, combinedGears);

    if (type.gearClass != GearClass.HEADGEAR) {
      throw new IllegalArgumentException(
              String.format("GearType provided not of class %s", GearClass.HEADGEAR.toString()));
    }
  }

  /**
   * Combines this gear with another and returns a new instance with the merged values.
   *
   * @param gear IHeadGear instance to merge with this
   * @return the new combined gear instance
   * @throws IllegalStateException when either this or the gear provided in is already combined
   */
  public IHeadGear combine(IHeadGear gear) throws IllegalArgumentException, IllegalStateException {
    if (this.isCombined) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Self is already combined with another gear.");
    }

    if (gear.isCombined()) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Gear is already combined with another gear.");
    }

    IHeadGear newGear = new HeadGear(
            this.type,
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

    if (other instanceof IHeadGear) {
      IHeadGear gear = (IHeadGear) other;
      return gear.hashCode() == this.hashCode();
    }

    return false;
  }
}
