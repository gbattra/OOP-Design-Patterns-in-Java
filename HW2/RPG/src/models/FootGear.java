package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.IFootGear;

/**
 * Type of IGear specifically for head gear. Has both attack and defense values.
 */
public class FootGear extends AbstractGear<IFootGear> implements IFootGear {
  /**
   * Constructor for when this gear is not combined.
   *
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty
   */
  public FootGear(
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalArgumentException {
    super(attack, defense, adjective, noun);
  }

  /**
   * Constructor for when this gear is combined.
   *
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @param combinedGears List of gears combined to form this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty,
   * combinedGears != 2
   */
  public FootGear(
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IFootGear> combinedGears) throws IllegalArgumentException {
    super(attack, defense, adjective, noun, combinedGears);
  }

  /**
   * Combines this gear with another and returns a new instance with the merged values.
   *
   * @param gear IFootGear instance to merge with this
   * @return the new combined gear instance
   * @throws IllegalStateException when either this or the gear provided in is already combined
   */
  public IFootGear combine(IFootGear gear) throws IllegalStateException {
    if (this.isCombined) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Self is already combined with another gear.");
    }

    if (gear.isCombined()) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Gear is already combined with another gear.");
    }

    IFootGear newGear = new FootGear(
            this.attack + gear.getAttack(),
            this.defense + gear.getDefense(),
            String.format("%s, %s", this.adjective, gear.getAdjective()),
            this.noun,
            new ArrayList<>(Arrays.asList(this, gear)));

    return newGear;
  }
}
